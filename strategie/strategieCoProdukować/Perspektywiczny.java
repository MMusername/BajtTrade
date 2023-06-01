package strategie.strategieCoProdukować;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

public class Perspektywiczny implements ICoProdukować {

    private final int okres;

    public Perspektywiczny(int okres) {
        this.okres = okres;
    }

    @Override
    public Przedmioty coProdukować(Giełda giełda, Robotnik robotnik) {
        return giełda.najwyższyWzrost(okres);
    }

}
