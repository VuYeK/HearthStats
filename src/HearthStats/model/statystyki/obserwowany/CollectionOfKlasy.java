package HearthStats.model.statystyki.obserwowany;


import HearthStats.model.statystyki.obserwator.IObservator;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VuYeK on 07.12.2015.
 */
public class CollectionOfKlasy implements IObservable {
    //private List<Student> students = new ArrayList<>();
    private List<IObservator> observers = new ArrayList<>();

    public CollectionOfKlasy() {
    }



    //Observe method
    @Override
    public void attachToAll(IObservator observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detachToAll(IObservator observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyAllObservers(ObservableList<Klasa> klasy)
    {
        for (IObservator observer : observers) {
            observer.update(klasy);
        }
    }

}
