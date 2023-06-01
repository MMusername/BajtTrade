package strategie.strategieCoProdukować;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Chciwy implements ICoProdukować {

    @Override
    public Przedmioty coProdukować(Giełda giełda, Robotnik robotnik) {
        Przedmioty najlepszyPrzedmiot = Przedmioty.Ubranie;
        float max = -1;
        for (Przedmioty przedmiot : Przedmioty.values()) {
            if (giełda.średniaCenaZOkresu(przedmiot, 1)
                    * robotnik.ileWyprodukuje(przedmiot, giełda) >= max) {
                max = giełda.średniaCenaZOkresu(przedmiot, 1)
                        * robotnik.ileWyprodukuje(przedmiot, giełda);
                najlepszyPrzedmiot = przedmiot;
            }
        }
        return najlepszyPrzedmiot;
    }

}
