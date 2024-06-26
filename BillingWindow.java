package Appointmentsystem_package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class BillingWindow {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, String> paymentStatus;

    public BillingWindow(Map<String, String> paymentStatus) {
        this.paymentStatus = paymentStatus;

        frame = new JFrame("Billing System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false);

        // Table model with column names
        tableModel = new DefaultTableModel(new String[]{"Patient's Name", "Date of Appointment", "Time of Appointment", "Dental Care", "Doctor", "Payment Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        updateStatus();

        frame.setVisible(true);
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
}
