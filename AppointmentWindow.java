package Appointmentsystem_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextArea appointmentList;

    public AppointmentWindow() {
        // Create the frame
        frame = new JFrame("Appointment Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(560, 250, 600, 400);
        frame.setSize(600, 400);

        // Create the panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        // Create input fields
        nameField = new JTextField(20);
        dateField = new JTextField(20);
        timeField = new JTextField(20);

        // Create labels for the input fields
        JLabel nameLabel = new JLabel("Name: ");
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD): ");
        JLabel timeLabel = new JLabel("Time (HH:MM): ");

        // Create a button to add the appointment
        JButton addButton = new JButton("Add Appointment");

        // Create a text area to display appointments
        appointmentList = new JTextArea(10, 30);
        appointmentList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        // Create a panel to hold input fields and labels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(new JLabel()); // Empty cell in grid
        inputPanel.add(addButton);

        // Add the input panel and the text area to the main panel
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
        String name = nameField.getText();
        String dateStr = dateField.getText();
        String timeStr = timeField.getText();

        if (name.isEmpty() || dateStr.isEmpty() || timeStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Convert date and time strings to integers or another suitable numeric format
            int date = Integer.parseInt(dateStr.replace("-", ""));
            int time = Integer.parseInt(timeStr.replace(":", ""));

            // Example formatting for display in appointment list
            String appointmentDetails = String.format("Name: %s, Date: %d, Time: %d\n", name, date, time);
            appointmentList.append(appointmentDetails);

            // Clear input fields after adding the appointment
            nameField.setText("");
            dateField.setText("");
            timeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid format for date or time.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Create an instance of MenuWindow
        AppointmentWindow menuWindow = new AppointmentWindow();
    }
}
