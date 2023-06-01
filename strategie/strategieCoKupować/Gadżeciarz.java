package strategie.strategieCoKupować;

import java.util.ArrayList;
import java.util.List;

import agenci.Robotnik;
import giełda.Giełda;
import oferty.OfertaRobotnika;
import przedmioty.ProgramKomputerowy;

public class Gadżeciarz implements ICoKupować {

    private final int liczbaNarzędzi;

    public Gadżeciarz(int liczbaNarzędzi) {
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    @Override
    public void coKupować(Giełda giełda, Robotnik robotnik) {
        List<OfertaRobotnika> oferty = new ArrayList<>();
        new Zmechanizowany(liczbaNarzędzi).coKupować(giełda, robotnik);
        oferty.add(new OfertaRobotnika(robotnik, new ProgramKomputerowy(0),
                robotnik.ileWyprodukowano()));
    }

}
