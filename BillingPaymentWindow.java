package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillingPaymentWindow {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField contactNumberField;
    private JTextField amountField;
    private JButton submitButton;

    public BillingPaymentWindow() {
        frame = new JFrame("Billing Window");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel(new GridLayout(6, 2, 10, 10));
        frame.add(panel);

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        contactNumberField = new JTextField(20);
        amountField = new JTextField(20);

        // Add components to the panel
        panel.add(new JLabel("Patient's Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Patient's Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Contact Number:"));
        panel.add(contactNumberField);
        panel.add(new JLabel("Amount to Pay:"));
        panel.add(amountField);

        // Create submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitBilling();
            }
        });
        panel.add(new JLabel()); // Placeholder for alignment
        panel.add(submitButton);

        frame.setVisible(true);
    }

    private void submitBilling() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String contactNumber = contactNumberField.getText().trim();
        String amount = amountField.getText().trim();

        if (name.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String billingDetails = String.format("Patient's Name: %s\nAddress: %s\nContact Number: %s\nAmount Paid: %s",
                name, address, contactNumber, amount);
        JOptionPane.showMessageDialog(frame, billingDetails, "Billing Information", JOptionPane.INFORMATION_MESSAGE);

        nameField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        amountField.setText("");
    }




}
