package giełda;

import java.util.Collections;
import java.util.List;

import agenci.Robotnik;
import agenci.Spekulant;
import oferty.OfertaRobotnikaComparator;

public class Kapitalistyczna extends Giełda {

    public Kapitalistyczna(List<Robotnik> listaRobotników, List<Spekulant> listaSpekulantów,
            int karaZaBrakUbrań, List<Float> cenyDniaZerowego) {
        super(listaRobotników, listaSpekulantów, karaZaBrakUbrań, cenyDniaZerowego);
    }

    OfertaRobotnikaComparator comparator = new OfertaRobotnikaComparator();

    @Override
    protected void ustawOferty() {
        Collections.sort(ofertySprzedażyRobotników, comparator);
        Collections.sort(ofertySprzedażyRobotników, comparator);
    }

}
