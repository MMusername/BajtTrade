package strategie.strategieCoKupować;

import agenci.Robotnik;
import giełda.Giełda;

/**
 * Interfejs strategii zakupów robotnika, podejmujący decyzję co kupić danego
 * dnia
 */
public interface ICoKupować {

    public void coKupować(Giełda giełda, Robotnik robotnik);
}
