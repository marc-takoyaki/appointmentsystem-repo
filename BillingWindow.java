package Appointmentsystem_package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BillingWindow extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, String> paymentStatus;
    private JButton payButton;
    private JButton removeButton; // New button for removing appointments
    private PaymentWindow paymentWindow;

    public BillingWindow(Map<String, String> paymentStatus) {
        this.paymentStatus = paymentStatus;

        setTitle("Billing System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // Table model with column names
        tableModel = new DefaultTableModel(new String[]{"Patient's Name", "Date of Appointment", "Time of Appointment", "Dental Care", "Doctor", "Payment Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // Create the pay button
        payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(BillingWindow.this, "Please select a row to proceed with payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = table.getValueAt(selectedRow, 0).toString();
                String date = table.getValueAt(selectedRow, 1).toString();
                String time = table.getValueAt(selectedRow, 2).toString();
                String dentalCare = table.getValueAt(selectedRow, 3).toString();
                String doctor = table.getValueAt(selectedRow, 4).toString();

                // Open PaymentWindow for the selected appointment
                paymentWindow = new PaymentWindow(name, date, time, dentalCare, doctor, paymentStatus, BillingWindow.this);
            }
        });

        // Create the remove button
        removeButton = new JButton("Remove Client");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(BillingWindow.this, "Please select a row to remove the appointment.", "Remove Appointment Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = table.getValueAt(selectedRow, 0).toString();
                String date = table.getValueAt(selectedRow, 1).toString();
                String time = table.getValueAt(selectedRow, 2).toString();
                String dentalCare = table.getValueAt(selectedRow, 3).toString();
                String doctor = table.getValueAt(selectedRow, 4).toString();

                // Remove the appointment from paymentStatus map
                String key = createKey(name, date, time, dentalCare, doctor);
                paymentStatus.remove(key);

                // Update the table
                updateStatus();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(removeButton); // Add remove button
        buttonPanel.add(payButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateStatus(); // Populate table with initial data

        setVisible(true);
    }

    public void updateStatus() {
        // Clear the table
        tableModel.setRowCount(0);

        // Add rows to the table model
        for (Map.Entry<String, String> entry : paymentStatus.entrySet()) {
            String[] details = entry.getKey().split(";");
            if (details.length == 5) {
                tableModel.addRow(new Object[]{details[0], details[1], details[2], details[3], details[4], entry.getValue()});
            }
        }
    }

    private String createKey(String name, String date, String time, String dentalCare, String doctor) {
        return String.format("%s;%s;%s;%s;%s", name, date, time, dentalCare, doctor);
    }

    public static void main(String[] args) {
        // Sample usage:
        SwingUtilities.invokeLater(() -> {
            Map<String, String> paymentStatus = new HashMap<>(); // Initialize with some data
            paymentStatus.put("John Doe;June 28, 2024;10:00;Dental Checkup;Dr. Smith", "Pending");
            paymentStatus.put("Jane Smith;June 29, 2024;14:30;Tooth Extraction;Dr. Brown", "Paid");

            new BillingWindow(paymentStatus);
        });
    }
}
