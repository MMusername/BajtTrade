package oferty;

import java.util.Comparator;

public class OfertaSpekulantaComparator implements Comparator<OfertaSpekulanta> {

    @Override
    public int compare(OfertaSpekulanta a, OfertaSpekulanta b) {
        if (a.przedmiot().typPrzedmiotu() != b.przedmiot().typPrzedmiotu()) {
            return a.przedmiot().typPrzedmiotu().compareTo(b.przedmiot().typPrzedmiotu());
        }
        if (a.przedmiot().poziom() != b.przedmiot().poziom()) {
            return a.przedmiot().poziom() - b.przedmiot().poziom();
        }
        return (int) (a.cena() - b.cena());
    }

}
