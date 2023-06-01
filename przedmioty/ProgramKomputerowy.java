package przedmioty;

public class ProgramKomputerowy extends PrzedmiotZPoziomem {

    public ProgramKomputerowy(int poziom) {
        super(poziom);
    }

    @Override
    public Przedmioty typPrzedmiotu() {
        return Przedmioty.ProgramKomputerowy;
    }

}
