package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DoctorSpecialization {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JTextArea specializationArea;
    private boolean isOpen = false; // Flag to check if the window is already open

    private static final String[] doctors = {
            "Dr. Marc Ebreo",
            "Dr. Ren Gubatanga",
            "Dr. Darryl Parrocho",
            "Dr. Cheng Garcia",
            "Dr. Gabriel Cac",
            "Dr. Joy Inocencio",
            "Dr. Vladimir Guimbal",
            "Dr. Allen Garcia"
    };

    private static final String[] doctorDetails = {
            "Dr. Ebreo: Specializes in Root Canal Treatment. With over 15 years of experience, Dr. Ebreo is an expert in handling complex root canal cases, ensuring patients experience minimal discomfort and quick recovery.",
            "Dr. Gubatanga: Specializes in Cosmetic Dentistry. Dr. Gubatanga focuses on improving the appearance of teeth, offering services such as veneers, bonding, and contouring to help you achieve a perfect smile.",
            "Dr. Parrocho: Specializes in Dental Crown. Dr. Parrocho is adept at restoring damaged teeth with crowns that look and function like natural teeth, providing durable and aesthetically pleasing results.",
            "Dr. Cheng Garcia: Specializes in Tooth Whitening. Using the latest technology, Dr. Cheng Garcia offers professional tooth whitening services that can significantly brighten your smile in just one visit.",
            "Dr. Gabriel Cac: Specializes in Dental Implants. Dr. Gabriel Cac has a high success rate in dental implant surgeries, offering a permanent solution for missing teeth with a focus on patient comfort and long-term oral health.",
            "Dr. Joy Inocencio: Specializes in Dental Bridge. Dr. Joy Inocencio provides comprehensive dental bridge solutions, ensuring that patients regain full functionality and aesthetics of their teeth seamlessly.",
            "Dr. Vladimir Guimbal: Specializes in Periodontics. Dr. Vladimir Guimbal is an expert in treating gum diseases and offers advanced periodontal treatments to help maintain healthy gums and prevent tooth loss.",
            "Dr. Allen Garcia: Specializes in Denture. With extensive experience in fitting and creating custom dentures, Dr. Allen Garcia ensures that patients receive comfortable and natural-looking dentures that improve their quality of life."
    };

    private static final String[] doctorImages = {
            "path/to/images/doctor1.jpg",
            "path/to/images/doctor2.jpg",
            "path/to/images/doctor3.jpg",
            "path/to/images/doctor4.jpg",
            "path/to/images/doctor5.jpg",
            "path/to/images/doctor6.jpg",
            "path/to/images/doctor7.jpg",
            "path/to/images/doctor8.jpg"
    };

    public DoctorSpecialization() {
        if (isOpen) {
            JOptionPane.showMessageDialog(null, "Doctor Specialization window is already open.");
            return;
        }

        isOpen = true;

        frame = new JFrame("Doctor Specialization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(560, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the frame on screen

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu homeMenu = new JMenu("Home");
        JMenuItem homeMenuItem = new JMenuItem("Go to Home");
        homeMenu.add(homeMenuItem);
        menuBar.add(homeMenu);

        homeMenuItem.addActionListener(this::goToHome);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(doctors.length, 1));
        frame.add(buttonPanel, BorderLayout.WEST);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        frame.add(infoPanel, BorderLayout.CENTER);

        specializationArea = new JTextArea();
        specializationArea.setEditable(false);
        specializationArea.setLineWrap(true);
        specializationArea.setWrapStyleWord(true);
        specializationArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(specializationArea);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for each doctor with images
        for (int i = 0; i < doctors.length; i++) {
            JButton doctorButton = new JButton(doctors[i]);
            doctorButton.setHorizontalAlignment(SwingConstants.LEFT);
            // Replace with actual image loading logic
            doctorButton.setIcon(new ImageIcon(doctorImages[i]));
            int index = i; // Capture index for action listener
            doctorButton.addActionListener(e -> showDoctorDetails(index));
            buttonPanel.add(doctorButton);
        }

        frame.setVisible(true);
    }

    private void showDoctorDetails(int index) {
        specializationArea.setText(doctorDetails[index]);

        if (infoPanel.getComponentCount() > 1) {
            infoPanel.remove(1); // Remove existing schedule button if any
        }
        JButton scheduleButton = new JButton("Schedule an Appointment");
        scheduleButton.setFont(new Font("Arial", Font.BOLD, 14));
        scheduleButton.setBackground(new Color(0, 123, 255));
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.setFocusPainted(false);
        scheduleButton.addActionListener(e -> scheduleAppointment(index));
        infoPanel.add(scheduleButton, BorderLayout.SOUTH);

        //  info panel
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void scheduleAppointment(int index) {
        JOptionPane.showMessageDialog(frame, "Opening appointment window for " + doctors[index]);
        new AppointmentWindow();
        frame.dispose(); // Close the current frame
        isOpen = false; // Reset the flag since the window is closed
    }

    private void goToHome(ActionEvent e) {
        frame.dispose(); // Dispose of current window
        new Admin(); // Open the main menu
    }
}