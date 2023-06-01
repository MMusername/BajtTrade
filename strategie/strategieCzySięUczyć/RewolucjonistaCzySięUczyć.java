package strategie.strategieCzySięUczyć;

import agenci.Robotnik;
import giełda.Giełda;

public class RewolucjonistaCzySięUczyć implements ICzySięUczyć {

    @Override
    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik) {
        return giełda.dzień() % 7 == 0;
    }

}
