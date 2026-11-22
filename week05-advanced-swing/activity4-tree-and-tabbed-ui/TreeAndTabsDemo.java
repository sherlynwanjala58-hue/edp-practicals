import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class TreeAndTabsDemo extends JFrame {
    public TreeAndTabsDemo() {
        setTitle("Week 5 - JTree & JTabbedPane");
        setSize(620, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("event practicles");
        DefaultMutableTreeNode w1 = new DefaultMutableTreeNode("Week1_EDP");
        w1.add(new DefaultMutableTreeNode("BugFixChallenge"));
        w1.add(new DefaultMutableTreeNode("eventdriven"));
        w1.add(new DefaultMutableTreeNode("procedural"));

        DefaultMutableTreeNode w2 = new DefaultMutableTreeNode("week02-observer-pattern");
        w2.add(new DefaultMutableTreeNode("activity1-weather-station"));

        DefaultMutableTreeNode w4 = new DefaultMutableTreeNode("week04-event-handling");
        w4.add(new DefaultMutableTreeNode("activity4-counter-app"));
        w4.add(new DefaultMutableTreeNode("activity7-mouse-and-key"));

        root.add(w1);
        root.add(w2);
        root.add(w4);

        JTree folderTree = new JTree(root);
        JLabel selectedNodeLabel = new JLabel("Selected node: event practicles");

        JPanel explorerTab = new JPanel(new BorderLayout(8, 8));
        explorerTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        explorerTab.add(new JScrollPane(folderTree), BorderLayout.CENTER);
        explorerTab.add(selectedNodeLabel, BorderLayout.SOUTH);

        folderTree.addTreeSelectionListener(e -> {
            Object lastPath = e.getPath().getLastPathComponent();
            selectedNodeLabel.setText("Selected node: " + lastPath);
        });

        JTextArea notesArea = new JTextArea(
                "Week 5 Key Takeaways:\n"
                + "- DefaultListModel & DefaultTableModel separate data from UI display.\n"
                + "- JMenuBar, JToolBar, and KeyStroke accelerators make apps easier to navigate.\n"
                + "- JOptionPane and JFileChooser handle standard prompts and file selection.\n"
                + "- JTree and JTabbedPane organize hierarchical or multi-section views."
        );
        notesArea.setEditable(false);
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Project Tree", explorerTab);
        tabs.addTab("Summary Notes", new JScrollPane(notesArea));

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TreeAndTabsDemo().setVisible(true));
    }
}