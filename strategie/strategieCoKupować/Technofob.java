package strategie.strategieCoKupować;

import java.util.ArrayList;
import java.util.List;

import agenci.Robotnik;
import giełda.Giełda;
import oferty.OfertaRobotnika;
import przedmioty.Jedzenie;

public class Technofob implements ICoKupować {

    @Override
    public void coKupować(Giełda giełda, Robotnik robotnik) {
        List<OfertaRobotnika> oferty = new ArrayList<>();
        oferty.add(new OfertaRobotnika(robotnik, new Jedzenie(), 100));
        giełda.dodajOfertyKupnaRobotnika(oferty);
    }

}
