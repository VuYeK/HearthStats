package model.statystyki.obserwowany;


import model.statystyki.obserwator.IObservator;
import javafx.collections.ObservableList;

/**
 * Created by VuYeK on 09.12.2015.
 */
public interface IObservable {
    void attachToAll(IObservator observer);
    void detachToAll(IObservator observer);
    void notifyAllObservers(ObservableList<Klasa> klasas);
}
