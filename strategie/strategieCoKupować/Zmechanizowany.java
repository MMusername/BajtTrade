package strategie.strategieCoKupować;

import java.util.ArrayList;
import java.util.List;

import agenci.Robotnik;
import giełda.Giełda;
import oferty.OfertaRobotnika;
import przedmioty.Narzędzie;

public class Zmechanizowany implements ICoKupować {

    private final int liczbaNarzędzi;

    public Zmechanizowany(int liczbaNarzędzi) {
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    @Override
    public void coKupować(Giełda giełda, Robotnik robotnik) {
        List<OfertaRobotnika> oferty = new ArrayList<>();
        new Czyścioszek().coKupować(giełda, robotnik);
        oferty.add(new OfertaRobotnika(robotnik, new Narzędzie(0), liczbaNarzędzi));
        giełda.dodajOfertyKupnaRobotnika(oferty);
    }

}
