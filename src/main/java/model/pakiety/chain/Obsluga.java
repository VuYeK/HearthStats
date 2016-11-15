package model.pakiety.chain;

import model.pakiety.Pakiety;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VuYeK on 31.01.2016.
 */
public abstract class Obsluga {


    protected String dodatek;
    protected int[] tablica = new int[3];

    protected static List<Obsluga> orderOfObsluga = new ArrayList<>();
    protected static int iter = 0;

    public void obsluz(String dodatek, String dzialanie, Pakiety pakiety) {
        if (iter != orderOfObsluga.size()) {
            if (dodatek == orderOfObsluga.get(iter).getDodatek()) {
                switch (dzialanie) {
                    case "dodaj": {
                        orderOfObsluga.get(iter).dodaj_pakiet();
                        break;
                    }
                    case "wyswietl": {
                        orderOfObsluga.get(iter).wyswietl_pakiety(pakiety);
                        break;
                    }
                    case "resetPakiety":{
                        orderOfObsluga.get(iter).resetuj_pakiety();
                        break;
                    }
                    case "resetLegenda":{
                        orderOfObsluga.get(iter).resetuj_legende();
                        break;
                    }
                    case "resetEpik":{
                        orderOfObsluga.get(iter).resetuj_epika();
                        break;
                    }
                }
            }
            orderOfObsluga.get(iter++).obsluz(dodatek,dzialanie,pakiety);
        }
        else iter=0;
    }




    public void setNextObsluge(Obsluga obsluga) {
        orderOfObsluga.add(obsluga);
    }


    protected abstract void dodaj_pakiet();
    protected abstract void wyswietl_pakiety(Pakiety pakiety);
    protected abstract void resetuj_pakiety();
    protected abstract void resetuj_legende();
    protected abstract void resetuj_epika();


    public String getDodatek(){return dodatek;}

}