package HearthStats.model.factory;

import HearthStats.model.factory.klasy.*;

/**
 * Created by VuYeK on 30.01.2016.
 */
public class ArenaFactory {


    public enum DostepneKlasy {
        DRUID,
        HUNTER,
        MAGE,
        PALADIN,
        PRIEST,
        ROGUE,
        SHAMAN,
        WARLOCK,
        WARRIOR
    }

    public static ArenaAbs getArena(int id_areny, int wygrane, String klasa)
    {
        DostepneKlasy currentKlasa = DostepneKlasy.valueOf(klasa.toUpperCase());

        if(currentKlasa != null) {
            switch (currentKlasa) {
                case DRUID:
                    Druid druid = new Druid(id_areny, wygrane, klasa);
                    return druid;
                case HUNTER:
                    Hunter hunter = new Hunter(id_areny, wygrane, klasa);
                    return hunter;
                case MAGE:
                    Mage mage = new Mage(id_areny, wygrane, klasa);
                    return mage;
                case PALADIN:
                    Paladin paladin = new Paladin(id_areny, wygrane, klasa);
                    return paladin;
                case PRIEST:
                    Priest priest = new Priest(id_areny, wygrane, klasa);
                    return priest;
                case ROGUE:
                    Rogue rogue = new Rogue(id_areny, wygrane, klasa);
                    return rogue;
                case SHAMAN:
                    Shaman shaman = new Shaman(id_areny, wygrane, klasa);
                    return shaman;
                case WARLOCK:
                    Warlock warlock = new Warlock(id_areny, wygrane, klasa);
                    return warlock;
                case WARRIOR:
                    Warrior warrior = new Warrior(id_areny, wygrane, klasa);
                    return warrior;
            }
        }
        return null;
    }

    public static ArenaAbs getArena(int wygrane, String klasa)
    {
        DostepneKlasy currentKlasa = DostepneKlasy.valueOf(klasa.toUpperCase());

        if(currentKlasa != null) {
            switch (currentKlasa) {
                case DRUID:
                    Druid druid = new Druid(wygrane, klasa);
                    return druid;
                case HUNTER:
                    Hunter hunter = new Hunter(wygrane, klasa);
                    return hunter;
                case MAGE:
                    Mage mage = new Mage(wygrane, klasa);
                    return mage;
                case PALADIN:
                    Paladin paladin = new Paladin(wygrane, klasa);
                    return paladin;
                case PRIEST:
                    Priest priest = new Priest(wygrane, klasa);
                    return priest;
                case ROGUE:
                    Rogue rogue = new Rogue(wygrane, klasa);
                    return rogue;
                case SHAMAN:
                    Shaman shaman = new Shaman(wygrane, klasa);
                    return shaman;
                case WARLOCK:
                    Warlock warlock = new Warlock(wygrane, klasa);
                    return warlock;
                case WARRIOR:
                    Warrior warrior = new Warrior(wygrane, klasa);
                    return warrior;
            }
        }
        return null;
    }

}
