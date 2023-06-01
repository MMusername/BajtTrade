package giełda;

import java.util.Collections;
import java.util.List;

import agenci.Robotnik;
import agenci.Spekulant;
import oferty.OfertaRobotnikaComparator;

public class Socjalistyczna extends Giełda {

    public Socjalistyczna(List<Robotnik> listaRobotników, List<Spekulant> listaSpekulantów,
            int karaZaBrakUbrań, List<Float> cenyDniaZerowego) {
        super(listaRobotników, listaSpekulantów, karaZaBrakUbrań, cenyDniaZerowego);
    }

    OfertaRobotnikaComparator comparator = (OfertaRobotnikaComparator) Collections
            .reverseOrder(new OfertaRobotnikaComparator());

    @Override
    protected void ustawOferty() {
        Collections.sort(ofertySprzedażyRobotników, comparator);
        Collections.sort(ofertySprzedażyRobotników, comparator);
    }

}
