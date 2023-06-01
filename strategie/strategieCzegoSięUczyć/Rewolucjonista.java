package strategie.strategieCzegoSięUczyć;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Rewolucjonista implements ICzegoSięUczyć {

    @Override
    public Przedmioty czegoSięUczyć(Giełda giełda, Robotnik robotnik) {
        return giełda.najwyższaŚredniaCenaZOkresu(Math.max(1, robotnik.id()) % 17);
    }

}
