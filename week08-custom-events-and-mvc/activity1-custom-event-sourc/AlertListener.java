@FunctionalInterface
public interface AlertListener {
    void onAlertRaised(AlertEvent event);
}

class AlertEvent extends java.util.EventObject {
    AlertEvent(Object source) {
        super(source);
    }
}