import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DialogsAndChooserDemo extends JFrame {
    private final JTextArea outputArea;

    public DialogsAndChooserDemo() {
        setTitle("Week 5 - Dialogs & JFileChooser");
        setSize(560, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        JButton inputDialogBtn = new JButton("Input Dialog");
        JButton confirmDialogBtn = new JButton("Confirm Dialog");
        JButton openFileBtn = new JButton("Choose File");

        inputDialogBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter student name:", "Input", JOptionPane.PLAIN_MESSAGE);
            if (name != null && !name.trim().isEmpty()) {
                outputArea.append("Entered name: " + name.trim() + "\n");
            }
        });

        confirmDialogBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Mark Week 5 activity as reviewed?", "Confirm", JOptionPane.YES_NO_OPTION);
            outputArea.append("Confirm result: " + (choice == JOptionPane.YES_OPTION ? "YES" : "NO") + "\n");
        });

        openFileBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = chooser.getSelectedFile();
                outputArea.append("Selected file: " + selected.getAbsolutePath() + "\n");
                if (selected.isFile() && selected.length() < 8192) {
                    try {
                        outputArea.append("--- Preview ---\n" + Files.readString(selected.toPath()) + "\n---------------\n");
                    } catch (IOException ex) {
                        outputArea.append("Could not read preview: " + ex.getMessage() + "\n");
                    }
                }
            }
        });

        buttonBar.add(inputDialogBtn);
        buttonBar.add(confirmDialogBtn);
        buttonBar.add(openFileBtn);

        add(buttonBar, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DialogsAndChooserDemo().setVisible(true));
    }
}