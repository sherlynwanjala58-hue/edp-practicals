import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PreferencesApp extends JFrame {
    private static final Path PROPS_FILE = Path.of("app_prefs.properties");
    private final JTextField studentNameField;
    private final JCheckBox autoSaveCheck;
    private final JComboBox<String> themeBox;
    private final JLabel statusLabel;

    public PreferencesApp() {
        setTitle("Week 9 - Preferences Persistence");
        setSize(460, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 8, 8));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        studentNameField = new JTextField(20);
        autoSaveCheck = new JCheckBox("Enable reminder banner");
        themeBox = new JComboBox<>(new String[]{"Standard", "Compact", "High Contrast"});
        statusLabel = new JLabel("Ready");

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Student Label:"));
        row1.add(studentNameField);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("View Mode:"));
        row2.add(themeBox);
        row2.add(autoSaveCheck);

        JButton savePrefBtn = new JButton("Save Preferences");
        savePrefBtn.addActionListener(e -> savePreferences());

        add(row1);
        add(row2);
        add(savePrefBtn);
        add(statusLabel);

        loadPreferences();
    }

    private void savePreferences() {
        Properties props = new Properties();
        props.setProperty("studentLabel", studentNameField.getText().trim());
        props.setProperty("viewMode", (String) themeBox.getSelectedItem());
        props.setProperty("reminderEnabled", String.valueOf(autoSaveCheck.isSelected()));

        try (OutputStream out = Files.newOutputStream(PROPS_FILE)) {
            props.store(out, "Week 9 Swing Preferences");
            statusLabel.setText("Saved preferences to " + PROPS_FILE.getFileName());
        } catch (IOException ex) {
            statusLabel.setText("Failed to save preferences: " + ex.getMessage());
        }
    }

    private void loadPreferences() {
        if (!Files.exists(PROPS_FILE)) {
            return;
        }
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(PROPS_FILE)) {
            props.load(in);
            studentNameField.setText(props.getProperty("studentLabel", ""));
            themeBox.setSelectedItem(props.getProperty("viewMode", "Standard"));
            autoSaveCheck.setSelected(Boolean.parseBoolean(props.getProperty("reminderEnabled", "false")));
            statusLabel.setText("Loaded saved preferences from " + PROPS_FILE.getFileName());
        } catch (IOException ex) {
            statusLabel.setText("Could not read preferences: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreferencesApp().setVisible(true));
    }
}