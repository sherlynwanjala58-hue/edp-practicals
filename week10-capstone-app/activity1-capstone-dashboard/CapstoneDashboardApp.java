import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CapstoneDashboardApp extends JFrame {
    private final DefaultTableModel tableModel;
    private final JTextField taskInput;
    private final JComboBox<String> priorityBox;
    private final JLabel statusLabel;
    private final JProgressBar progressBar;
    private final JButton runCheckBtn;

    public CapstoneDashboardApp() {
        setTitle("Week 10 - Event Programming Capstone Dashboard");
        setSize(720, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top input bar
        taskInput = new JTextField(24);
        priorityBox = new JComboBox<>(new String[]{"Normal", "High", "Urgent"});
        JButton addBtn = new JButton("Add Item");
        JButton removeBtn = new JButton("Remove Selected");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        topPanel.add(new JLabel("Task / Milestone:"));
        topPanel.add(taskInput);
        topPanel.add(priorityBox);
        topPanel.add(addBtn);
        topPanel.add(removeBtn);

        // Center table
        String[] cols = {"Task / Milestone", "Priority", "State"};
        Object[][] initialRows = {
            {"Observer & Swing Listeners", "High", "Ready"},
            {"Graphics2D & SwingWorker Concurrency", "High", "Ready"},
            {"Repository Cleanup & Git Push", "Urgent", "Pending"}
        };
        tableModel = new DefaultTableModel(initialRows, cols);
        statusLabel = new JLabel("Total items: " + tableModel.getRowCount());
        JTable table = new JTable(tableModel);
        table.setRowHeight(24);

        JTable targetTable = table;
        addBtn.addActionListener(e -> {
            String title = taskInput.getText().trim();
            if (!title.isEmpty()) {
                tableModel.addRow(new Object[]{title, priorityBox.getSelectedItem(), "Ready"});
                taskInput.setText("");
                statusLabel.setText("Added: " + title);
            }
        });

        removeBtn.addActionListener(e -> {
            int row = targetTable.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
                statusLabel.setText("Removed selected row.");
            }
        });

        // Bottom validation runner (SwingWorker) + status
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        runCheckBtn = new JButton("Run Readiness Check");
        runCheckBtn.addActionListener(e -> runReadinessCheck());

        JPanel actionRow = new JPanel(new BorderLayout(8, 6));
        actionRow.add(progressBar, BorderLayout.CENTER);
        actionRow.add(runCheckBtn, BorderLayout.EAST);

        JPanel southPanel = new JPanel(new BorderLayout(4, 4));
        southPanel.add(actionRow, BorderLayout.NORTH);
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void runReadinessCheck() {
        runCheckBtn.setEnabled(false);
        progressBar.setValue(0);
        statusLabel.setText("Checking milestones...");

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(150);
                    publish(i * 10);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latest = chunks.get(chunks.size() - 1);
                progressBar.setValue(latest);
                statusLabel.setText("Readiness progress: " + latest + "%");
            }

            @Override
            protected void done() {
                for (int r = 0; r < tableModel.getRowCount(); r++) {
                    tableModel.setValueAt("Verified", r, 2);
                }
                runCheckBtn.setEnabled(true);
                statusLabel.setText("All " + tableModel.getRowCount() + " items marked Verified.");
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CapstoneDashboardApp().setVisible(true));
    }
}