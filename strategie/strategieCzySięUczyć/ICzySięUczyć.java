package strategie.strategieCzySięUczyć;

import agenci.Robotnik;
import giełda.Giełda;

/**
 * Interfejs strategii robotnika, wybierającej, czy dany dzień poświęcić na
 * naukę czy pracę
 */
public interface ICzySięUczyć {

    public boolean czySięUczyć(Giełda giełda, Robotnik robotnik);
}
