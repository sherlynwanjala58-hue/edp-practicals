import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class LegacyDisplay implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o != null) {
            try {
                Object temperature = o.getClass().getMethod("getTemperature").invoke(o);
                Object humidity = o.getClass().getMethod("getHumidity").invoke(o);
                System.out.println("Legacy Display -> Temp: " + temperature + "°C, Humidity: " + humidity + "%");
            } catch (ReflectiveOperationException e) {
                // This observable does not provide weather readings.
            }
        }
    }
}