package model.factory.klasy;

import model.factory.ArenaAbs;

/**
 * Created by VuYeK on 30.01.2016.
 */
public class Hunter extends ArenaAbs {
    public Hunter(int id_areny, int wygrane, String klasa) {
        super(id_areny, wygrane, klasa);
    }

    public Hunter(int wygrane, String klasa) {
        super(wygrane, klasa);
    }
}
