
// Import necessary classes for GUI, events, file handling, and regular expressions
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.regex.Pattern;

// Main class for the Veterinary Clinic application, extending JFrame for GUI window
public class VeterinaryClinic extends JFrame {
    // Declare GUI components as private instance variables
    private JTextField patientNameField, ownerNameField, emailField;
    private JRadioButton vet1RadioButton, vet2RadioButton, vet3RadioButton;
    private ButtonGroup vetGroup; // Button group to group radio buttons
    private JLabel messageLabel; // Label to display messages such as validation results

    // Constructor to set up the GUI window and layout
    public VeterinaryClinic() {
        // Set window properties
        setTitle("Veterinary Clinic - Patient Registration");
        setSize(400, 300); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when window is closed
        setLocationRelativeTo(null); // Center the window on the screen

        // Set the main panel layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); // BorderLayout for adding different panels
        mainPanel.setBackground(Color.WHITE); // Set background color of the main panel

        // Create a title panel for the header section
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(28, 48, 88)); // Set background color for title panel
        JLabel titleLabel = new JLabel("Patient Registration Form", JLabel.CENTER); // Create title label
        titleLabel.setForeground(Color.WHITE); // Set text color for title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font properties for title label
        titlePanel.add(titleLabel); // Add title label to the title panel

        // Create an input panel for the registration form fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10)); // GridLayout with 6 rows and 2 columns
        inputPanel.setBackground(Color.WHITE); // Set background color for input panel

        // Add input fields for patient name, owner name, email, and veterinarian selection
        inputPanel.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField(); // Create a text field for patient name input
        inputPanel.add(patientNameField);

        inputPanel.add(new JLabel("Owner Name:"));
        ownerNameField = new JTextField(); // Create a text field for owner name input
        inputPanel.add(ownerNameField);

        inputPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField(); // Create a text field for email input
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Select Veterinarian:"));
        // Create radio buttons for selecting a veterinarian
        vet1RadioButton = new JRadioButton("Dr. Smith");
        vet2RadioButton = new JRadioButton("Dr. Jones");
        vet3RadioButton = new JRadioButton("Dr. Taylor");

        // Group the radio buttons together so that only one can be selected at a time
        vetGroup = new ButtonGroup();
        vetGroup.add(vet1RadioButton);
        vetGroup.add(vet2RadioButton);
        vetGroup.add(vet3RadioButton);

        // Add radio buttons to the input panel
        inputPanel.add(vet1RadioButton);
        inputPanel.add(vet2RadioButton);
        inputPanel.add(vet3RadioButton);

        // Set the default selected veterinarian to Dr. Smith
        vet1RadioButton.setSelected(true);

        // Message label for displaying status messages
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setForeground(new Color(255, 0, 0)); // Set text color to red for error messages

        // Button panel for action buttons (Register, Clear, Exit)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered buttons with spacing
        buttonPanel.setBackground(Color.WHITE); // Set background color for button panel

        // Create buttons and add action listeners for user actions
        JButton registerButton = new JButton("Register");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        registerButton.addActionListener(new RegisterButtonListener());
        clearButton.addActionListener(e -> clearForm()); // Clear the form when the clear button is clicked
        exitButton.addActionListener(e -> System.exit(0)); // Exit the application when the exit button is clicked

        // Add buttons to the button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        // Add panels to the main frame
        mainPanel.add(titlePanel, BorderLayout.NORTH); // Title panel at the top
        mainPanel.add(inputPanel, BorderLayout.CENTER); // Input panel in the center
        mainPanel.add(messageLabel, BorderLayout.SOUTH); // Message label at the bottom
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Button panel at the bottom

        // Add the main panel to the frame
        add(mainPanel);

        // Apply padding to the content pane for better spacing
        getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    // Validate input fields (patient name, owner name, email)
    private boolean validateInputs() {
        String patientName = patientNameField.getText();
        String ownerName = ownerNameField.getText();
        String email = emailField.getText();

        // Check if patient name is empty
        if (patientName.isEmpty()) {
            messageLabel.setText("Patient Name is required.");
            return false;
        }
        // Check if owner name is empty
        if (ownerName.isEmpty()) {
            messageLabel.setText("Owner Name is required.");
            return false;
        }
        // Check if email is empty or invalid
        if (email.isEmpty() || !isValidEmail(email)) {
            messageLabel.setText("Invalid Email Address.");
            return false;
        }
        return true; // Return true if all inputs are valid
    }

    // Validate email format using a regular expression
    private boolean isValidEmail(String email) {
        String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regexPattern);
        return pattern.matcher(email).matches(); // Return true if email matches the pattern
    }

    // Clear the input form fields
    private void clearForm() {
        patientNameField.setText("");
        ownerNameField.setText("");
        emailField.setText("");
        messageLabel.setText(""); // Clear the message label
        vet1RadioButton.setSelected(true); // Reset the veterinarian selection to Dr. Smith
    }

    // Inner class for the action listener of the Register button
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!validateInputs()) {
                return;  // Stop execution if validation fails
            }

            // Get the input values from the form fields
            String patientName = patientNameField.getText();
            String ownerName = ownerNameField.getText();
            String email = emailField.getText();
            String selectedVet = vet1RadioButton.isSelected() ? "Dr. Smith" :
                    vet2RadioButton.isSelected() ? "Dr. Jones" : "Dr. Taylor";

            // Create a new Patient object with the input values
            Patient patient = new Patient(patientName, ownerName, email, selectedVet);

            // Open a file chooser to allow the user to save the patient information to a file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null); // Show the save dialog
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile(); // Get the selected file
                // Attempt to write patient data to the selected file using FileHandler
                if (FileHandler.writeToFile(file, patient)) {
                    messageLabel.setText("Patient registered successfully!"); // Success message
                } else {
                    messageLabel.setText("Error writing to file."); // Error message if writing fails
                }
            }
        }
    }

    // Main method to launch the Veterinary Clinic application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VeterinaryClinic clinic = new VeterinaryClinic(); // Create instance of VeterinaryClinic
            clinic.setVisible(true); // Make the window visible
        });
    }
}
