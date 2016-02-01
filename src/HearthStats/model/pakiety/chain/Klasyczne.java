package HearthStats.model.pakiety.chain;

import HearthStats.base.ConnectDB;
import HearthStats.model.pakiety.Pakiety;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by VuYeK on 31.01.2016.
 */
public class Klasyczne extends Obsluga {
    private final static String DODATEK = "Klasyczne";

    public Klasyczne(){
        this.dodatek = DODATEK;
        if(orderOfObsluga.size() == 0){
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
        int[] tablica = new int[3];
        try {
            ConnectDB.getInstance().prepareStmt = ConnectDB.getInstance().polaczenie.prepareStatement("SELECT * FROM pakiety WHERE rodzaj = '"+DODATEK+"'");
            result = ConnectDB.getInstance().prepareStmt.executeQuery();

            for(int i=0; result.next(); i++) {
                tablica[0] = result.getInt("ilosc");
                tablica[1] = result.getInt("legenda");
                tablica[2] = result.getInt("epik");
            }
            //return tablica;
            pakiety.tablica = tablica;


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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
