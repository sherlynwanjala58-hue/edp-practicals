public class WeatherStationMain {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(statsDisplay);
        weatherData.registerObserver(forecastDisplay);

        weatherData.setMeasurements(25.0f, 65.0f, 1013.0f);
        weatherData.setMeasurements(28.0f, 70.0f, 1012.0f);
        weatherData.setMeasurements(22.0f, 60.0f, 1015.0f);

        System.out.println("\n--- Removing ForecastDisplay ---");
        weatherData.removeObserver(forecastDisplay);
        weatherData.setMeasurements(20.0f, 55.0f, 1018.0f);
    }
}

class WeatherData {
    private final java.util.List<Object> observers = new java.util.ArrayList<>();

    public void registerObserver(Object observer) {
        observers.add(observer);
    }

    public void removeObserver(Object observer) {
        observers.remove(observer);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        for (Object observer : observers) {
            try {
                observer.getClass()
                        .getMethod("update", float.class, float.class, float.class)
                        .invoke(observer, temperature, humidity, pressure);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException("Observer must provide a public update method", e);
            }
        }
    }
}

class CurrentConditionsDisplay {
    public void update(float temperature, float humidity, float pressure) {
        System.out.printf("Current conditions: %.1f°C, %.1f%% humidity%n", temperature, humidity);
    }
}

class StatisticsDisplay {
    private float minTemperature = Float.POSITIVE_INFINITY;
    private float maxTemperature = Float.NEGATIVE_INFINITY;
    private float temperatureTotal;
    private int readingCount;

    public void update(float temperature, float humidity, float pressure) {
        minTemperature = Math.min(minTemperature, temperature);
        maxTemperature = Math.max(maxTemperature, temperature);
        temperatureTotal += temperature;
        readingCount++;
        System.out.printf("Temperature stats: avg %.1f°C, max %.1f°C, min %.1f°C%n",
                temperatureTotal / readingCount, maxTemperature, minTemperature);
    }
}

class ForecastDisplay {
    private float lastPressure = Float.NaN;

    public void update(float temperature, float humidity, float pressure) {
        if (Float.isNaN(lastPressure)) {
            System.out.println("Forecast: conditions recorded");
        } else if (pressure > lastPressure) {
            System.out.println("Forecast: improving weather");
        } else if (pressure < lastPressure) {
            System.out.println("Forecast: cooler, rainy weather");
        } else {
            System.out.println("Forecast: no significant change");
        }
        lastPressure = pressure;
    }
}
