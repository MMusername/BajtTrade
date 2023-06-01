package oferty;

import java.util.Comparator;

import agenci.Robotnik;

public class OfertaRobotnikaComparator implements Comparator<OfertaRobotnika> {

    @Override
    public int compare(OfertaRobotnika a, OfertaRobotnika b) {
        if (a.wystawiający().diamenty() != b.wystawiający().diamenty()) {
            return (int) (a.wystawiający().diamenty() - b.wystawiający().diamenty());
        }
        return ((Robotnik) (a.wystawiający())).id() - ((Robotnik) (b.wystawiający())).id();
    }

}
