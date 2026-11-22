import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class NotesSaverApp extends JFrame {
    private static final Path NOTES_FILE = Path.of("weekly_notes.txt");
    private final JTextArea notesArea;
    private final JLabel statusLabel;

    public NotesSaverApp() {
        setTitle("Week 9 - Event-Driven File Save & Load");
        setSize(540, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        notesArea = new JTextArea();
        notesArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        statusLabel = new JLabel("Target file: " + NOTES_FILE.toAbsolutePath());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton saveBtn = new JButton("Save Notes");
        JButton loadBtn = new JButton("Load Notes");
        JButton clearBtn = new JButton("Clear");

        saveBtn.addActionListener(e -> saveNotes());
        loadBtn.addActionListener(e -> loadNotes());
        clearBtn.addActionListener(e -> {
            notesArea.setText("");
            statusLabel.setText("Cleared editor area (disk file unchanged).");
        });

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        topBar.add(saveBtn);
        topBar.add(loadBtn);
        topBar.add(clearBtn);

        add(topBar, BorderLayout.NORTH);
        add(new JScrollPane(notesArea), BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        loadNotes();
    }

    private void saveNotes() {
        try {
            Files.writeString(NOTES_FILE, notesArea.getText(), StandardCharsets.UTF_8);
            statusLabel.setText("Saved successfully to " + NOTES_FILE.getFileName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(),
                    "I/O Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Save failed.");
        }
    }

    private void loadNotes() {
        if (!Files.exists(NOTES_FILE)) {
            statusLabel.setText("No saved file yet. Click Save Notes to create " + NOTES_FILE.getFileName());
            return;
        }
        try {
            String content = Files.readString(NOTES_FILE, StandardCharsets.UTF_8);
            notesArea.setText(content);
            statusLabel.setText("Loaded " + NOTES_FILE.getFileName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage(),
                    "I/O Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Load failed.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotesSaverApp().setVisible(true));
    }
}