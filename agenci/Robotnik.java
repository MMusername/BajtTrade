package agenci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giełda.Giełda;
import oferty.OfertaRobotnika;
import przedmioty.Jedzenie;
import przedmioty.Narzędzie;
import przedmioty.ProgramKomputerowy;
import przedmioty.ProgramKomputerowyComparator;
import przedmioty.Przedmiot;
import przedmioty.Przedmioty;
import przedmioty.Ubranie;
import strategie.strategieCoKupować.ICoKupować;
import strategie.strategieCoProdukować.ICoProdukować;
import strategie.strategieCzegoSięUczyć.ICzegoSięUczyć;
import strategie.strategieCzySięUczyć.ICzySięUczyć;

/**
 * Produkuje dobra na rynek
 */
public class Robotnik extends Agent {

    private final int id;

    private final Map<Przedmioty, Integer> bazowyWektorProduktywności;

    private List<Narzędzie> listaNowychNarzędzi;
    private List<ProgramKomputerowy> listaNowychProgramówKomputerowych;

    private int ileProgramówUżytoTegoDnia = 0;

    private boolean czySięUczył;
    private int dniBezJedzenia = 0;
    private int ileWyprodukowano = 0;

    private Przedmioty obecnaKariera;

    private Map<Przedmioty, Integer> poziomy;

    private ICzySięUczyć czySięUczyć;
    private ICzegoSięUczyć czegoSięUczyć;
    private ICoProdukować coProdukować;
    private ICoKupować coKupować;

    public Robotnik(int id, int bazowyWektor[]) {
        this.id = id;

        bazowyWektorProduktywności = new HashMap<Przedmioty, Integer>();
        int i = 0;
        for (Przedmioty przedmiot : Przedmioty.values()) {
            bazowyWektorProduktywności.put(przedmiot, bazowyWektor[i]);
            i++;
        }
    }

    public void grajDzień(Giełda giełda) {
        if (czySięUczyć.czySięUczyć(giełda, this)) {
            czySięUczył = true;
            dniBezJedzenia = 0;
            ileWyprodukowano = 0;
            Przedmioty nowaKariera = czegoSięUczyć.czegoSięUczyć(giełda, this);
            if (nowaKariera == obecnaKariera) {
                Integer obecnyPoziom = poziomy.getOrDefault(nowaKariera, 0);
                poziomy.put(nowaKariera, obecnyPoziom + 1);
            } else {
                obecnaKariera = nowaKariera;
            }
        } else {
            czySięUczył = false;
            produkujPrzedmioty(giełda);
            coKupować.coKupować(giełda, this);
        }
    }

    private void produkujPrzedmioty(Giełda giełda) {
        Przedmioty przedmiot = coProdukować.coProdukować(giełda, this);
        List<OfertaRobotnika> oferty = new ArrayList<>();
        int ileWyprodukuje = ileWyprodukuje(przedmiot, giełda);
        if (ileWyprodukuje <= 0)
            return;
        ileProgramówUżytoTegoDnia = 0;
        switch (przedmiot) {
        case Jedzenie:
            oferty.add(new OfertaRobotnika(this, new Jedzenie(), ileWyprodukuje));
        case Ubranie:
            produkujUbranie(oferty, przedmiot, ileWyprodukuje);
        case Narzędzie:
            produkujNarzędzia(oferty, przedmiot, ileWyprodukuje);
        case ProgramKomputerowy:
            int poziom = 1;
            if (obecnaKariera == Przedmioty.ProgramKomputerowy) {
                poziom = poziomy.get(przedmiot);
            }
            oferty.add(new OfertaRobotnika(this, new ProgramKomputerowy(poziom), ileWyprodukuje));
        }

        ileWyprodukowano = ileWyprodukuje;
        giełda.dodajOfertySprzedażyRobotnika(oferty);
    }

    private void produkujUbranie(List<OfertaRobotnika> oferty, Przedmioty przedmiot,
            int ileWyprodukuje) {

        if (listaProgramówKomputerowych.isEmpty()) {
            oferty.add(new OfertaRobotnika(this, new Ubranie(1), ileWyprodukuje));
        } else {
            int obecnyPoziom = listaProgramówKomputerowych.get(0).poziom();
            int i = 1;
            int ile = 1;
            while (!listaProgramówKomputerowych.isEmpty() && ileWyprodukuje > 0) {
                while (obecnyPoziom == listaProgramówKomputerowych.get(i).poziom()
                        && ileWyprodukuje > 0) {
                    i++;
                    ile++;
                    ileWyprodukuje--;
                }
                oferty.add(new OfertaRobotnika(this, new Ubranie(obecnyPoziom), ile));
                ile = 1;
                obecnyPoziom = listaProgramówKomputerowych.get(i).poziom();
            }
            ileProgramówUżytoTegoDnia = i;
        }
    }

    private void produkujNarzędzia(List<OfertaRobotnika> oferty, Przedmioty przedmiot,
            int ileWyprodukuje) {

        if (listaProgramówKomputerowych.isEmpty()) {
            oferty.add(new OfertaRobotnika(this, new Narzędzie(1), ileWyprodukuje));
        } else {
            int obecnyPoziom = listaProgramówKomputerowych.get(0).poziom();
            int i = 1;
            int ile = 1;
            while (!listaProgramówKomputerowych.isEmpty() && ileWyprodukuje > 0) {
                while (obecnyPoziom == listaProgramówKomputerowych.get(i).poziom()
                        && ileWyprodukuje > 0) {
                    i++;
                    ile++;
                    ileWyprodukuje--;
                }
                oferty.add(new OfertaRobotnika(this, new Narzędzie(obecnyPoziom), ile));
                ile = 1;
                obecnyPoziom = listaProgramówKomputerowych.get(i).poziom();
            }

            ileProgramówUżytoTegoDnia = i;
        }
    }

    private int bonusPoziomu(int poziom) {
        switch (poziom) {
        case 1:
            return 50;
        case 2:
            return 150;
        case 3:
            return 300;
        default:
            return (poziom - 3) * 25 + 300;
        }
    }

    public int ileWyprodukuje(Przedmioty przedmiot, Giełda giełda) {
        int produkcja = bazowyWektorProduktywności.get(przedmiot);
        int bonusNarzędzi = 0;
        for (Narzędzie narzędzie : listaNarzędzi) {
            bonusNarzędzi += narzędzie.poziom();
        }
        int karaZaNieJedzenie = 0;
        switch (dniBezJedzenia) {
        case 1:
            karaZaNieJedzenie = 100;
        default:
            karaZaNieJedzenie = 300;
        }
        int karaZaBrakUbrań = 0;
        if (listaUbrań.size() < 100)
            karaZaBrakUbrań = giełda.karaZaBrakUbrań();
        if (przedmiot == obecnaKariera) {
            return produkcja * (int) ((bonusPoziomu(poziomy.get(przedmiot)) + bonusNarzędzi
                    - karaZaNieJedzenie - karaZaBrakUbrań) / 100);
        }
        return produkcja * (int) ((bonusNarzędzi - karaZaNieJedzenie - karaZaBrakUbrań) / 100);
    }

    public int dniBezJedzenia() {
        return dniBezJedzenia;
    }

    public void zużyjPrzedmioty() {
        if (czySięUczył == true)
            return;
        listaNarzędzi.clear();
        listaNarzędzi.addAll(listaNowychNarzędzi);
        listaNowychNarzędzi.clear();
        for (int i = 0; i < ileProgramówUżytoTegoDnia; i++) {
            listaProgramówKomputerowych.remove(0);
        }
        listaProgramówKomputerowych.addAll(listaNowychProgramówKomputerowych);
        listaNowychProgramówKomputerowych.clear();
        Collections.sort(listaProgramówKomputerowych, new ProgramKomputerowyComparator());

        for (int i = 0; i < 100 || i < listaUbrań.size(); i++) {
            listaUbrań.get(i).zużyj();
            if (listaUbrań.get(i).dniDoZużycia() == 0) {
                listaUbrań.remove(i);
                i--;
            }
        }
    }

    @Override
    public void dodaj(Przedmiot przedmiot, int ilość) {
        switch (przedmiot.typPrzedmiotu()) {
        case Jedzenie:
            ilośćJedzenia += ilość;
            break;
        case Ubranie:
            for (int i = 0; i < ilość; i++) {
                listaUbrań.add(new Ubranie(przedmiot.poziom()));
            }
            break;
        case Narzędzie:
            for (int i = 0; i < ilość; i++) {
                listaNowychNarzędzi.add(new Narzędzie(przedmiot.poziom()));
            }
            break;
        case ProgramKomputerowy:
            for (int i = 0; i < ilość; i++) {
                listaNowychProgramówKomputerowych.add(new ProgramKomputerowy(przedmiot.poziom()));
            }
            break;
        default:
            break;
        }
    }

    public Przedmioty obecaKariera() {
        return obecnaKariera;
    }

    public int id() {
        return id;
    }

    public List<Ubranie> listaUbrań() {
        return listaUbrań;
    }

    public int ileWyprodukowano() {
        return ileWyprodukowano;
    }

}
