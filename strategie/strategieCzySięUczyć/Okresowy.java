package strategie.strategieCzySięUczyć;

import agenci.Robotnik;
import giełda.Giełda;

public class Okresowy implements ICzySięUczyć {

    private final int okres;

    public Okresowy(int okres) {
        this.okres = okres;
    }

    @Override
    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik) {
        return giełda.dzień() % okres == 0;
    }

}
