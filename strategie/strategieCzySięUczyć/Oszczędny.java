package strategie.strategieCzySięUczyć;

import agenci.Robotnik;
import giełda.Giełda;

public class Oszczędny implements ICzySięUczyć {

    private final int limitDiamentów;

    public Oszczędny(int limitDiamnetów) {
        this.limitDiamentów = limitDiamnetów;
    }

    @Override
    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik) {
        return robotnik.diamenty() > limitDiamentów;
    }

}
