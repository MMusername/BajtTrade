package przedmioty;

import java.util.Comparator;

public class ProgramKomputerowyComparator implements Comparator<ProgramKomputerowy> {

    @Override
    public int compare(ProgramKomputerowy a, ProgramKomputerowy b) {
        return a.poziom() - b.poziom();
    }

}
