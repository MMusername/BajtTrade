package strategie.strategieCoProdukować;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

/**
 * Interfejs strategii produkcji robotnika, podejmujący decyzję jaki przedmiot
 * produkować danego dnia
 */
public interface ICoProdukować {

    public Przedmioty coProdukować(Giełda giełda, Robotnik robotnik);
}
