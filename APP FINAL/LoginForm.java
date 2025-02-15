import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Text;

public class LoginForm {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/app_proj"; // Your database URL
    private static final String DB_USER = "postgres"; // Your database username
    private static final String DB_PASSWORD = "1804"; // Your database password
    private static final String AUTH_QUERY = "SELECT * FROM public.user WHERE email = ? AND password = ?"; // SQL query

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CampusEye Login");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        
        // Set a gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(33, 150, 243), 0, getHeight(), new Color(30, 130, 230));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        frame.add(backgroundPanel);

        JPanel panel = createMainPanel();
        backgroundPanel.add(panel);
        frame.setVisible(true);
    }

    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(300, 500));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon("logo.png")); // Replace with your logo path
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        JLabel welcomeLabel = new JLabel("Welcome to CampusEye", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(33, 150, 243));
        gbc.gridy = 1;
        panel.add(welcomeLabel, gbc);

        JLabel loginLabel = new JLabel("Login to your account", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginLabel.setForeground(new Color(100, 100, 100));
        gbc.gridy = 2;
        panel.add(loginLabel, gbc);

        JTextField emailField = createTextField("Enter your Email");
        gbc.gridy = 3;
        panel.add(emailField, gbc);

        JPasswordField passwordField = createPasswordField("Enter your Password");
        gbc.gridy = 4;
        panel.add(passwordField, gbc);

        // Remember Me Checkbox
        JCheckBox rememberMeCheckBox = new JCheckBox("Remember Me");
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(rememberMeCheckBox, gbc);

        JButton loginButton = createLoginButton(emailField, passwordField);
        gbc.gridy = 6;
        panel.add(loginButton, gbc);

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<html><a href=''>Forgot Password?</a></html>");
        forgotPasswordLabel.setForeground(new Color(33, 150, 243));
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Password recovery options will be sent to your email.");
            }
        });
        gbc.gridy = 7;
        panel.add(forgotPasswordLabel, gbc);

        // Sign Up Link
        JLabel signUpLabel = new JLabel("<html><a href=''>Sign Up</a></html>");
        signUpLabel.setForeground(new Color(33, 150, 243));
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Redirecting to sign-up page...");
            }
        });
        gbc.gridy = 8;
        panel.add(signUpLabel, gbc);

        return panel;
    }

    private static JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        styleTextField(textField);
        setupPlaceholder(textField, placeholder);
        return textField;
    }

    private static JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(placeholder);
        styleTextField(passwordField);
        setupPlaceholder(passwordField, placeholder);
        return passwordField;
    }

    private static void setupPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(new Color(50, 50, 50));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setPreferredSize(new Dimension(200, 35));
        textField.setOpaque(true);
        textField.setBackground(new Color(245, 245, 245));
    }

    private static JButton createLoginButton(JTextField emailField, JPasswordField passwordField) {
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        loginButton.setPreferredSize(new Dimension(200, 40));

        // Rounded corners effect
        loginButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(new Color(30, 130, 230));
                g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);
            }
        });

        // Hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(30, 130, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(33, 150, 243));
            }
        });

        // Login action listener
        loginButton.addActionListener(e -> handleLogin(emailField.getText(), new String(passwordField.getPassword()), loginButton));

        return loginButton;
    }

    private static void handleLogin(String email, String password, Component parentComponent) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(AUTH_QUERY)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Show success message
                    JOptionPane.showMessageDialog(parentComponent,
                        "Login successful! Welcome to CampusEye.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Close the login window
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(parentComponent);
                    frame.dispose();

                    // Navigate to the next page (Text.java)
                    TheftLocationForm newPage = new TheftLocationForm(); // Instantiate and display Text.java page
                    newPage.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "Invalid email or password");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Database connection error: " + ex.getMessage());
        }
    }
}
