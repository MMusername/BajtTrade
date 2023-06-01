package przedmioty;

public class Jedzenie implements Przedmiot {

    @Override
    public Przedmioty typPrzedmiotu() {
        return Przedmioty.Jedzenie;
    }

    @Override
    public int poziom() {
        return 0;
    }

}
