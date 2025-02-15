import javax.swing.*;
import java.awt.*;

public class ThiefDescriptionBox extends JFrame {
    private JTextField heightField;
    private JTextField weightField;
    private JTextField hairColorField;
    private JTextField eyeColorField;
    private JTextField distinguishingFeaturesField;

    public ThiefDescriptionBox() {
        createGUI();
    }

    private void createGUI() {
        setTitle("Thief's Appearance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(41, 128, 185), 0, getHeight(), new Color(109, 213, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Title label
        JLabel titleLabel = new JLabel("Describe the Thief's Appearance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Input fields with card-like layout
        heightField = createTextField("Enter height");
        weightField = createTextField("Enter weight");
        hairColorField = createTextField("Enter hair color");
        eyeColorField = createTextField("Enter eye color");
        distinguishingFeaturesField = createTextField("Enter distinguishing features");

        addFieldCard(mainPanel, "Height:", heightField, gbc, 1);
        addFieldCard(mainPanel, "Weight:", weightField, gbc, 2);
        addFieldCard(mainPanel, "Hair Color:", hairColorField, gbc, 3);
        addFieldCard(mainPanel, "Eye Color:", eyeColorField, gbc, 4);
        addFieldCard(mainPanel, "Distinguishing Features:", distinguishingFeaturesField, gbc, 5);

        // Submit button with style and hover effects
        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);
        submitButton.addActionListener(e -> gotopage());

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(submitButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    // Method to create placeholder-style text fields
    private JTextField createTextField(String placeholderText) {
        JTextField textField = new JTextField(placeholderText);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Placeholder text effect
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
        
        return textField;
    }

    // Adds a field label and text input inside a styled card
    private void addFieldCard(JPanel mainPanel, String labelText, JTextField textField, GridBagConstraints gbc, int y) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(new Color(255, 255, 255, 150));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(169, 169, 169), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(41, 128, 185));

        cardPanel.add(label, BorderLayout.NORTH);
        cardPanel.add(textField, BorderLayout.CENTER);

        gbc.gridy = y;
        gbc.gridwidth = 2;
        mainPanel.add(cardPanel, gbc);
    }

    // Styles the submit button with hover effects
    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(31, 97, 141));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185));
            }
        });
    }

    public void gotopage() {
        EventQueue.invokeLater(() -> {
            TheftLocationForm t = new TheftLocationForm();
            t.setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        new ThiefDescriptionBox();
    }
}
