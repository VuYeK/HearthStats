package model.pakiety.chain;

import base.ConnectDB;
import model.pakiety.Pakiety;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by VuYeK on 31.01.2016.
 */
public class FrozenThrone extends Obsluga {
    private final static String DODATEK = "Frozen Throne";

    public FrozenThrone(){
        this.dodatek = DODATEK;
        if(orderOfObsluga==null && orderOfObsluga.size()==0){
            orderOfObsluga.add(this);
        }
    }

    @Override
    public void dodaj_pakiet(){
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE pakiety SET ilosc=ilosc+1, legenda=legenda-1, epik=epik-1 WHERE rodzaj = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    @Override
    public void wyswietl_pakiety(Pakiety pakiety) {

        ResultSet result = null;
        int[] tablica = new int[5];
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("SELECT * FROM pakiety WHERE rodzaj = '"+DODATEK+"'");
            result = ConnectDB.getInstance().prepareStmt.executeQuery();

            for(int i=0; result.next(); i++) {
                tablica[0] = result.getInt("ilosc");
                tablica[1] = result.getInt("legenda");
                tablica[2] = result.getInt("epik");
            }
            //return tablica;


            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("SELECT * FROM statPakiety WHERE dodatek = '"+DODATEK+"'");
            result = ConnectDB.getInstance().prepareStmt.executeQuery();

            for(int i=0; result.next(); i++) {
                tablica[3] = result.getInt("liczbaLegend");
                tablica[4] = result.getInt("liczbaEpikow");
            }


            pakiety.tablica=tablica;


        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
            //return null;
        }

    }


    @Override
    public void resetuj_pakiety() {
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE pakiety SET ilosc=0 WHERE rodzaj = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE statPakiety SET liczbaLegend=0, liczbaEpikow=0 WHERE dodatek = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    @Override
    public void resetuj_legende() {
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE pakiety SET legenda=40 WHERE rodzaj = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE statPakiety SET liczbaLegend=liczbaLegend+1 WHERE dodatek = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    @Override
    public void resetuj_epika() {
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE pakiety SET epik=10 WHERE rodzaj = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("UPDATE statPakiety SET liczbaEpikow=liczbaEpikow+1 WHERE dodatek = '"+DODATEK+"'");
            ConnectDB.getInstance().prepareStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
