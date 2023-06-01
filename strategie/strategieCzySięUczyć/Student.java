package strategie.strategieCzySięUczyć;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Student implements ICzySięUczyć {

    private final int zapas;
    private final int okres;

    public Student(int zapas, int okres) {
        this.zapas = zapas;
        this.okres = okres;
    }

    @Override
    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik) {
        return robotnik.diamenty() > 100 * zapas
                * giełda.średniaCenaZOkresu(Przedmioty.Jedzenie, okres);
    }

}
