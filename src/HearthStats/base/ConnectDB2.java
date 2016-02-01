package HearthStats.base;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class ConnectDB2 {

    public Connection polaczenie = null;
    public PreparedStatement prepareStmt;

    private static volatile ConnectDB2 instance = null;

    /**
     * Singleton ktory tworzy tylko 1 instancje klasy na wszystkich watkach(synchronized).
     */
    public static ConnectDB2 getInstance() {
        if (instance == null) {
            synchronized (ConnectDB2.class) {
                if (instance == null) {
                    instance = new ConnectDB2();
                }
            }
        }
        return instance;
    }


    public ConnectDB2() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:database/HS/Areny2.sqlite");
            System.out.println("Polaczenie z baza danych Areny2 udane!");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

}
