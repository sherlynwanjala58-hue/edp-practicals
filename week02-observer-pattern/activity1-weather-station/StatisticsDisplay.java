interface Observer {
    void update(float temperature, float humidity, float pressure);
}

public class StatisticsDisplay implements Observer {
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;

    public void update(float temperature, float humidity, float pressure) {
        maxTemp = Math.max(maxTemp, temperature);
        minTemp = Math.min(minTemp, temperature);
        System.out.println("  [Statistics] Max: " + maxTemp + "°C, Min: " + minTemp + "°C");
    }
}