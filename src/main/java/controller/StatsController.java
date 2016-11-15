package controller;

import model.statystyki.obserwator.BarChartModel;
import model.statystyki.obserwator.PieChartModel;
import model.statystyki.obserwowany.CollectionOfKlasy;
import model.statystyki.obserwowany.Klasa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by VuYeK on 14.01.2016.
 */
public class StatsController implements Initializable {

    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis barChartAxisX;
    @FXML private PieChart pieChart;

    public Klasa paladin = new Klasa("Warrior");
    private CollectionOfKlasy collectionOfKlasy;
    private BarChartModel barChartMod;
    private PieChartModel pieChartMod;
    private ObservableList<Klasa> klasy;
    private List<Klasa> klasaList = new ArrayList<>();








    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //podpiecie Modelu
        collectionOfKlasy = new CollectionOfKlasy();
        pieChartMod = new PieChartModel(pieChart);
        barChartMod = new BarChartModel(barChart, barChartAxisX);

        generateKlasy();
        setKlasyArray();
        collectionOfKlasy.attachToAll(barChartMod);
        collectionOfKlasy.attachToAll(pieChartMod);
        collectionOfKlasy.notifyAllObservers(klasy);

    }



    private void setKlasyArray() {
        klasy = FXCollections.observableArrayList(klasaList);
        //ID_TABLE_VIEW.setItems(students);
    }


    private void generateKlasy()
    {
        String[] tablica = new String[9];
        tablica[0]="Warrior";tablica[1]="Shaman";tablica[2]="Rogue";tablica[3]="Paladin";tablica[4]="Hunter";tablica[5]="Druid";tablica[6]="Warlock";tablica[7]="Mage";tablica[8]="Priest";

        for(String klasa : tablica)
        {
            Klasa klasaTemp = new Klasa(klasa);
            klasaList.add(klasaTemp);
        }
    }
}
