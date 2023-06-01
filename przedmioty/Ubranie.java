package przedmioty;

public class Ubranie extends PrzedmiotZPoziomem {

    private int turDoKońca;

    public Ubranie(int poziom) {
        super(poziom);
        this.turDoKońca = poziom * poziom;
    }

    public void zużyj() {
        turDoKońca--;
    }

    public int dniDoZużycia() {
        return turDoKońca;
    }

    @Override
    public Przedmioty typPrzedmiotu() {
        return Przedmioty.Ubranie;
    }

}
