package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppointmentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> hourComboBox;
    private JComboBox<String> minuteComboBox;
    private JComboBox<String> dentalCareComboBox;
    private JComboBox<String> doctorComboBox;
    private JButton addButton;
    private JButton paymentButton;
    private JTextArea appointmentList;

    private String[] dentalCareOptions = {
            "Root Canal Treatment",
            "Cosmetic Dentistry",
            "Dental Crown",
            "Tooth Whitening",
            "Dental Implants",
            "Dental Bridge",
            "Periodontics",
            "Denture"
    };

    private String[] doctorOptions = {
            "Dr. Marc Ebreo",
            "Dr. Ren Gubatanga",
            "Dr. Darryl Parrocho",
            "Dr. Cheng Garcia",
            "Dr. Gabriel Cac",
            "Dr. Joy Inocencio",
            "Dr. Vladimir Guimbal",
            "Dr. Allen Garcia"
    };

    private String lastAddedName;
    private String lastAddedDate;
    private String lastAddedTime;
    private String lastAddedDentalCare;
    private String lastAddedDoctor;

    public AppointmentWindow() {
        // Create the frame
        frame = new JFrame("Appointment Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create the Home menu with a single menu item
        JMenu homeMenu = new JMenu("Home");
        JMenuItem homeMenuItem = new JMenuItem("Go to Home");
        homeMenu.add(homeMenuItem);
        menuBar.add(homeMenu);

        // Add action listener to the Home menu item
        homeMenuItem.addActionListener(e -> goToHome());

        // Create the panel
        panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // Create input fields and labels
        nameField = new JTextField(20);

        // Date combo boxes
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"});
        dayComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30", "31"});
        yearComboBox = new JComboBox<>(new String[]{"2023", "2024", "2025"});
        // Time combo boxes
        hourComboBox = new JComboBox<>(new String[]{"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"});
        minuteComboBox = new JComboBox<>(new String[]{"00", "15", "30", "45"});

        dentalCareComboBox = new JComboBox<>(dentalCareOptions);
        doctorComboBox = new JComboBox<>(doctorOptions);

        // Create a button to add the appointment
        addButton = new JButton("Add Appointment");

        // Text area to display appointments
        appointmentList = new JTextArea(20, 50);
        appointmentList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        // Panel for input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10)); // Adjust gaps between components
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        inputPanel.add(new JLabel("Patient's Name: "));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Date of Appointment: "));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for date components
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        datePanel.add(yearComboBox);
        inputPanel.add(datePanel);
        inputPanel.add(new JLabel("Time of Appointment: "));
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for time components
        timePanel.add(hourComboBox);
        timePanel.add(new JLabel(":"));
        timePanel.add(minuteComboBox);
        inputPanel.add(timePanel);
        inputPanel.add(new JLabel("Select Dental Care: "));
        inputPanel.add(dentalCareComboBox);
        inputPanel.add(new JLabel("Select Doctor: "));
        inputPanel.add(doctorComboBox);
        inputPanel.add(new JLabel()); // Placeholder for alignment
        inputPanel.add(addButton);

        // Add components to main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the button
        addButton.addActionListener(e -> addAppointment());

        // Create the payment button (initially invisible)
        paymentButton = new JButton("Payment");
        paymentButton.setVisible(false);
        paymentButton.addActionListener(e -> showPaymentDialog());

        // Add the payment button to the panel
        panel.add(paymentButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    private void addAppointment() {
        String name = nameField.getText().trim();
        String month = (String) monthComboBox.getSelectedItem();
        String day = (String) dayComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        String hour = (String) hourComboBox.getSelectedItem();
        String minute = (String) minuteComboBox.getSelectedItem();
        String dentalCare = (String) dentalCareComboBox.getSelectedItem();
        String doctor = (String) doctorComboBox.getSelectedItem();

        // Validate input fields
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter patient's name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construct LocalDateTime object from selected date and time
        String dateTime = String.format("%s %s, %s at %s:%s", month, day, year, hour, minute);
        String appointmentDetails = String.format("Patient's Name: %s\nDate and Time: %s\nDental Care: %s\nDoctor: %s\n\n",
                name, dateTime, dentalCare, doctor);

        // Store the details of the last added appointment
        lastAddedName = name;
        lastAddedDate = String.format("%s %s, %s", month, day, year);
        lastAddedTime = String.format("%s:%s", hour, minute);
        lastAddedDentalCare = dentalCare;
        lastAddedDoctor = doctor;

        // Append appointment details to the text area
        appointmentList.append(appointmentDetails);

        // Clear input fields after adding the appointment
        nameField.setText("");

        // Show success message
        JOptionPane.showMessageDialog(frame, "Appointment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Make the payment button visible
        paymentButton.setVisible(true);
    }

    private void showPaymentDialog() {
        new PaymentWindow(lastAddedName, lastAddedDate, lastAddedTime, lastAddedDentalCare, lastAddedDoctor);
    }

    private void goToHome() {
        frame.dispose(); // Dispose of current window
        new Menu(); // Open the main menu
    }

    public static void main(String[] args) {
        new AppointmentWindow();
    }
}
