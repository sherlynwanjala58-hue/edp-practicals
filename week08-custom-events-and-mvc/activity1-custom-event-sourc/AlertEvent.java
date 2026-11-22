import java.util.EventObject;

public class AlertEvent extends EventObject {
    private final String message;
    private final String severity;

    public AlertEvent(Object source, String message, String severity) {
        super(source);
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }
}