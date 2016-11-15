package model.statystyki.obserwator;

import model.statystyki.obserwowany.Klasa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Created by VuYeK on 07.12.2015.
 */
public class PieChartModel implements IObservator {
    private javafx.scene.chart.PieChart chart;

    public PieChartModel(javafx.scene.chart.PieChart chart){
        this.chart = chart;
    }

    @Override
    public void update(ObservableList<Klasa> klasy) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Klasa klasa : klasy) {
            String name = klasa.getName();
            double mark = klasa.getWygrane();
            pieChartData.add(new PieChart.Data(name, mark));
        }

        this.chart.setData(pieChartData);
    }

}
