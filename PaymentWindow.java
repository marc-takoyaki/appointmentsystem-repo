package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PaymentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField dentalCareField;
    private JTextField doctorField;
    private JTextArea paymentDetailsArea;
    private JLabel paymentStatusLabel;
    private JButton calculateButton;
    private JButton payButton;
    private Map<String, String> paymentStatus;
    private BillingWindow billingWindow;

    public PaymentWindow(String name, String date, String time, String dentalCare, String doctor, Map<String, String> paymentStatus, BillingWindow billingWindow) {
        this.paymentStatus = paymentStatus;
        this.billingWindow = billingWindow;

        // Create the frame
        frame = new JFrame("Payment System");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);

        // Create the panel
        panel = new JPanel(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, 10px gaps
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

        // Create the payment status label
        paymentStatusLabel = new JLabel("Payment Status: " + paymentStatus.getOrDefault(createKey(name, date, time, dentalCare, doctor), "Pending"));

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
        panel.add(paymentStatusLabel); // Add payment status label to the panel

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

        // Display payment details in the text area
        paymentDetailsArea.setText(paymentDetails);

        // Print payment details to console
        printPaymentDetails(paymentDetails);

        JOptionPane.showMessageDialog(frame, "Payment details calculated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void printPaymentDetails(String paymentDetails) {
        System.out.println("Payment Details:");
        System.out.println(paymentDetails);
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
        // Update payment status
        String key = createKey(nameField.getText(), dateField.getText(), timeField.getText(), dentalCareField.getText(), doctorField.getText());
        paymentStatus.put(key, "Paid");
        paymentStatusLabel.setText("Payment Status: " + paymentStatus.get(key));

        JOptionPane.showMessageDialog(frame, "Paid Successfully", "Payment", JOptionPane.INFORMATION_MESSAGE);

        // Print payment status to console
        System.out.println("Payment Status Updated: " + paymentStatus.get(key));

        // Update the billing window with the new status
        billingWindow.updateStatus();

        // Close the payment window
        frame.dispose();
    }

    private String createKey(String name, String date, String time, String dentalCare, String doctor) {
        return String.format("%s;%s;%s;%s;%s", name, date, time, dentalCare, doctor);
    }
}