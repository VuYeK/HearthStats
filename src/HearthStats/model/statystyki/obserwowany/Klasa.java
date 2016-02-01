package HearthStats.model.statystyki.obserwowany;

import HearthStats.base.ConnectDB;
import HearthStats.base.ConnectDB1;
import HearthStats.base.ConnectDB2;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by VuYeK on 07.12.2015.
 */
public class Klasa {
    private String name = "";
    private Integer wygrane, wybrane;


    public Klasa(String name) {
        this.name = name;
        pobierzIloscWyborowKlasy();
        pobierzIloscWygranychKlasy();
    }

    //GETTERs and SETTERs:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getWygrane() {
        return wygrane;
    }
    public void setWygrane(Integer wygrane) {
        this.wygrane = wygrane;
    }

    public Integer getWybrane() {
        return wybrane;
    }
    public void setWybrane(Integer wybrane) {
        this.wybrane = wybrane;
    }





    public void pobierzIloscWyborowKlasy()
    {
        ResultSet result = null;
        try {
            ConnectDB2.getInstance().prepareStmt = ConnectDB2.getInstance().polaczenie.prepareStatement("SELECT COUNT(klasa) AS wybrane FROM areny2 WHERE klasa=?");
            ConnectDB2.getInstance().prepareStmt.setString(1, this.name);
            result = ConnectDB2.getInstance().prepareStmt.executeQuery();

            this.wybrane=result.getInt("wybrane");
            //System.out.println(wybrane);

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
        }
    }



    public void pobierzIloscWygranychKlasy()
    {
        ResultSet result = null;
        try {
            ArrayList<Integer> listaNumerow = new ArrayList<>();
            ConnectDB2.getInstance().prepareStmt = ConnectDB2.getInstance().polaczenie.prepareStatement("SELECT id_areny FROM areny2 WHERE klasa=?");
            ConnectDB2.getInstance().prepareStmt.setString(1, this.name);
            result = ConnectDB2.getInstance().prepareStmt.executeQuery();

            for(int i=0; result.next(); i++) {
                listaNumerow.add(result.getInt("id_areny"));
            }


            int tempWygrane = 0;
            for(int i=0; i<listaNumerow.size(); i++)
            {
                ConnectDB1.getInstance().prepareStmt = ConnectDB1.getInstance().polaczenie.prepareStatement("SELECT wygrane FROM areny1 WHERE id_areny=?");
                ConnectDB1.getInstance().prepareStmt.setString(1, listaNumerow.get(i).toString());
                result = ConnectDB1.getInstance().prepareStmt.executeQuery();
                tempWygrane += result.getInt("wygrane");
            }

            this.wygrane=tempWygrane;
            //System.out.println(wygrane);

        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
            e1.printStackTrace();
        }
    }
}
