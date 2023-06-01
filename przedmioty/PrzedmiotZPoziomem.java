package przedmioty;

public abstract class PrzedmiotZPoziomem implements Przedmiot {

    private int poziom;

    public PrzedmiotZPoziomem(int poziom) {
        this.poziom = poziom;
    }

    public int poziom() {
        return poziom;
    }
}
