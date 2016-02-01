package HearthStats.model.statystyki.obserwator;

import HearthStats.model.statystyki.obserwowany.Klasa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * Created by VuYeK on 07.12.2015.
 */
public class BarChartModel implements IObservator {
    private javafx.scene.chart.BarChart<String, Integer> chart;
    private CategoryAxis categoryAxis;

    public BarChartModel(javafx.scene.chart.BarChart<String, Integer> chart, CategoryAxis categoryAxis){
        this.chart = chart;
        this.categoryAxis = categoryAxis;
    }

    @Override
    public void update(ObservableList<Klasa> klasy) {
        this.chart.getData().clear();

        //ustawianie nazw X
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Klasa klasa : klasy) {
            names.add(klasa.getName());
        }
        this.categoryAxis.setCategories(names);

        //ustalanie Y dla kazdego osobnego X.
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (Klasa klasa : klasy) {
            XYChart.Data<String, Integer> value = new XYChart.Data<>(klasa.getName(), klasa.getWybrane());
            series.getData().add(value);
        }

        this.chart.getData().add(series);
    }

}