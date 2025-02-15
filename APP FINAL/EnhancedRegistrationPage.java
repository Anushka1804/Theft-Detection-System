import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnhancedRegistrationPage {
    private JFrame frame;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public EnhancedRegistrationPage() {
        frame = new JFrame("User  Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#8AB6D6"));
        JLabel titleLabel = new JLabel("REGISTER");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#003366"));
        headerPanel.add(titleLabel);

        frame.getContentPane().setBackground(Color.decode("#E8F0FE"));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField = new JTextField();
        customizeField(nameField, "Enter your full name");
        addToPanel(formPanel, nameLabel, nameField, gbc, 0);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField = new JTextField();
        customizeField(emailField, "Enter your email address");
        addToPanel(formPanel, emailLabel, emailField, gbc, 1);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField();
        customizeField(passwordField, "Create a secure password");
        addToPanel(formPanel, passwordLabel, passwordField, gbc, 2);

        // Register button
        registerButton = new JButton("Register");
        customizeButton(registerButton);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Display the data in console (or send it to a backend for processing)
                System.out.println("User  Registration Details:");
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);

                // Show confirmation dialog
                JOptionPane.showMessageDialog(frame, "Registration successful!");

                // Clear fields after submission
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
            }
        });

        // Add header and form panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to add a label and field pair to the panel
    private void addToPanel(JPanel panel, JLabel label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(field, gbc);
        gbc.gridwidth = 1;
    }

    // Method to customize text fields with placeholders
    private void customizeField(JTextField field, String placeholder) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#3E8EDE"), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setPreferredSize(new Dimension(200, 30));
        field.setOpaque(true); // Set opaque to true for background color
        field.setBackground(Color.WHITE);
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        // Clear placeholder text on focus
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#3E8EDE"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#3E8EDE"), 1));
        button.setPreferredSize(new Dimension(180, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#3E8EDE"), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#5599FF"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#3E8EDE"));
            }
        });
    }

    public static void main(String[] args) {
        // Run the application
        new EnhancedRegistrationPage();
    }
}