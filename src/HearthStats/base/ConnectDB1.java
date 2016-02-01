package HearthStats.base;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class ConnectDB1 {

    public Connection polaczenie = null;
    public PreparedStatement prepareStmt;

    private static volatile ConnectDB1 instance = null;

    /**
     * Singleton ktory tworzy tylko 1 instancje klasy na wszystkich watkach(synchronized).
     */
    public static ConnectDB1 getInstance() {
        if (instance == null) {
            synchronized (ConnectDB1.class) {
                if (instance == null) {
                    instance = new ConnectDB1();
                }
            }
        }
        return instance;
    }


    public ConnectDB1() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:database/HS/Areny1.sqlite");
            System.out.println("Polaczenie z baza danych Areny1 udane!");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
