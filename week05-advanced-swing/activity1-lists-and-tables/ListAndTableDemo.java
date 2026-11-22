import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListAndTableDemo extends JFrame {
    public ListAndTableDemo() {
        setTitle("Week 5 - JList & JTable Demo");
        setSize(680, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left: JList with DefaultListModel
        DefaultListModel<String> courseModel = new DefaultListModel<>();
        courseModel.addElement("Event Programming");
        courseModel.addElement("Web Development");
        courseModel.addElement("Financial Accounting");
        courseModel.addElement("Business Law");

        JList<String> courseList = new JList<>(courseModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Units"));
        leftPanel.setPreferredSize(new Dimension(190, 0));
        leftPanel.add(new JScrollPane(courseList), BorderLayout.CENTER);

        JTextField newUnitField = new JTextField();
        JButton addUnitBtn = new JButton("Add Unit");
        addUnitBtn.addActionListener(e -> {
            String unit = newUnitField.getText().trim();
            if (!unit.isEmpty()) {
                courseModel.addElement(unit);
                newUnitField.setText("");
            }
        });
        JPanel addUnitPanel = new JPanel(new BorderLayout(4, 4));
        addUnitPanel.add(newUnitField, BorderLayout.CENTER);
        addUnitPanel.add(addUnitBtn, BorderLayout.EAST);
        leftPanel.add(addUnitPanel, BorderLayout.SOUTH);

        // Center: JTable with DefaultTableModel
        String[] columns = {"Practical", "Status", "Score (%)"};
        Object[][] rows = {
            {"Week 1 - Intro & Calculator", "Completed", 95},
            {"Week 2 - Observer Pattern", "In Progress", 88},
            {"Week 3 - Swing GUI Basics", "Completed", 92},
            {"Week 4 - Event Listeners", "Completed", 94}
        };

        DefaultTableModel tableModel = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Keep practical name read-only
            }
        };

        JTable gradesTable = new JTable(tableModel);
        gradesTable.setRowHeight(24);
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Practical Tracker"));
        centerPanel.add(new JScrollPane(gradesTable), BorderLayout.CENTER);

        JButton addRowBtn = new JButton("Add Week 5 Row");
        addRowBtn.addActionListener(e -> tableModel.addRow(new Object[]{"Week 5 - Advanced Swing", "In Progress", 90}));
        centerPanel.add(addRowBtn, BorderLayout.SOUTH);

        JLabel statusLabel = new JLabel("Select a unit or edit a table row.");
        courseList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && courseList.getSelectedValue() != null) {
                statusLabel.setText("Selected Unit: " + courseList.getSelectedValue());
            }
        });

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListAndTableDemo().setVisible(true));
    }
}