import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskBoardApp extends JFrame {
    @FunctionalInterface
    interface TaskChangeListener {
        void onTasksChanged(List<String> updatedTasks);
    }

    private final List<String> tasks = new ArrayList<>();
    private final List<TaskChangeListener> changeListeners = new ArrayList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JLabel countLabel;

    public TaskBoardApp() {
        setTitle("Week 8 - Decoupled Event-Driven Task Board");
        setSize(500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField taskInput = new JTextField();
        JButton addBtn = new JButton("Add Task");
        JButton removeBtn = new JButton("Remove Selected");

        JList<String> taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 14));

        countLabel = new JLabel("Total tasks: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 13));

        // Observers reacting automatically to state changes
        addTaskChangeListener(updated -> {
            listModel.clear();
            for (String item : updated) {
                listModel.addElement(item);
            }
        });

        addTaskChangeListener(updated -> countLabel.setText("Total tasks: " + updated.size()));

        addBtn.addActionListener(e -> {
            String text = taskInput.getText().trim();
            if (!text.isEmpty()) {
                tasks.add(text);
                taskInput.setText("");
                notifyTaskListeners();
            }
        });

        removeBtn.addActionListener(e -> {
            int idx = taskList.getSelectedIndex();
            if (idx >= 0) {
                tasks.remove(idx);
                notifyTaskListeners();
            }
        });

        JPanel topBar = new JPanel(new BorderLayout(6, 6));
        topBar.add(new JLabel("Task:"), BorderLayout.WEST);
        topBar.add(taskInput, BorderLayout.CENTER);
        topBar.add(addBtn, BorderLayout.EAST);

        JPanel bottomBar = new JPanel(new BorderLayout(8, 8));
        bottomBar.add(countLabel, BorderLayout.WEST);
        bottomBar.add(removeBtn, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void addTaskChangeListener(TaskChangeListener listener) {
        changeListeners.add(listener);
    }

    private void notifyTaskListeners() {
        List<String> snapshot = List.copyOf(tasks);
        for (TaskChangeListener listener : changeListeners) {
            listener.onTasksChanged(snapshot);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskBoardApp().setVisible(true));
    }
}