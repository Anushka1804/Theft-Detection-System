import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Thieflatestlocation {

    // Main JFrame
    private JFrame frame;

    // Components for the form
    private JTextField locationField, timeField, detailsField;
    private JButton submitButton;

    public Thieflatestlocation() {
        // Set up the frame with a modern, minimalist design
        frame = new JFrame("Report Latest Sighting of Suspected Thief");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);  // Center the window

        // Set a custom gradient background panel
        GradientPanel backgroundPanel = new GradientPanel();
        backgroundPanel.setLayout(new BorderLayout(20, 20));

        // Panel to hold form elements
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 20));
        formPanel.setBackground(new Color(255, 255, 255, 220));  // Semi-transparent white background
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Labels with a modern font
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        JLabel locationLabel = new JLabel("Latest Location:");
        locationLabel.setFont(labelFont);
        locationField = new JTextField();
        locationField.setToolTipText("Enter the location where the thief was last seen.");
        locationField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel timeLabel = new JLabel("Time of Sighting:");
        timeLabel.setFont(labelFont);
        timeField = new JTextField();
        timeField.setToolTipText("Enter the time when the sighting occurred.");
        timeField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel detailsLabel = new JLabel("Additional Details:");
        detailsLabel.setFont(labelFont);
        detailsField = new JTextField();
        detailsField.setToolTipText("Enter any additional information you have about the sighting.");
        detailsField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Submit Button with a modern design
        submitButton = new JButton("Submit Sighting Report");
        submitButton.setBackground(new Color(0, 123, 255));  // Blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setPreferredSize(new Dimension(250, 40));
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));
        submitButton.setToolTipText("Click to submit the sighting report");
        submitButton.setOpaque(true); // Make the button background solid
        submitButton.setRolloverEnabled(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input data from form fields
                String location = locationField.getText();
                String time = timeField.getText();
                String details = detailsField.getText();

                // Process the data (for example, send to backend or print to console)
                System.out.println("New Suspected Thief Sighting Report:");
                System.out.println("Latest Location: " + location);
                System.out.println("Time of Sighting: " + time);
                System.out.println("Additional Details: " + details);

                // Show confirmation dialog
                JOptionPane.showMessageDialog(frame, "Sighting report submitted successfully!",
                        "Submission Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the fields after submission
                locationField.setText("");
                timeField.setText("");
                detailsField.setText("");
            }
        });

        // Add components to the form panel
        formPanel.add(locationLabel);
        formPanel.add(locationField);

        formPanel.add(timeLabel);
        formPanel.add(timeField);

        formPanel.add(detailsLabel);
        formPanel.add(detailsField);

        // Empty label for spacing between form fields and submit button
        formPanel.add(new JLabel(""));

        // Create a bottom panel for the submit button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);  // Transparent to let the gradient show through
        bottomPanel.add(submitButton);

        // Add form panel and bottom panel to the main frame
        backgroundPanel.add(formPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Apply some animations for the submit button when hovered over
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(0, 150, 255));  // Lighter blue when hovered
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(0, 123, 255));  // Original blue when not hovered
            }
        });

        // Set the main content pane and display the frame
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    // Custom JPanel class for gradient background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(0, 123, 255);   // Start color
            Color color2 = new Color(0, 200, 255);   // End color
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Thieflatestlocation();
            }
        });
    }
}