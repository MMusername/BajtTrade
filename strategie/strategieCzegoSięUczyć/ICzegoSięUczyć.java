package strategie.strategieCzegoSięUczyć;

import agenci.Robotnik;
import giełda.Giełda;
import przedmioty.Przedmioty;

/**
 * Interfejs strategii nauki robotnika, podejmujący decyzję czego się uczyć
 * danego dnia
 */
public interface ICzegoSięUczyć {

    public Przedmioty czegoSięUczyć(Giełda giełda, Robotnik robotnik);
}
