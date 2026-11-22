import java.beans.PropertyChangeListener;

public interface Subject {
    void registerObserver(PropertyChangeListener listener);
    void removeObserver(PropertyChangeListener listener);
    void notifyObservers();
}