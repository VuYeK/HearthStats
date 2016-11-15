package model.pakiety;

import base.ConnectDB;
import model.pakiety.chain.*;


/**
 * Created by VuYeK on 03.11.2015.
 */
public class Pakiety {


    //Po��czenie z baz�
    //public ConnectDB baza = new ConnectDB();


    private static volatile Pakiety instance = null;
    public int[] tablica = new int[5];

    //Singleton ktory tworzy tylko 1 instancje klasy na wszystkich watkach(synchronized)
    public static Pakiety getInstance() {
        if (instance == null) {
            synchronized (Pakiety.class) {
                if (instance == null) {
                    instance = new Pakiety();
                    ConnectDB.getInstance();
                }
            }
        }
        return instance;
    }



    //Konstruktor
    private Pakiety() {
    }



    public Obsluga getChainOfObsluga(){
        Obsluga klasyczne = new Klasyczne();
        Obsluga gvg = new GvG();
        Obsluga tgt = new TGT();
        Obsluga oldgods = new OldGods();

        klasyczne.setNextObsluge(gvg);
        gvg.setNextObsluge(tgt);
        tgt.setNextObsluge(oldgods);

        return klasyczne;
    }


}