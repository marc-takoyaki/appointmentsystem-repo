package Appointmentsystem_package;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SwingUtilities;

public class PaymentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField dentalCareField;
    private JTextField doctorField;
    private JTextArea paymentDetailsArea;
    private JButton calculateButton;
    private JButton payButton;

    public PaymentWindow(String name, String date, String time, String dentalCare, String doctor) {
        // Create the frame
        frame = new JFrame("Payment System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);

        // Create the panel
        panel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns, 10px gaps
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Create input fields and labels
        nameField = new JTextField(name);
        nameField.setEditable(false);

        dateField = new JTextField(date);
        dateField.setEditable(false);

        timeField = new JTextField(time);
        timeField.setEditable(false);

        dentalCareField = new JTextField(dentalCare);
        dentalCareField.setEditable(false);

        doctorField = new JTextField(doctor);
        doctorField.setEditable(false);

        paymentDetailsArea = new JTextArea(5, 20);
        paymentDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(paymentDetailsArea);

        // Add components to panel
        panel.add(new JLabel("Patient's Name: "));
        panel.add(nameField);
        panel.add(new JLabel("Date of Appointment: "));
        panel.add(dateField);
        panel.add(new JLabel("Time of Appointment: "));
        panel.add(timeField);
        panel.add(new JLabel("Dental Care: "));
        panel.add(dentalCareField);
        panel.add(new JLabel("Doctor: "));
        panel.add(doctorField);

        // Create the calculate button
        calculateButton = new JButton("Calculate Payment");
        calculateButton.addActionListener(e -> calculatePayment());

        // Add the button and text area to the panel
        panel.add(calculateButton);
        panel.add(scrollPane);

        // Create the pay button
        payButton = new JButton("Pay");
        payButton.addActionListener(e -> handlePayment());

        // Add the panel and pay button to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(payButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    public PaymentWindow() {

    }

    private void calculatePayment() {
        String dentalCare = dentalCareField.getText();
        double price = getTreatmentPrice(dentalCare);

        if (price == 0.00) {
            JOptionPane.showMessageDialog(frame, "Invalid dental care type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String paymentDetails = String.format(
                "Patient's Name: %s\nDate: %s\nTime: %s\nDental Care: %s\nDoctor: %s\n\nTotal Amount Due: $%.2f",
                nameField.getText(),
                dateField.getText(),
                timeField.getText(),
                dentalCare,
                doctorField.getText(),
                price
        );

        // Display payment details in the text area and print to the console
        paymentDetailsArea.setText(paymentDetails);
        System.out.println(paymentDetails);

        JOptionPane.showMessageDialog(frame, "Payment details calculated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private double getTreatmentPrice(String dentalCare) {
        switch (dentalCare) {
            case "Root Canal Treatment":
                return 300.00;
            case "Cosmetic Dentistry":
                return 500.00;
            case "Dental Crown":
                return 700.00;
            case "Tooth Whitening":
                return 200.00;
            case "Dental Implants":
                return 1000.00;
            case "Dental Bridge":
                return 800.00;
            case "Periodontics":
                return 400.00;
            case "Denture":
                return 600.00;
            default:
                return 0.00;
        }
    }

    private void handlePayment() {
        // Handle payment logic here
        JOptionPane.showMessageDialog(frame, "Proceeding to payment.", "Payment", JOptionPane.INFORMATION_MESSAGE);
        // Replace with your actual payment logic
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new PaymentWindow("John Doe", "2023-06-25", "10:00 AM", "Dental Crown", "Dr. Smith"));
    }
}
