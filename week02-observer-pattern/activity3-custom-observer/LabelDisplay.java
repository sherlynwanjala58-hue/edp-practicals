interface DataObserver {
    void update(int newValue);
}

public class LabelDisplay implements DataObserver {
    @Override
    public void update(int newValue) {
        System.out.println("Label shows: " + newValue);
    }
}