package strategie.strategieCoKupować;

import java.util.ArrayList;
import java.util.List;

import agenci.Robotnik;
import giełda.Giełda;
import oferty.OfertaRobotnika;
import przedmioty.Ubranie;

public class Czyścioszek implements ICoKupować {

    @Override
    public void coKupować(Giełda giełda, Robotnik robotnik) {
        List<OfertaRobotnika> oferty = new ArrayList<>();
        new Technofob().coKupować(giełda, robotnik);
        int ile = 0;
        for (Ubranie ubranie : robotnik.listaUbrań()) {
            if (ubranie.dniDoZużycia() != 1) {
                ile++;
            }
        }
        if (ile < 100) {
            oferty.add(new OfertaRobotnika(robotnik, new Ubranie(0), 100 - ile));
        }
        giełda.dodajOfertyKupnaRobotnika(oferty);
    }

}
