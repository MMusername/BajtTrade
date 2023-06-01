package strategie.strategieCzySięUczyć;

import java.util.Random;

import agenci.Robotnik;
import giełda.Giełda;

public class Rozkładowy implements ICzySięUczyć {

    @Override
    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik) {
        Random random = new Random();
        return random.nextDouble() < (1 / giełda.dzień() + 3);
    }

}
