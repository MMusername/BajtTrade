package agenci;

import java.util.List;

import przedmioty.Narzędzie;
import przedmioty.ProgramKomputerowy;
import przedmioty.Przedmiot;
import przedmioty.Ubranie;

/**
 * Nadklasa Robotników i Spekulantów
 */
public abstract class Agent {

    protected int ilośćJedzenia;
    protected float ilośćDiamentów;

    protected List<Ubranie> listaUbrań;
    protected List<Narzędzie> listaNarzędzi;
    protected List<ProgramKomputerowy> listaProgramówKomputerowych;

    public float diamenty() {
        return ilośćDiamentów;
    }

    public void przelew(float kwota) {
        ilośćDiamentów += kwota;
    }

    public abstract void dodaj(Przedmiot przedmiot, int ilość);
}
