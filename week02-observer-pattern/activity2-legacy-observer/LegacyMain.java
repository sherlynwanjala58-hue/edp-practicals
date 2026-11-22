import java.util.Observable;
import java.util.Observer;

public class LegacyMain {
    public static void main(String[] args) {
        LegacyWeatherData data = new LegacyWeatherData();
        LegacyDisplay display = new LegacyDisplay();
        data.addObserver(display);
        data.setMeasurements(26.0f, 60.0f);
        data.setMeasurements(30.0f, 75.0f);
    }
}

@SuppressWarnings("deprecation")
class LegacyWeatherData extends Observable {
    private float temperature;
    private float humidity;

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        setChanged();
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }
}

@SuppressWarnings("deprecation")
class LegacyDisplay implements Observer {
    @Override
    public void update(Observable observable, Object arg) {
        LegacyWeatherData data = (LegacyWeatherData) observable;
        System.out.println("Temperature: " + data.getTemperature()
                + "°C, Humidity: " + data.getHumidity() + "%");
    }
}