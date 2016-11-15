package model.factory.klasy;

import model.factory.ArenaAbs;

/**
 * Created by VuYeK on 30.01.2016.
 */
public class Paladin extends ArenaAbs {
    public Paladin(int id_areny, int wygrane, String klasa) {
        super(id_areny, wygrane, klasa);
    }

    public Paladin(int wygrane, String klasa) {
        super(wygrane, klasa);
    }
}
