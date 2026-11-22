import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuAndToolbarDemo extends JFrame {
    private final JTextArea editorArea;
    private final JLabel statusLabel;

    public MenuAndToolbarDemo() {
        setTitle("Week 5 - Menus & Toolbar");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        editorArea = new JTextArea();
        editorArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        editorArea.setLineWrap(true);
        editorArea.setWrapStyleWord(true);

        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        // Toolbar
        JToolBar toolBar = new JToolBar("Main Toolbar");
        JButton newBtn = new JButton("New");
        JButton clearBtn = new JButton("Clear");
        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem("Word Wrap", true);

        newBtn.addActionListener(e -> {
            editorArea.setText("New session started.\n");
            statusLabel.setText("Created new note");
        });

        clearBtn.addActionListener(e -> {
            editorArea.setText("");
            statusLabel.setText("Editor cleared");
        });

        wordWrapItem.addActionListener(e -> {
            boolean wrap = wordWrapItem.isSelected();
            editorArea.setLineWrap(wrap);
            editorArea.setWrapStyleWord(wrap);
            statusLabel.setText("Word wrap: " + (wrap ? "ON" : "OFF"));
        });

        toolBar.add(newBtn);
        toolBar.add(clearBtn);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(e -> newBtn.doClick());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> dispose());

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("View");
        viewMenu.add(wordWrapItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(editorArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuAndToolbarDemo().setVisible(true));
    }
}