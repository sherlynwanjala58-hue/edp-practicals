import java.util.ArrayList;
import java.util.List;

interface DataObserver {
    void update(int value);
}

interface DataSubject {
    void addObserver(DataObserver observer);
    void removeObserver(DataObserver observer);
    void notifyObservers();
}

public class DataModel implements DataSubject {
    private List<DataObserver> observers = new ArrayList<>();
    private int value;

    @Override
    public void addObserver(DataObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(DataObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (DataObserver o : observers) {
            o.update(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }
}