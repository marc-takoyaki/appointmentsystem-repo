package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManagementWindow extends JFrame {

    private JComboBox<String> patientComboBox;
    private JTextField ageField;
    private JTextField contactInfoField;
    private JTextField contactAddressField;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> dentalCareComboBox;
    private JButton saveButton;
    private JButton mainMenuButton;

    private List<String> initialPatients; // List of initially appointed patients

    public PatientManagementWindow(List<String> initialPatients) {
        this.initialPatients = initialPatients;

        setTitle("Patient Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel patientLabel = new JLabel("Select Patient:");
        patientComboBox = new JComboBox<>(initialPatients.toArray(new String[0]));
        patientComboBox.addActionListener(e -> displayPatientInfo());
        panel.add(patientLabel);
        panel.add(patientComboBox);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();
        panel.add(ageLabel);
        panel.add(ageField);

        JLabel contactInfoLabel = new JLabel("Contact Info:");
        contactInfoField = new JTextField();
        panel.add(contactInfoLabel);
        panel.add(contactInfoField);

        JLabel contactAddressLabel = new JLabel("Contact Address:");
        contactAddressField = new JTextField();
        panel.add(contactAddressLabel);
        panel.add(contactAddressField);

        JLabel sexLabel = new JLabel("Sex:");
        String[] sexOptions = {"Male", "Female"};
        sexComboBox = new JComboBox<>(sexOptions);
        panel.add(sexLabel);
        panel.add(sexComboBox);

        JLabel dentalCareLabel = new JLabel("Dental Care:");
        String[] dentalCareOptions = {
                "Root Canal Treatment",
                "Cosmetic Dentistry",
                "Dental Crown",
                "Tooth Whitening",
                "Dental Implants",
                "Dental Bridge",
                "Periodontics",
                "Denture"
        };
        dentalCareComboBox = new JComboBox<>(dentalCareOptions);
        panel.add(dentalCareLabel);
        panel.add(dentalCareComboBox);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> savePatientInfo());
        panel.add(saveButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> showMainMenu());
        panel.add(mainMenuButton);

        add(panel, BorderLayout.CENTER);

        // Initialize fields for the first patient on load
        if (!initialPatients.isEmpty()) {
            displayPatientInfo();
        }

        setVisible(true);
    }

    private void displayPatientInfo() {
        String selectedPatient = (String) patientComboBox.getSelectedItem();
        // You can fetch and display patient details from your data source here
        // For demonstration, let's assume the fields are filled with dummy data
        ageField.setText("30");
        contactInfoField.setText("123-456-7890");
        contactAddressField.setText("123 Street, City, Country");
        sexComboBox.setSelectedItem("Male");
        dentalCareComboBox.setSelectedItem("Root Canal Treatment");
    }

    private void savePatientInfo() {
        String selectedPatient = (String) patientComboBox.getSelectedItem();
        // Retrieve entered information
        String age = ageField.getText();
        String contactInfo = contactInfoField.getText();
        String contactAddress = contactAddressField.getText();
        String sex = (String) sexComboBox.getSelectedItem();
        String dentalCare = (String) dentalCareComboBox.getSelectedItem();

        // Optionally, you can save this information to a database or file
        // For now, let's just display it
        String patientInfo = String.format("Name: %s\nAge: %s\nContact Info: %s\nContact Address: %s\nSex: %s\nDental Care: %s",
                selectedPatient, age, contactInfo, contactAddress, sex, dentalCare);
        JOptionPane.showMessageDialog(this, patientInfo, "Patient Information Saved", JOptionPane.INFORMATION_MESSAGE);

        // You may want to close the window after saving
        dispose();
    }

    private void showMainMenu() {
        new Admin();
        dispose();
    }

    public static void main(String[] args) {
        List<String> initialPatients = new ArrayList<>(); // Replace with actual initial patient names
        SwingUtilities.invokeLater(() -> new PatientManagementWindow(initialPatients));
    }
}
