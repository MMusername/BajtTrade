package giełda;

import java.util.Collections;
import java.util.List;

import agenci.Robotnik;
import agenci.Spekulant;
import oferty.OfertaRobotnikaComparator;

public class Zrównoważona extends Giełda {

    public Zrównoważona(List<Robotnik> listaRobotników, List<Spekulant> listaSpekulantów,
            int karaZaBrakUbrań, List<Float> cenyDniaZerowego) {
        super(listaRobotników, listaSpekulantów, karaZaBrakUbrań, cenyDniaZerowego);
    }

    OfertaRobotnikaComparator comparatorKapitalistyczny = new OfertaRobotnikaComparator();
    OfertaRobotnikaComparator comparatorSocjalistyczny = (OfertaRobotnikaComparator) Collections
            .reverseOrder(new OfertaRobotnikaComparator());

    @Override
    protected void ustawOferty() {
        if (dzień % 2 == 0) {
            Collections.sort(ofertySprzedażyRobotników, comparatorKapitalistyczny);
            Collections.sort(ofertySprzedażyRobotników, comparatorKapitalistyczny);
        } else {
            Collections.sort(ofertySprzedażyRobotników, comparatorSocjalistyczny);
            Collections.sort(ofertySprzedażyRobotników, comparatorSocjalistyczny);
        }
    }

}
