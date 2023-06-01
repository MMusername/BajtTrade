package strategie.strategieCzegoSięUczyć;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Konserwatysta implements ICzegoSięUczyć {

    @Override
    public Przedmioty czegoSięUczyć(Giełda giełda, Robotnik robotnik) {
        return robotnik.obecaKariera();
    }

}
