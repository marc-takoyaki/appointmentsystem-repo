package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> dayComboBox;
    private JTextField timeField;
    private JComboBox<String> dentalCareComboBox; // New combo box for dental care options
    private JComboBox<String> doctorComboBox; // New combo box for doctors
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
            "Dr. Darryl Parrocho",
            "Dr. Ren Gubatanga"
    };

    public AppointmentWindow() {
        // Create the frame
        frame = new JFrame("Appointment Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBounds(380, 150, 800, 600);
        frame.setResizable(false);

        // Create the panel
        panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // Create input fields and labels
        nameField = new JTextField(20);
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"});
        dayComboBox = new JComboBox<>(getDaysOfMonth(31)); // Default to 31 days
        timeField = new JTextField(10); // Use a smaller size for time field
        dentalCareComboBox = new JComboBox<>(dentalCareOptions); // Populate dental care options
        doctorComboBox = new JComboBox<>(doctorOptions); // Populate doctor options

        JLabel nameLabel = new JLabel("Patient's Name: ");
        JLabel dateLabel = new JLabel("Date of Appointment: ");
        JLabel timeLabel = new JLabel("Time of Appointment 24Hour Format: (HH:MM): ");
        JLabel dentalCareLabel = new JLabel("Select Dental Care: ");
        JLabel doctorLabel = new JLabel("Select Doctor: ");

        // Create a button to add the appointment
        JButton addButton = new JButton("Add Appointment");

        // Create a text area to display appointments
        appointmentList = new JTextArea(10, 30);
        appointmentList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        // Panel for input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // Adjust gaps between components
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for date components
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        inputPanel.add(datePanel);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(dentalCareLabel);
        inputPanel.add(dentalCareComboBox);
        inputPanel.add(doctorLabel);
        inputPanel.add(doctorComboBox);
        inputPanel.add(new JLabel()); // Placeholder for alignment
        inputPanel.add(addButton);

        // Add components to main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    private void addAppointment() {
        String name = nameField.getText().trim();
        String month = (String) monthComboBox.getSelectedItem();
        int day = (int) dayComboBox.getSelectedItem();
        String timeStr = timeField.getText().trim();
        String dentalCare = (String) dentalCareComboBox.getSelectedItem(); // Get selected dental care option
        String doctor = (String) doctorComboBox.getSelectedItem(); // Get selected doctor option

        // Validate input fields
        if (name.isEmpty() || month.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Map month string to a numerical value (1-12)
        int monthValue = monthToNumber(month);

        // Validate time format
        try {
            String[] timeParts = timeStr.split(":");
            if (timeParts.length != 2) {
                throw new IllegalArgumentException();
            }
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new IllegalArgumentException();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Invalid time format. Use HH:MM (24-hour format).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Construct LocalDate object from selected month and day
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), monthValue, day);

        // Add appointment details to the list
        String appointmentDetails = String.format("Name: %s, Date: %s, Time: %s, Dental Care: %s, Doctor: %s\n",
                name, date.format(DateTimeFormatter.ISO_DATE), timeStr, dentalCare, doctor);
        appointmentList.append(appointmentDetails);

        // Clear input fields after adding the appointment
        nameField.setText("");
        timeField.setText("");

        JOptionPane.showMessageDialog(frame, "Appointment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Utility method to get an array of integers from 1 to max
    private Integer[] getDaysOfMonth(int max) {
        Integer[] days = new Integer[max];
        for (int i = 0; i < max; i++) {
            days[i] = i + 1;
        }
        return days;
    }

    // Utility method to convert month name to its corresponding number
    private int monthToNumber(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return -1; // Invalid month
        }
    }

    public static void main(String[] args) {
        // Use Event Dispatch Thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppointmentWindow();
            }
        });
    }
}
