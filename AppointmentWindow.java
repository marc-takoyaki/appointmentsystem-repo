package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

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
    private JButton removeButton;
    private JButton clearButton;
    private DefaultListModel<String> appointmentListModel;

    public static String[] dentalCareOptions = {
            "Root Canal Treatment",
            "Cosmetic Dentistry",
            "Dental Crown",
            "Tooth Whitening",
            "Dental Implants",
            "Dental Bridge",
            "Periodontics",
            "Denture"
    };

    public static String[] doctorOptions = {
            "Dr. Marc Ebreo",
            "Dr. Ren Gubatanga",
            "Dr. Darryl Parrocho",
            "Dr. Cheng Garcia",
            "Dr. Gabriel Cac",
            "Dr. Joy Inocencio",
            "Dr. Vladimir Guimbal",
            "Dr. Allen Garcia"
    };

    private Map<String, String> paymentStatus = new HashMap<>();

    public AppointmentWindow() {
        frame = new JFrame("Appointment Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        mainMenuItem.addActionListener(e -> showMainMenu());
        menu.add(mainMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();

        monthComboBox = new JComboBox<>(getMonthOptions());
        dayComboBox = new JComboBox<>(getDayOptions());
        yearComboBox = new JComboBox<>(getYearOptions());
        hourComboBox = new JComboBox<>(getHourOptions());
        minuteComboBox = new JComboBox<>(getMinuteOptions());

        dentalCareComboBox = new JComboBox<>(dentalCareOptions);
        doctorComboBox = new JComboBox<>(doctorOptions);

        addButton = new JButton("Add Appointment");
        addButton.addActionListener(e -> addAppointment());

        removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeAppointment());

        clearButton = new JButton("Clear All Appointments");
        clearButton.addActionListener(e -> clearAppointments());

        appointmentListModel = new DefaultListModel<>();
        JList<String> appointmentList = new JList<>(appointmentListModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        panel.add(new JLabel("Patient's Name: "));
        panel.add(nameField);
        panel.add(new JLabel("Month: "));
        panel.add(monthComboBox);
        panel.add(new JLabel("Day: "));
        panel.add(dayComboBox);
        panel.add(new JLabel("Year: "));
        panel.add(yearComboBox);
        panel.add(new JLabel("Hour: "));
        panel.add(hourComboBox);
        panel.add(new JLabel("Minute: "));
        panel.add(minuteComboBox);
        panel.add(new JLabel("Dental Care: "));
        panel.add(dentalCareComboBox);
        panel.add(new JLabel("Doctor: "));
        panel.add(doctorComboBox);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(clearButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        loadAppointments();

        frame.setVisible(true);
    }

    private void addAppointment() {
        String name = nameField.getText();
        String date = String.format("%s %s, %s", monthComboBox.getSelectedItem(), dayComboBox.getSelectedItem(), yearComboBox.getSelectedItem());
        String time = String.format("%s:%s", hourComboBox.getSelectedItem(), minuteComboBox.getSelectedItem());
        String dentalCare = (String) dentalCareComboBox.getSelectedItem();
        String doctor = (String) doctorComboBox.getSelectedItem();

        String appointmentDetails = String.format("Patient: %s, Date: %s, Time: %s, Dental Care: %s, Doctor: %s",
                name, date, time, dentalCare, doctor);
        appointmentListModel.addElement(appointmentDetails);

        String key = createKey(name, date, time, dentalCare, doctor);
        paymentStatus.put(key, "Pending");

        saveAppointments();

        // Update Doctor Monitoring Window
        updateDoctorMonitoring();
    }

    private void removeAppointment() {
        int selectedIndex = appointmentListModel.getSize() - 1; // Get last selected index
        if (selectedIndex >= 0) {
            String appointmentDetails = appointmentListModel.getElementAt(selectedIndex);
            appointmentListModel.removeElementAt(selectedIndex);

            // Remove from paymentStatus map
            String keyToRemove = getKeyFromDetails(appointmentDetails);
            paymentStatus.remove(keyToRemove);

            saveAppointments();

            // Update Doctor Monitoring Window
            updateDoctorMonitoring();
        }
    }

    private void clearAppointments() {
        appointmentListModel.clear();
        paymentStatus.clear();
        saveAppointments();

        // Update Doctor Monitoring Window
        updateDoctorMonitoring();
    }

    private void saveAppointments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("appointments.dat"))) {
            oos.writeObject(paymentStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAppointments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appointments.dat"))) {
            paymentStatus = (Map<String, String>) ois.readObject();
            for (String key : paymentStatus.keySet()) {
                String appointmentDetails = createDetailsFromKey(key);
                appointmentListModel.addElement(appointmentDetails);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String createKey(String name, String date, String time, String dentalCare, String doctor) {
        return String.format("%s;%s;%s;%s;%s", name, date, time, dentalCare, doctor);
    }

    private String createDetailsFromKey(String key) {
        String[] details = key.split(";");
        if (details.length == 5) {
            return String.format("Patient: %s, Date: %s, Time: %s, Dental Care: %s, Doctor: %s",
                    details[0], details[1], details[2], details[3], details[4]);
        }
        return "";
    }

    private String getKeyFromDetails(String details) {
        String[] parts = details.split(", ");
        if (parts.length == 5) {
            String name = parts[0].split(": ")[1];
            String date = parts[1].split(": ")[1];
            String time = parts[2].split(": ")[1];
            String dentalCare = parts[3].split(": ")[1];
            String doctor = parts[4].split(": ")[1];
            return createKey(name, date, time, dentalCare, doctor);
        }
        return "";
    }

    private String[] getMonthOptions() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    private String[] getDayOptions() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = Integer.toString(i + 1);
        }
        return days;
    }

    private String[] getYearOptions() {
        String[] years = new String[5];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 5; i++) {
            years[i] = Integer.toString(currentYear + i);
        }
        return years;
    }

    private String[] getHourOptions() {
        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }
        return hours;
    }

    private String[] getMinuteOptions() {
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        return minutes;
    }

    private void showMainMenu() {
        new Menu();
        frame.dispose();
    }

    private void updateDoctorMonitoring() {
        SwingUtilities.invokeLater(() -> {
            DoctorMonitoringWindow monitoringWindow = new DoctorMonitoringWindow(paymentStatus);
            monitoringWindow.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppointmentWindow::new);
    }
}
