package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorSpecialization {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JTextArea specializationArea;
    private JButton scheduleButton;

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
            "Dr. Ebreo: Specializes in Root Canal Treatment. With over 15 years of experience, Dr. Smith is an expert in handling complex root canal cases, ensuring patients experience minimal discomfort and quick recovery.",
            "Dr. Gubatanga: Specializes in Cosmetic Dentistry. Dr. Johnson focuses on improving the appearance of teeth, offering services such as veneers, bonding, and contouring to help you achieve a perfect smile.",
            "Dr. Parrocho: Specializes in Dental Crown. Dr. Williams is adept at restoring damaged teeth with crowns that look and function like natural teeth, providing durable and aesthetically pleasing results.",
            "Dr. Cheng Garcia: Specializes in Tooth Whitening. Using the latest technology, Dr. Brown offers professional tooth whitening services that can significantly brighten your smile in just one visit.",
            "Dr. Gabrial Cac: Specializes in Dental Implants. Dr. Jones has a high success rate in dental implant surgeries, offering a permanent solution for missing teeth with a focus on patient comfort and long-term oral health.",
            "Dr. Joy Inocencio: Specializes in Dental Bridge. Dr. Garcia provides comprehensive dental bridge solutions, ensuring that patients regain full functionality and aesthetics of their teeth seamlessly.",
            "Dr. Vladimir Guimbal: Specializes in Periodontics. Dr. Miller is an expert in treating gum diseases and offers advanced periodontal treatments to help maintain healthy gums and prevent tooth loss.",
            "Dr. Allen Garcia: Specializes in Denture. With extensive experience in fitting and creating custom dentures, Dr. Davis ensures that patients receive comfortable and natural-looking dentures that improve their quality of life."
    };

    public DoctorSpecialization() {
        // Create the frame
        frame = new JFrame("Doctor Specialization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(560, 600);
        frame.setLayout(new BorderLayout());
        frame.setBounds(520, 200, 500, 400);

        // Create the panel for doctor buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(doctors.length, 1));
        frame.add(buttonPanel, BorderLayout.WEST);

        // Create the panel for showing specialization and scheduling
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        frame.add(infoPanel, BorderLayout.CENTER);

        // Create the text area for specializations
        specializationArea = new JTextArea();
        specializationArea.setEditable(false);
        specializationArea.setLineWrap(true);
        specializationArea.setWrapStyleWord(true);
        specializationArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(specializationArea);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for each doctor with images
        for (int i = 0; i < doctors.length; i++) {
            JButton doctorButton = new JButton(doctors[i], new ImageIcon("path/to/doctor" + (i + 1) + ".jpg"));
            doctorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            doctorButton.setHorizontalTextPosition(SwingConstants.CENTER);
            int index = i; // For use in the action listener
            doctorButton.addActionListener(e -> showDoctorDetails(index));
            buttonPanel.add(doctorButton);
        }

        // Make the frame visible
        frame.setVisible(true);
    }

    private void showDoctorDetails(int index) {
        // Show the doctor's specialization
        specializationArea.setText(doctorDetails[index]);

        // Add the schedule button
        scheduleButton = new JButton("Schedule an Appointment");
        scheduleButton.setFont(new Font("Arial", Font.BOLD, 14));
        scheduleButton.setBackground(new Color(0, 123, 255));
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.setFocusPainted(false);
        scheduleButton.addActionListener(e -> scheduleAppointment(index));
        infoPanel.add(scheduleButton, BorderLayout.SOUTH);

        // Refresh the info panel
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void scheduleAppointment(int index) {
        JOptionPane.showMessageDialog(frame, "Opening appointment window for " + doctors[index]);
        new AppointmentWindow();
        frame.dispose();

    }
}