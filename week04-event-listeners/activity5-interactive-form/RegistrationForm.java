import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {
    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(600, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 6, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(25);
        JTextField emailField = new JTextField(25);
        JPasswordField passwordField = new JPasswordField(25);

        JRadioButton maleRadio = new JRadioButton("Male", true);
        JRadioButton femaleRadio = new JRadioButton("Female");
        JRadioButton otherRadio = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        JCheckBox sportsCheck = new JCheckBox("Sports");
        JCheckBox musicCheck = new JCheckBox("Music");
        JCheckBox readingCheck = new JCheckBox("Reading");
        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);

        JComboBox<String> countryBox = new JComboBox<>(new String[]{
            "Kenya", "Tanzania", "Uganda", "Rwanda"
        });

        JTextArea commentsArea = new JTextArea(3, 25);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);

        JLabel statusLabel = new JLabel("Status: Ready");

        addRow(formPanel, gbc, 0, "Full Name:", nameField);
        addRow(formPanel, gbc, 1, "Email:", emailField);
        addRow(formPanel, gbc, 2, "Password:", passwordField);
        addRow(formPanel, gbc, 3, "Gender:", genderPanel);
        addRow(formPanel, gbc, 4, "Interests:", interestsPanel);
        addRow(formPanel, gbc, 5, "Country:", countryBox);
        addRow(formPanel, gbc, 6, "Comments:", commentsScroll);
        addRow(formPanel, gbc, 7, "Status:", statusLabel);

        add(formPanel, BorderLayout.CENTER);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(46, 125, 50));
        submitButton.setForeground(Color.WHITE);

        JButton clearButton = new JButton("Clear");

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(198, 40, 40));
        exitButton.setForeground(Color.WHITE);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty()) {
                statusLabel.setText("Error: Please enter your name");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (email.isEmpty() || !email.contains("@")) {
                statusLabel.setText("Error: Please enter a valid email");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (password.length() < 6) {
                statusLabel.setText("Error: Password must be at least 6 characters");
                statusLabel.setForeground(Color.RED);
                return;
            }

            String gender = "Not specified";
            if (maleRadio.isSelected()) gender = "Male";
            else if (femaleRadio.isSelected()) gender = "Female";
            else if (otherRadio.isSelected()) gender = "Other";

            StringBuilder interests = new StringBuilder();
            if (sportsCheck.isSelected()) interests.append("Sports ");
            if (musicCheck.isSelected()) interests.append("Music ");
            if (readingCheck.isSelected()) interests.append("Reading ");

            String country = (String) countryBox.getSelectedItem();
            String comments = commentsArea.getText().trim();

            String message = "Registration Successful!\n\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Gender: " + gender + "\n"
                    + "Interests: " + (interests.length() > 0 ? interests.toString().trim() : "None") + "\n"
                    + "Country: " + country + "\n"
                    + "Comments: " + (comments.isEmpty() ? "None" : comments);

            JOptionPane.showMessageDialog(this, message,
                    "Registration Complete", JOptionPane.INFORMATION_MESSAGE);

            statusLabel.setText("Registration submitted successfully!");
            statusLabel.setForeground(new Color(0, 128, 0));
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            genderGroup.clearSelection();
            sportsCheck.setSelected(false);
            musicCheck.setSelected(false);
            readingCheck.setSelected(false);
            countryBox.setSelectedIndex(0);
            commentsArea.setText("");
            statusLabel.setText("Status: Form cleared");
            statusLabel.setForeground(Color.BLACK);
        });

        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}