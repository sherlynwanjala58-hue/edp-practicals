interface DataObserver {
    void update(int newValue);
}

public class BarChartDisplay implements DataObserver {
    @Override
    public void update(int newValue) {
        System.out.println("Bar chart height: " + newValue);
    }
}
