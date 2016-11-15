package model.statystyki.obserwator;


import model.statystyki.obserwowany.Klasa;
import javafx.collections.ObservableList;

/**
 * Created by VuYeK on 07.12.2015.
 */
public interface IObservator {
    void update(ObservableList<Klasa> klasy);
}
