package agenci;

import giełda.Giełda;
import przedmioty.Przedmioty;

public class Wypukły extends Spekulant {

    @Override
    public void grajDzień(Giełda giełda) {
        for (Przedmioty przedmiot : Przedmioty.values()) {

            if (giełda.rodzajFunkcji(przedmiot) == RodzajFunkcji.Wypukła) {
                if (przedmiot == Przedmioty.Jedzenie) {
                    dodajOfertyKupnaJedzenia(giełda,
                            (int) (giełda.średniaCenaZOkresu(przedmiot, 1) * 0.9));
                } else if (przedmiot == Przedmioty.Ubranie) {
                    dodajOfertyKupnaUbrań(giełda,
                            (int) (giełda.średniaCenaZOkresu(przedmiot, 1) * 0.9));
                } else if (przedmiot == Przedmioty.Narzędzie) {
                    dodajOfertyKupnaNarzędzi(giełda,
                            (int) (giełda.średniaCenaZOkresu(przedmiot, 1) * 0.9));
                } else if (przedmiot == Przedmioty.ProgramKomputerowy) {
                    dodajOfertyKupnaProgramówKomputerowych(giełda,
                            (int) (giełda.średniaCenaZOkresu(przedmiot, 1) * 0.9));
                }
            }

            if (giełda.rodzajFunkcji(przedmiot) == RodzajFunkcji.Wklęsła) {
                if (przedmiot == Przedmioty.Jedzenie && ilośćJedzenia > 0) {
                    dodajOfertySprzedażyJedzenia(giełda, giełda.średniaCenaZOkresu(przedmiot, 1));
                } else if (przedmiot == Przedmioty.Ubranie && listaUbrań.size() > 0) {
                    dodajOfertySprzedażyUbrań(giełda, giełda.średniaCenaZOkresu(przedmiot, 1));
                } else if (przedmiot == Przedmioty.Narzędzie && listaNarzędzi.size() > 0) {
                    dodajOfertySprzedażyNarzędzi(giełda, giełda.średniaCenaZOkresu(przedmiot, 1));
                } else if (przedmiot == Przedmioty.ProgramKomputerowy
                        && listaProgramówKomputerowych.size() > 0) {
                    dodajOfertySprzedażyProgramówKomputerowych(giełda,
                            giełda.średniaCenaZOkresu(przedmiot, 1));
                }
            }
        }
    }

}
