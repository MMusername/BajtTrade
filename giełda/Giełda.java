package giełda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agenci.Agent;
import agenci.Robotnik;
import agenci.RodzajFunkcji;
import agenci.Spekulant;
import oferty.OfertaRobotnika;
import oferty.OfertaSpekulanta;
import oferty.OfertaSpekulantaComparator;
import przedmioty.Przedmioty;

/**
 * Główna klasa prowadząca symulację
 */
public abstract class Giełda {

    private List<Robotnik> listaRobotników;
    private List<Spekulant> listaSpekulantów;
    protected int dzień;

    protected List<OfertaRobotnika> ofertySprzedażyRobotników;
    protected List<OfertaRobotnika> ofertyKupnaRobotników;
    protected List<OfertaSpekulanta> ofertySprzedażySpekulantów;
    protected List<OfertaSpekulanta> ofertyKupnaSpekulantów;

    private Map<Przedmioty, ArrayList<Float>> historiaŚrednichCen = new HashMap<Przedmioty, ArrayList<Float>>();
    private Map<Przedmioty, ArrayList<Integer>> historiaIleWystawiono = new HashMap<Przedmioty, ArrayList<Integer>>();
    private Map<Przedmioty, Float> sumaCenDzisiaj = new HashMap<Przedmioty, Float>();
    private Map<Przedmioty, Integer> ilośćPrzedmiotówDzisiaj = new HashMap<Przedmioty, Integer>();

    private final int karaZaBrakUbrań;

    private OfertaSpekulantaComparator comparator = new OfertaSpekulantaComparator();

    public Giełda(List<Robotnik> listaRobotników, List<Spekulant> listaSpekulantów,
            int karaZaBrakUbrań, List<Float> cenyDniaZerowego) {
        this.listaRobotników = new ArrayList<Robotnik>();
        this.listaRobotników.addAll(listaRobotników);
        this.listaSpekulantów = new ArrayList<Spekulant>();
        this.listaSpekulantów.addAll(listaSpekulantów);
        this.dzień = 1;
        this.karaZaBrakUbrań = karaZaBrakUbrań;

        int i = 0;
        for (Przedmioty przedmiot : Przedmioty.values()) {
            historiaŚrednichCen.put(przedmiot, new ArrayList<Float>());
            historiaŚrednichCen.get(przedmiot).add(cenyDniaZerowego.get(i));
            historiaIleWystawiono.put(przedmiot, new ArrayList<Integer>());
            i++;
        }
        przygotujMapy();
    }

    public void grajSymulacje() {
        while (listaRobotników.size() != 0 && listaSpekulantów.size() > 0) {
            grajDzień();
        }
    }

    private void grajDzień() {
        grajDzieńRobotników();
        grajDzieńSpekulantów();
        dopasujOferty();
        uzupełnijHistorięŚrednichCen();
        czyśćOferty();
        zużyjPrzedmiotyRobotników();
        dzień++;

        przygotujMapy();
    }

    private void zużyjPrzedmiotyRobotników() {
        for (Robotnik robotnik : listaRobotników) {
            robotnik.zużyjPrzedmioty();
        }
        for (Robotnik robotnik : listaRobotników) {
            if (robotnik.dniBezJedzenia() >= 3) {
                listaRobotników.remove(robotnik);
            }
        }
    }

    private void czyśćOferty() {
        ofertySprzedażySpekulantów.clear();
        ofertyKupnaSpekulantów.clear();

        for (OfertaRobotnika oferta : ofertySprzedażyRobotników) {
            Robotnik robotnik = (Robotnik) oferta.wystawiający();
            Przedmioty typPrzedmiotu = oferta.przedmiot().typPrzedmiotu();
            ArrayList<Float> historia = historiaŚrednichCen.get(typPrzedmiotu);
            robotnik.przelew(historia.get(historia.size() - 1));
        }
        ofertySprzedażyRobotników.clear();
    }

    private void dopasujOferty() {
        ustawOferty();
        Collections.sort(ofertySprzedażySpekulantów, comparator);
        Collections.sort(ofertyKupnaSpekulantów, comparator);

        dopasujOfertySprzedażyRobotników();
        dopasujOfertyKupnaRobotników();
    }

    private void dopasujOfertySprzedażyRobotników() {
        for (OfertaRobotnika ofertaRobotnika : ofertySprzedażyRobotników) {
            for (OfertaSpekulanta ofertaSpekulanta : ofertyKupnaSpekulantów) {
                Przedmioty typ = ofertaRobotnika.przedmiot().typPrzedmiotu();
                if (typ == ofertaSpekulanta.przedmiot().typPrzedmiotu()) {

                    float cena = ofertaSpekulanta.cena();
                    Agent robotnik = ofertaRobotnika.wystawiający();
                    Agent spekulant = ofertaSpekulanta.wystawiający();

                    int ileUdaSięKupić = Math.min(ofertaRobotnika.ilość(),
                            ofertaSpekulanta.ilość());
                    ileUdaSięKupić = Math.min(ileUdaSięKupić, (int) (spekulant.diamenty() / cena));
                    robotnik.przelew(ileUdaSięKupić * cena);
                    spekulant.przelew(-ileUdaSięKupić * cena);
                    ofertaRobotnika.zmieńIlość(-ileUdaSięKupić);
                    ofertaSpekulanta.zmieńIlość(ileUdaSięKupić);
                    if (ofertaRobotnika.ilość() == 0) {
                        ofertySprzedażyRobotników.remove(ofertaRobotnika);
                    }
                    if (ofertaSpekulanta.ilość() == 0) {
                        ofertyKupnaSpekulantów.remove(ofertaSpekulanta);
                    }

                    sumaCenDzisiaj.put(typ, sumaCenDzisiaj.get(typ) + (ileUdaSięKupić * cena));
                    ilośćPrzedmiotówDzisiaj.put(typ,
                            ilośćPrzedmiotówDzisiaj.get(typ) + ileUdaSięKupić);
                }
            }
        }
    }

    private void dopasujOfertyKupnaRobotników() {
        for (OfertaRobotnika ofertaRobotnika : ofertyKupnaRobotników) {
            for (OfertaSpekulanta ofertaSpekulanta : ofertySprzedażySpekulantów) {
                Przedmioty typ = ofertaRobotnika.przedmiot().typPrzedmiotu();
                if (typ == ofertaSpekulanta.przedmiot().typPrzedmiotu()) {

                    float cena = ofertaSpekulanta.cena();
                    Agent robotnik = ofertaRobotnika.wystawiający();
                    Agent spekulant = ofertaSpekulanta.wystawiający();

                    int ileUdaSięKupić = Math.min(ofertaRobotnika.ilość(),
                            ofertaSpekulanta.ilość());
                    ileUdaSięKupić = Math.min(ileUdaSięKupić, (int) (robotnik.diamenty() / cena));
                    robotnik.przelew(-ileUdaSięKupić * cena);
                    spekulant.przelew(ileUdaSięKupić * cena);
                    ofertaRobotnika.zmieńIlość(ileUdaSięKupić);
                    ofertaSpekulanta.zmieńIlość(-ileUdaSięKupić);
                    if (ofertaRobotnika.ilość() == 0) {
                        ofertySprzedażyRobotników.remove(ofertaRobotnika);
                    }
                    if (ofertaSpekulanta.ilość() == 0) {
                        ofertyKupnaSpekulantów.remove(ofertaSpekulanta);
                    }

                    sumaCenDzisiaj.put(typ, sumaCenDzisiaj.get(typ) + (ileUdaSięKupić * cena));
                    ilośćPrzedmiotówDzisiaj.put(typ,
                            ilośćPrzedmiotówDzisiaj.get(typ) + ileUdaSięKupić);
                }
            }
        }
    }

    protected abstract void ustawOferty();

    private void grajDzieńSpekulantów() {
        for (Spekulant spekulant : listaSpekulantów) {
            spekulant.grajDzień(this);
        }
    }

    private void grajDzieńRobotników() {
        for (Robotnik robotnik : listaRobotników) {
            robotnik.grajDzień(this);
        }
    }

    private void przygotujMapy() {
        for (Przedmioty przedmiot : Przedmioty.values()) {
            sumaCenDzisiaj.put(przedmiot, (float) 0);
            ilośćPrzedmiotówDzisiaj.put(przedmiot, 0);
        }
    }

    private void uzupełnijHistorięŚrednichCen() {
        for (Przedmioty przedmiot : Przedmioty.values()) {
            historiaIleWystawiono.get(przedmiot).add(ilośćPrzedmiotówDzisiaj.get(przedmiot));
            if (ilośćPrzedmiotówDzisiaj.get(przedmiot) != 0) {
                historiaŚrednichCen.get(przedmiot).add(
                        sumaCenDzisiaj.get(przedmiot) / ilośćPrzedmiotówDzisiaj.get(przedmiot));
            } else {
                historiaŚrednichCen.get(przedmiot).add((float) 0);
            }
        }
    }

    public void dodajOfertySprzedażyRobotnika(List<OfertaRobotnika> oferty) {
        ofertySprzedażyRobotników.addAll(oferty);
    }

    public void dodajOfertyKupnaRobotnika(List<OfertaRobotnika> oferty) {
        ofertyKupnaRobotników.addAll(oferty);
    }

    public void dodajOfertySprzedażySpekulanta(List<OfertaSpekulanta> oferty) {
        ofertySprzedażySpekulantów.addAll(oferty);
    }

    public void dodajOfertyKupnaSpekulanta(List<OfertaSpekulanta> oferty) {
        ofertyKupnaSpekulantów.addAll(oferty);
    }

    public int karaZaBrakUbrań() {
        return karaZaBrakUbrań;
    }

    // TODO dodać sobie potem średnia cena z wczoraj
    public float średniaCenaZOkresu(Przedmioty przedmiot, int okres) {
        ArrayList<Float> historiaCen = historiaŚrednichCen.get(przedmiot);
        float suma = 0;
        int rozmiar = Math.min(okres, historiaCen.size() - 1);
        for (int i = rozmiar; i >= 0; i--) {
            suma += historiaCen.get(i);
        }
        return suma / rozmiar;
    }

    public Przedmioty najwyższaŚredniaCenaZOkresu(int okres) {
        Przedmioty wygrywający = Przedmioty.Jedzenie;
        float maxŚredniaCena = -1;
        for (Przedmioty przedmiot : Przedmioty.values()) {
            ArrayList<Float> historia = historiaŚrednichCen.get(przedmiot);
            int rozmiar = Math.min(okres, historia.size() - 1);
            for (int i = rozmiar; i >= 0; i--) {
                if (maxŚredniaCena <= historia.get(i)) {
                    maxŚredniaCena = historia.get(i);
                    wygrywający = przedmiot;
                }
            }
        }
        return wygrywający;
    }

    public int ilePrzedmiotówWystawiono(Przedmioty przedmiot, int dniTemu) {
        int rozmiar = historiaIleWystawiono.get(przedmiot).size();
        if (rozmiar >= dniTemu) {
            return historiaIleWystawiono.get(przedmiot).get(rozmiar - dniTemu);
        }
        return 0;
    }

    public RodzajFunkcji rodzajFunkcji(Przedmioty przedmiot) {
        ArrayList<Float> historia = historiaŚrednichCen.get(przedmiot);
        int rozmiar = historia.size();
        if (rozmiar >= 3) {
            float dzieńJeden = historia.get(rozmiar - 3);
            float dzieńDwa = historia.get(rozmiar - 2);
            float dzieńTrzy = historia.get(rozmiar - 1);
            if (dzieńJeden > dzieńDwa && dzieńTrzy > dzieńDwa)
                return RodzajFunkcji.Wklęsła;
            if (dzieńJeden < dzieńDwa && dzieńTrzy < dzieńDwa)
                return RodzajFunkcji.Wypukła;
        }
        return RodzajFunkcji.Inna;
    }

    public int dzień() {
        return dzień;
    }

    public Przedmioty najwyższyWzrost(int okres) {
        int rozmiar = historiaŚrednichCen.get(Przedmioty.Jedzenie).size();
        int dzieńJeden = Math.min(0, rozmiar - okres);
        Przedmioty wygrywający = Przedmioty.Jedzenie;
        float maxWzrost = historiaŚrednichCen.get(wygrywający).get(rozmiar - 1)
                - historiaŚrednichCen.get(wygrywający).get(dzieńJeden);
        for (Przedmioty przedmiot : Przedmioty.values()) {
            if (historiaŚrednichCen.get(przedmiot).get(rozmiar - 1)
                    - historiaŚrednichCen.get(przedmiot).get(dzieńJeden) >= maxWzrost) {
                maxWzrost = historiaŚrednichCen.get(przedmiot).get(rozmiar - 1)
                        - historiaŚrednichCen.get(przedmiot).get(dzieńJeden);
                wygrywający = przedmiot;
            }
        }
        return wygrywający;
    }
}
