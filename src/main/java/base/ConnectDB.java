package base;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class ConnectDB {

    public Connection polaczenie = null;
    public PreparedStatement prepareStmt;

    private static volatile ConnectDB instance = null;

    /**
     * Singleton ktory tworzy tylko 1 instancje klasy na wszystkich watkach(synchronized).
     */
    public static ConnectDB getInstance() {
        if (instance == null) {
            synchronized (ConnectDB.class) {
                if (instance == null) {
                    instance = new ConnectDB();
                }
            }
        }
        return instance;
    }


    private ConnectDB() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            File file = new File("src/main/resources/database/HS.sqlite");
            if (file.exists())
                polaczenie = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/HS.sqlite");
            else
                polaczenie = DriverManager.getConnection("jdbc:sqlite:database/HS.sqlite");
            System.out.println("Polaczenie z baza danych Pakiety udane!");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
