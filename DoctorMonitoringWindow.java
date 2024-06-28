package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DoctorMonitoringWindow extends JFrame {

    private JTextArea statusTextArea;

    public DoctorMonitoringWindow(Map<String, String> appointments) {
        setTitle("Doctor Monitoring");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusTextArea);

        updateDoctorStatus(appointments);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updateDoctorStatus(Map<String, String> appointments) {
        statusTextArea.setText("");
        for (String doctor : AppointmentWindow.doctorOptions) {
            boolean available = checkAvailability(doctor, appointments);
            String status = available ? "Available" : "Not Available";
            statusTextArea.append(doctor + ": " + status + "\n");
        }
    }

    private boolean checkAvailability(String doctor, Map<String, String> appointments) {
        for (String key : appointments.keySet()) {
            String[] details = key.split(";");
            if (details.length == 5 && details[4].equals(doctor)) {
                return false; // Doctor is not available if any appointment exists
            }
        }
        return true; // Doctor is available if no conflicting appointment found
    }
}
