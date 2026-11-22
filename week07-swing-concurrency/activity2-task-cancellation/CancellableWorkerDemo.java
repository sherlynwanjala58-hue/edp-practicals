import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CancellableWorkerDemo extends JFrame {
    private final JButton startBtn;
    private final JButton cancelBtn;
    private final JProgressBar bar;
    private final JLabel statusLabel;
    private SwingWorker<Integer, Integer> currentWorker;

    public CancellableWorkerDemo() {
        setTitle("Week 7 - Cancellable SwingWorker");
        setSize(480, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 8, 8));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        statusLabel = new JLabel("Status: Idle", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

        bar = new JProgressBar(0, 20);
        bar.setStringPainted(true);

        startBtn = new JButton("Start");
        cancelBtn = new JButton("Cancel");
        cancelBtn.setEnabled(false);

        startBtn.addActionListener(e -> startJob());
        cancelBtn.addActionListener(e -> {
            if (currentWorker != null && !currentWorker.isDone()) {
                currentWorker.cancel(true);
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        btnPanel.add(startBtn);
        btnPanel.add(cancelBtn);

        add(statusLabel);
        add(bar);
        add(btnPanel);
        add(new JLabel("Tip: Click Cancel while running to stop cleanly.", SwingConstants.CENTER));
    }

    private void startJob() {
        startBtn.setEnabled(false);
        cancelBtn.setEnabled(true);
        bar.setValue(0);
        statusLabel.setText("Status: Running...");

        currentWorker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                int processed = 0;
                for (int i = 1; i <= 20; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    Thread.sleep(250);
                    processed = i;
                    publish(i);
                }
                return processed;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latest = chunks.get(chunks.size() - 1);
                bar.setValue(latest);
                statusLabel.setText("Status: Processing item " + latest + " / 20");
            }

            @Override
            protected void done() {
                try {
                    if (isCancelled()) {
                        statusLabel.setText("Status: Cancelled at " + bar.getValue() + " / 20");
                    } else {
                        int total = get();
                        statusLabel.setText("Status: Completed (" + total + " / 20)");
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Status: Interrupted");
                } finally {
                    startBtn.setEnabled(true);
                    cancelBtn.setEnabled(false);
                }
            }
        };

        currentWorker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CancellableWorkerDemo().setVisible(true));
    }
}