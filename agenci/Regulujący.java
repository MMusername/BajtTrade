package agenci;

import giełda.Giełda;
import przedmioty.Przedmioty;

public class Regulujący extends Spekulant {

    @Override
    public void grajDzień(Giełda giełda) {
        if (giełda.dzień() > 0) {
            for (Przedmioty przedmiot : Przedmioty.values()) {
                float cena = giełda.średniaCenaZOkresu(przedmiot, 1)
                        * giełda.ilePrzedmiotówWystawiono(przedmiot, 1)
                        / Math.max(1, giełda.ilePrzedmiotówWystawiono(przedmiot, 2));
                if (przedmiot == Przedmioty.Jedzenie) {
                    dodajOfertyKupnaJedzenia(giełda, (float) (cena * 0.9));
                    if (ilośćJedzenia > 0) {
                        dodajOfertySprzedażyJedzenia(giełda, (float) (cena * 1.1));
                    }
                } else if (przedmiot == Przedmioty.Ubranie) {
                    dodajOfertyKupnaUbrań(giełda, (float) (cena * 0.9));
                    if (listaUbrań.size() > 0) {
                        dodajOfertySprzedażyUbrań(giełda, (float) (cena * 1.1));
                    }
                } else if (przedmiot == Przedmioty.Narzędzie) {
                    dodajOfertyKupnaNarzędzi(giełda, (float) (cena * 0.9));
                    if (listaNarzędzi.size() > 0) {
                        dodajOfertySprzedażyNarzędzi(giełda, (float) (cena * 1.1));
                    }
                } else if (przedmiot == Przedmioty.ProgramKomputerowy) {
                    dodajOfertyKupnaProgramówKomputerowych(giełda, (float) (cena * 0.9));
                    if (listaProgramówKomputerowych.size() > 0) {
                        dodajOfertySprzedażyProgramówKomputerowych(giełda, (float) (cena * 1.1));
                    }
                }
            }
        }
    }

}
