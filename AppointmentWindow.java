package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, String> paymentStatus = new HashMap<>();
    private BillingWindow billingWindow;

    public AppointmentWindow() {
        billingWindow = new BillingWindow(paymentStatus);

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

        paymentButton = new JButton("Make Payment");
        paymentButton.addActionListener(e -> openPaymentWindow());

        appointmentList = new JTextArea(10, 40);
        appointmentList.setEditable(false);
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
        panel.add(paymentButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addAppointment() {
        String name = nameField.getText();
        String date = String.format("%s %s, %s", monthComboBox.getSelectedItem(), dayComboBox.getSelectedItem(), yearComboBox.getSelectedItem());
        String time = String.format("%s:%s", hourComboBox.getSelectedItem(), minuteComboBox.getSelectedItem());
        String dentalCare = (String) dentalCareComboBox.getSelectedItem();
        String doctor = (String) doctorComboBox.getSelectedItem();

        lastAddedName = name;
        lastAddedDate = date;
        lastAddedTime = time;
        lastAddedDentalCare = dentalCare;
        lastAddedDoctor = doctor;

        String appointmentDetails = String.format("Patient: %s\nDate: %s\nTime: %s\nDental Care: %s\nDoctor: %s\n\n", name, date, time, dentalCare, doctor);
        appointmentList.append(appointmentDetails);

        String key = createKey(name, date, time, dentalCare, doctor);
        paymentStatus.put(key, "Pending");

        billingWindow.updateStatus();
    }

    private void openPaymentWindow() {
        new PaymentWindow(lastAddedName, lastAddedDate, lastAddedTime, lastAddedDentalCare, lastAddedDoctor, paymentStatus, billingWindow);
    }

    private String createKey(String name, String date, String time, String dentalCare, String doctor) {
        return String.format("%s;%s;%s;%s;%s", name, date, time, dentalCare, doctor);
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
        // Code to show the main menu
    }
}
