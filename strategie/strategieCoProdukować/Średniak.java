package strategie.strategieCoProdukować;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Średniak implements ICoProdukować {

    private final int okres;

    public Średniak(int okres) {
        this.okres = okres;
    }

    @Override
    public Przedmioty coProdukować(Giełda giełda, Robotnik robotnik) {
        return giełda.najwyższaŚredniaCenaZOkresu(okres);
    }

}
