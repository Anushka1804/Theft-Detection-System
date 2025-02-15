import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheftLocationForm extends JFrame {

    private JTextField locationField, notesField;
    private JButton submitButton;

    public TheftLocationForm() {
        setTitle("Report Theft Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
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
        gbc.insets = new Insets(15, 0, 15, 0);

        // Title label
        JLabel titleLabel = new JLabel("Theft Location Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Location and Notes input fields with card-style layout
        locationField = createTextField("Enter location of theft");
        notesField = createTextField("Additional notes (optional)");

        addCardPanel(mainPanel, "Location of Theft:", locationField, gbc, 1);
        addCardPanel(mainPanel, "Additional Notes:", notesField, gbc, 2);

        // Submit button with shadow and hover effects
        submitButton = new JButton("Submit Location Report");
        styleButton(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = locationField.getText();
                String notes = notesField.getText();
                System.out.println("Theft Location Report Submitted:");
                System.out.println("Location of Theft: " + location);
                System.out.println("Additional Notes: " + notes);
                JOptionPane.showMessageDialog(TheftLocationForm.this, "Theft location report submitted successfully!");

                // Show the next form
                Thieflatestlocation va = new Thieflatestlocation();
                va.setVisible(true);
                dispose();  // Close the current form
            }
        });
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(submitButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    // Method to create placeholder-styled text fields
    private JTextField createTextField(String placeholderText) {
        JTextField textField = new JTextField(placeholderText);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Add focus listener to manage placeholder text
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

    // Method to add card-like panels for each field
    private void addCardPanel(JPanel mainPanel, String labelText, JTextField textField, GridBagConstraints gbc, int y) {
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

    // Style the button with hover and shadow effects
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

    public static void main(String[] args) {
        new TheftLocationForm();
    }
}
