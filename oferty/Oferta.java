package oferty;

import agenci.Agent;
import przedmioty.Przedmiot;

/*
 * Oferta, którą Agenci wystawiają na Giełdzie, aby dokonać tranzakcji
 */
public abstract class Oferta {

    protected Agent wystawiający;
    protected Przedmiot przedmiot;
    protected int ilość;

    public Oferta(Agent wystawiający, Przedmiot przedmiot, int ilość) {
        this.wystawiający = wystawiający;
        this.przedmiot = przedmiot;
        this.ilość = ilość;
    }

    public Agent wystawiający() {
        return wystawiający;
    }

    public Przedmiot przedmiot() {
        return przedmiot;
    }

    public int ilość() {
        return ilość;
    }

    public void zmieńIlość(int oIle) {
        ilość += oIle;
    }
}
