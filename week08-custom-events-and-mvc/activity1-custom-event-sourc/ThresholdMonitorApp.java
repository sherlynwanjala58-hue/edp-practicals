import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

interface AlertListener {
    void onAlertRaised(AlertEvent event);
}

class AlertEvent {
    private final Object source;
    private final String message;
    private final String severity;

    AlertEvent(Object source, String message, String severity) {
        this.source = source;
        this.message = message;
        this.severity = severity;
    }

    public Object getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }
}

public class ThresholdMonitorApp extends JFrame {
    private final List<AlertListener> listeners = new ArrayList<>();
    private final JSlider readingSlider;
    private final JLabel readingLabel;
    private final JTextArea alertLogArea;

    public ThresholdMonitorApp() {
        setTitle("Week 8 - Custom AlertEvent & Listener");
        setSize(520, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        readingLabel = new JLabel("Sensor Reading: 25°C", SwingConstants.CENTER);
        readingLabel.setFont(new Font("Arial", Font.BOLD, 16));

        readingSlider = new JSlider(0, 100, 25);
        readingSlider.setMajorTickSpacing(25);
        readingSlider.setMinorTickSpacing(5);
        readingSlider.setPaintTicks(true);
        readingSlider.setPaintLabels(true);

        JButton triggerBtn = new JButton("Check Threshold");
        triggerBtn.addActionListener(e -> evaluateReading(readingSlider.getValue()));

        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        topPanel.add(readingLabel, BorderLayout.NORTH);
        topPanel.add(readingSlider, BorderLayout.CENTER);
        topPanel.add(triggerBtn, BorderLayout.SOUTH);

        alertLogArea = new JTextArea();
        alertLogArea.setEditable(false);
        alertLogArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        readingSlider.addChangeListener(e -> {
            int val = readingSlider.getValue();
            readingLabel.setText("Sensor Reading: " + val + "°C");
            if (!readingSlider.getValueIsAdjusting() && val >= 70) {
                evaluateReading(val);
            }
        });

        // Register custom event listeners
        addAlertListener(event -> alertLogArea.append(
                "[" + event.getSeverity() + "] " + event.getMessage() + "\n"
        ));

        addAlertListener(event -> {
            if ("CRITICAL".equals(event.getSeverity())) {
                Toolkit.getDefaultToolkit().beep();
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(alertLogArea), BorderLayout.CENTER);
    }

    public void addAlertListener(AlertListener listener) {
        listeners.add(listener);
    }

    private void evaluateReading(int value) {
        if (value >= 85) {
            fireAlert(new AlertEvent(this, "High temperature spike at " + value + "°C!", "CRITICAL"));
        } else if (value >= 70) {
            fireAlert(new AlertEvent(this, "Warm reading detected at " + value + "°C.", "WARNING"));
        } else {
            alertLogArea.append("[OK] Reading " + value + "°C is within normal range.\n");
        }
    }

    private void fireAlert(AlertEvent event) {
        for (AlertListener listener : new ArrayList<>(listeners)) {
            listener.onAlertRaised(event);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThresholdMonitorApp().setVisible(true));
    }
}