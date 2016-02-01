package HearthStats.model.factory;

import HearthStats.base.ConnectDB;
import HearthStats.base.ConnectDB1;
import HearthStats.base.ConnectDB2;
import HearthStats.model.factory.facade.ArenaPart1;
import HearthStats.model.factory.facade.ArenaPart2;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by VuYeK on 03.11.2015.
 */
public class Areny{
    ArrayList<ArenaAbs> arenki = new ArrayList<>();

    //Po��czenie z baz�
    //public ConnectDB baza = new ConnectDB();


    private static volatile Areny instance = null;

    //Singleton ktory tworzy tylko 1 instancje klasy na wszystkich watkach(synchronized)
    public static Areny getInstance() {
        if (instance == null) {
            synchronized (Areny.class) {
                if (instance == null) {
                    instance = new Areny();
                    ConnectDB.getInstance();
                    ConnectDB1.getInstance();
                    ConnectDB2.getInstance();
                }
            }
        }
        return instance;
    }



    //Konstruktor
    private Areny() {
    }




    ////////////////////////// Dodawanie areny do bazy /////////////////////////////////
    public void dodaj_arene(ArenaAbs arenka) {
        if (arenka != null) {
            boolean firstFlag = dodaj_arene_part1(arenka.getArenaPart1());
            if (!firstFlag) {
            } else {
                boolean secondFlag = dodaj_arene_part2(arenka.getArenaPart2());
                if (!secondFlag) {
                }
            }
        }
    }

    //Dodanie do 1 bazy
    public boolean dodaj_arene_part1(ArenaPart1 arenka) {
        try {

            ConnectDB1.getInstance().prepareStmt = ConnectDB1.getInstance().polaczenie.prepareStatement("insert into areny1 values (null, ?);");

            int i=0;
            ConnectDB1.getInstance().prepareStmt.setString(++i, String.valueOf(arenka.getWygrane()));
            ConnectDB1.getInstance().prepareStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Dodanie do 2 bazy
    public boolean dodaj_arene_part2(ArenaPart2 arenka) {
        try {

            ConnectDB2.getInstance().prepareStmt = ConnectDB2.getInstance().polaczenie.prepareStatement("insert into areny2 values (null, ?);");

            int i=0;
            ConnectDB2.getInstance().prepareStmt.setString(++i, arenka.getKlasa());
            ConnectDB2.getInstance().prepareStmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
            return false;
        }
        return true;
    }




    ////////////////////////// Pobranie aren z bazy /////////////////////////////////
    public ArrayList<ArenaAbs> wyswietl_areny() {

        ArrayList<ArenaAbs> arenki = new ArrayList<>();
        ArrayList<ArenaPart1> arenkiPart1 = new ArrayList<>();
        ArrayList<ArenaPart2> arenkiPart2 = new ArrayList<>();
        arenkiPart1 = wyswietl_areny_part1();
        arenkiPart2 = wyswietl_areny_part2();

        for (int i=0; i<arenkiPart1.size() && i<arenkiPart2.size(); ++i)
        {
            ArenaAbs arenka;
            arenka = ArenaFactory.getArena(arenkiPart1.get(i).getId_areny(), arenkiPart1.get(i).getWygrane(), arenkiPart2.get(i).getKlasa());
            arenki.add(arenka);
        }
        return arenki;
    }

    //Pobranie aren z 1 bazy
    public ArrayList<ArenaPart1> wyswietl_areny_part1() {

        ResultSet result = null;
        ArrayList<ArenaPart1> arenki = new ArrayList<>();
        try {
            ConnectDB1.getInstance().prepareStmt = ConnectDB1.getInstance().polaczenie.prepareStatement("SELECT * FROM areny1 ORDER BY id_areny DESC");
            result = ConnectDB1.getInstance().prepareStmt.executeQuery();
            int id_areny, wygrane;

            for(int i=0; result.next(); i++) {
                id_areny = result.getInt("id_areny");
                wygrane = result.getInt("wygrane");
                ArenaPart1 arenka = new ArenaPart1(id_areny, wygrane);
                //arenka = ArenaFactory.getArena(id_areny,wygrane,klasa);
                arenki.add(arenka);
            }
            return arenki;

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
            return null;
        }

    }

    //Pobranie aren z 2 bazy
    public ArrayList<ArenaPart2> wyswietl_areny_part2() {

        ResultSet result = null;
        ArrayList<ArenaPart2> arenki = new ArrayList<>();
        try {
            ConnectDB2.getInstance().prepareStmt = ConnectDB2.getInstance().polaczenie.prepareStatement("SELECT * FROM areny2 ORDER BY id_areny DESC");
            result = ConnectDB2.getInstance().prepareStmt.executeQuery();
            int id_areny;
            String klasa;

            for(int i=0; result.next(); i++) {
                id_areny = result.getInt("id_areny");
                klasa = result.getString("klasa");
                ArenaPart2 arenka = new ArenaPart2(id_areny,klasa);
                //arenka = ArenaFactory.getArena(id_areny,wygrane,klasa);
                arenki.add(arenka);
            }
            return arenki;

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
            return null;
        }

    }





    ////////////////////////// Usuniecie areny z bazy /////////////////////////////////
    public void usun_arene(ArenaAbs arenka) {

        int id = arenka.getId_areny();
        int wygrane = arenka.getWygrane();
        String klasa = arenka.getKlasa();
        try {
            ConnectDB1.getInstance().prepareStmt = ConnectDB1.getInstance().polaczenie.prepareStatement("DELETE from areny1 " +
                    "where id_areny = '" + id + "' AND wygrane = '" + wygrane + "'");

            ConnectDB1.getInstance().prepareStmt.execute();


            ConnectDB2.getInstance().prepareStmt = ConnectDB2.getInstance().polaczenie.prepareStatement("DELETE from areny2 " +
                    "where id_areny = '" + id + "' AND klasa = '" + klasa + "'");

            ConnectDB2.getInstance().prepareStmt.execute();
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
        }
    }
}