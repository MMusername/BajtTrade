package strategie.strategieCoProdukować;

import java.util.Random;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Losowy implements ICoProdukować {

    @Override
    public Przedmioty coProdukować(Giełda giełda, Robotnik robotnik) {
        return Przedmioty.values()[new Random().nextInt(Przedmioty.values().length)];
    }

}
