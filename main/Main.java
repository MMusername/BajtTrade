package main;

import giełda.Giełda;
import wczytywanie.WczytajDane;

/**
 * @author Mateusz Mroczka, Program do prowadzenia symulacji rynku Bajt Trade
 */
public class Main {

    public static void main(String[] args) {
        Giełda giełda = new WczytajDane().twórzGiełdę();
        giełda.grajSymulacje();
    }

}
