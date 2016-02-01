package HearthStats.model.factory;

import HearthStats.model.factory.facade.ArenaPart1;
import HearthStats.model.factory.facade.ArenaPart2;

/**
 * Created by VuYeK on 03.11.2015.
 */
public abstract class ArenaAbs {

    ArenaPart1 arenaPart1;
    ArenaPart2 arenaPart2;

    //Konstruktor z ID
    public ArenaAbs(int id_areny, int wygrane, String klasa) {
        this.arenaPart1 = new ArenaPart1(id_areny,wygrane);
        this.arenaPart2 = new ArenaPart2(id_areny,klasa);
    }

    //Konstruktor bez ID
    public ArenaAbs(int wygrane, String klasa) {
        this.arenaPart1 = new ArenaPart1(wygrane);
        this.arenaPart2 = new ArenaPart2(klasa);
    }

    /*GET*/
    public int getId_areny() {
        return arenaPart1.getId_areny();
    }
    public int getWygrane(){return arenaPart1.getWygrane();}
    public String getKlasa() {
        return arenaPart2.getKlasa();
    }



    public ArenaPart1 getArenaPart1(){ return arenaPart1;}
    public void setArenaPart1(ArenaPart1 arenaTemp1){this.arenaPart1=arenaTemp1;}
    public ArenaPart2 getArenaPart2(){ return arenaPart2;}
    public void setArenaPart2(ArenaPart2 arenaTemp2){this.arenaPart2=arenaTemp2;}

}
