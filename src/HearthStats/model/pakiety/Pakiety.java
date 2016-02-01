package HearthStats.model.pakiety;

import HearthStats.base.ConnectDB;
import HearthStats.model.pakiety.chain.GvG;
import HearthStats.model.pakiety.chain.Klasyczne;
import HearthStats.model.pakiety.chain.Obsluga;
import HearthStats.model.pakiety.chain.TGT;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by VuYeK on 03.11.2015.
 */
public class Pakiety {


    //Po��czenie z baz�
    //public ConnectDB baza = new ConnectDB();


    private static volatile Pakiety instance = null;
    public int[] tablica = new int[3];

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

        klasyczne.setNextObsluge(gvg);
        gvg.setNextObsluge(tgt);

        return klasyczne;
    }


}