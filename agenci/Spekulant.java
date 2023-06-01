package agenci;

import java.util.ArrayList;
import java.util.List;

import giełda.Giełda;
import oferty.OfertaSpekulanta;
import przedmioty.Jedzenie;
import przedmioty.Narzędzie;
import przedmioty.ProgramKomputerowy;
import przedmioty.Przedmiot;
import przedmioty.Ubranie;

/**
 * Osoba zajmująca się handlem na giełdzie
 */
public abstract class Spekulant extends Agent {

    protected List<OfertaSpekulanta> ofertySprzedaży = new ArrayList<>();
    protected List<OfertaSpekulanta> ofertyKupna = new ArrayList<>();

    public abstract void grajDzień(Giełda giełda);

    protected void dodajOfertySprzedażyJedzenia(Giełda giełda, float cenaJedzenia) {
        ofertySprzedaży
                .add(new OfertaSpekulanta(this, new Jedzenie(), ilośćJedzenia, cenaJedzenia));
        ilośćJedzenia = 0;
    }

    protected void dodajOfertyKupnaJedzenia(Giełda giełda, float cenaJedzenia) {
        ofertyKupna.add(new OfertaSpekulanta(this, new Jedzenie(), 100, cenaJedzenia));
    }

    protected void dodajOfertySprzedażyUbrań(Giełda giełda, float cenaUbrań) {
        int aktualnyPoziom = 0;
        int ileUbrań = 0;
        if (listaUbrań.size() > 0) {
            aktualnyPoziom = listaUbrań.get(0).poziom();
        }
        for (Ubranie ubranie : listaUbrań) {
            if (ubranie.poziom() == aktualnyPoziom) {
                ileUbrań++;
            } else {
                ofertySprzedaży.add(new OfertaSpekulanta(this, new Ubranie(aktualnyPoziom),
                        ileUbrań, cenaUbrań));
                ileUbrań = 1;
            }
            aktualnyPoziom = ubranie.poziom();
        }

        listaUbrań.clear();
    }

    protected void dodajOfertyKupnaUbrań(Giełda giełda, float cenaUbrań) {
        ofertyKupna.add(new OfertaSpekulanta(this, new Ubranie(0), 100, cenaUbrań));
    }

    protected void dodajOfertySprzedażyNarzędzi(Giełda giełda, float cenaNarzędzi) {
        int aktualnyPoziom = 0;
        int ileNarzędzi = 0;
        if (listaNarzędzi.size() > 0) {
            aktualnyPoziom = listaNarzędzi.get(0).poziom();
        }
        for (Narzędzie narzędzie : listaNarzędzi) {
            if (narzędzie.poziom() == aktualnyPoziom) {
                ileNarzędzi++;
            } else {
                ofertySprzedaży.add(new OfertaSpekulanta(this, new Narzędzie(aktualnyPoziom),
                        ileNarzędzi, (int) (cenaNarzędzi * 1.1)));
                ileNarzędzi = 1;
            }
            aktualnyPoziom = narzędzie.poziom();
        }

        listaNarzędzi.clear();
    }

    protected void dodajOfertyKupnaNarzędzi(Giełda giełda, float cenaNarzędzi) {
        ofertyKupna.add(new OfertaSpekulanta(this, new Narzędzie(0), 100, cenaNarzędzi));
    }

    protected void dodajOfertySprzedażyProgramówKomputerowych(Giełda giełda, float cenaProgramów) {
        int aktualnyPoziom = 0;
        int ileProgramów = 0;
        if (listaProgramówKomputerowych.size() > 0) {
            aktualnyPoziom = listaProgramówKomputerowych.get(0).poziom();
        }
        for (ProgramKomputerowy programKomputerowy : listaProgramówKomputerowych) {
            if (programKomputerowy.poziom() == aktualnyPoziom) {
                ileProgramów++;
            } else {
                ofertySprzedaży.add(new OfertaSpekulanta(this,
                        new ProgramKomputerowy(aktualnyPoziom), ileProgramów, cenaProgramów));
                ileProgramów = 1;
            }
            aktualnyPoziom = programKomputerowy.poziom();
        }

        listaProgramówKomputerowych.clear();
    }

    protected void dodajOfertyKupnaProgramówKomputerowych(Giełda giełda, float cenaProgramów) {
        ofertyKupna.add(new OfertaSpekulanta(this, new ProgramKomputerowy(0), 100, cenaProgramów));
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
                listaNarzędzi.add(new Narzędzie(przedmiot.poziom()));
            }
            break;
        case ProgramKomputerowy:
            for (int i = 0; i < ilość; i++) {
                listaProgramówKomputerowych.add(new ProgramKomputerowy(przedmiot.poziom()));
            }
            break;
        default:
            break;
        }
    }

}
