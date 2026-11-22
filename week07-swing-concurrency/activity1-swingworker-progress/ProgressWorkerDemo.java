import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProgressWorkerDemo extends JFrame {
    private final JProgressBar progressBar;
    private final JTextArea logArea;
    private final JButton startButton;

    public ProgressWorkerDemo() {
        setTitle("Week 7 - SwingWorker Progress Demo");
        setSize(520, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        startButton = new JButton("Start Batch Task");
        startButton.addActionListener(e -> runBackgroundTask());

        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        topPanel.add(progressBar, BorderLayout.CENTER);
        topPanel.add(startButton, BorderLayout.EAST);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    private void runBackgroundTask() {
        startButton.setEnabled(false);
        progressBar.setValue(0);
        logArea.setText("");

        SwingWorker<String, String> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                int totalSteps = 10;
                for (int i = 1; i <= totalSteps; i++) {
                    Thread.sleep(350); // Simulates slow I/O or computation off the EDT
                    int percent = (i * 100) / totalSteps;
                    setProgress(percent);
                    publish("Completed step " + i + " of " + totalSteps + " (" + percent + "%)");
                }
                return "Batch task finished cleanly!";
            }

            @Override
            protected void process(List<String> chunks) {
                for (String msg : chunks) {
                    logArea.append(msg + "\n");
                }
            }

            @Override
            protected void done() {
                try {
                    String summary = get();
                    logArea.append("\nRESULT: " + summary + "\n");
                } catch (Exception ex) {
                    logArea.append("\nERROR: " + ex.getMessage() + "\n");
                } finally {
                    startButton.setEnabled(true);
                }
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgressWorkerDemo().setVisible(true));
    }
}