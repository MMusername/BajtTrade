package oferty;

import agenci.Agent;
import przedmioty.Przedmiot;

public class OfertaSpekulanta extends Oferta {

    protected final float cena;

    public OfertaSpekulanta(Agent wystawiający, Przedmiot przedmiot, int ilość, float cena) {
        super(wystawiający, przedmiot, ilość);
        this.cena = cena;
    }

    public float cena() {
        return cena;
    }

}
