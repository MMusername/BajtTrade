package przedmioty;

public class Narzędzie extends PrzedmiotZPoziomem {

    public Narzędzie(int poziom) {
        super(poziom);
    }

    @Override
    public Przedmioty typPrzedmiotu() {
        return Przedmioty.Narzędzie;
    }

}
