package agenci;

import giełda.Giełda;
import przedmioty.Przedmioty;

public class Średni extends Spekulant {

    private final int okres;

    public Średni(int okres) {
        this.okres = okres;
    }

    @Override
    public void grajDzień(Giełda giełda) {
        ofertyJedzenia(giełda);
        ofertyUbrań(giełda);
        ofertyNarzędzi(giełda);
        ofertyProgramówKomputerowych(giełda);
    }

    private void ofertyJedzenia(Giełda giełda) {
        if (ilośćJedzenia > 0) {
            dodajOfertySprzedażyJedzenia(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Jedzenie, okres) * 1.1));
            dodajOfertyKupnaJedzenia(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Jedzenie, okres) * 0.9));
        } else {
            dodajOfertyKupnaJedzenia(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Jedzenie, okres) * 0.95));
        }
    }

    private void ofertyUbrań(Giełda giełda) {
        if (listaUbrań.size() > 0) {
            dodajOfertySprzedażyUbrań(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Ubranie, okres) * 1.1));
            dodajOfertyKupnaUbrań(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Ubranie, okres) * 0.9));
        } else {
            dodajOfertyKupnaUbrań(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Ubranie, okres) * 0.95));
        }
    }

    private void ofertyNarzędzi(Giełda giełda) {
        if (listaNarzędzi.size() > 0) {
            dodajOfertySprzedażyNarzędzi(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Narzędzie, okres) * 1.1));
            dodajOfertyKupnaNarzędzi(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Narzędzie, okres) * 0.9));
        } else {
            dodajOfertyKupnaNarzędzi(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.Narzędzie, okres) * 0.95));
        }
    }

    private void ofertyProgramówKomputerowych(Giełda giełda) {
        if (listaProgramówKomputerowych.size() > 0) {
            dodajOfertySprzedażyProgramówKomputerowych(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.ProgramKomputerowy, okres) * 1.1));
            dodajOfertyKupnaProgramówKomputerowych(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.ProgramKomputerowy, okres) * 0.9));
        } else {
            dodajOfertyKupnaProgramówKomputerowych(giełda,
                    (int) (giełda.średniaCenaZOkresu(Przedmioty.ProgramKomputerowy, okres) * 0.95));
        }
    }

}
