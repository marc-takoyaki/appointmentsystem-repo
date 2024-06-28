package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu patientManagementMenu;
    private JMenu billingPaymentMenu;
    private JMenu inventorySupplyMenu;
    private JMenu doctorSchedulerMenu;
    private JMenuItem patientManagementMenuItem;
    private JMenuItem billingPaymentMenuItem;
    private JMenuItem inventorySupplyMenuItem;
    private JMenuItem doctorSchedulerMenuItem;
    private JButton logoutButton;
    private Map<String, String> paymentStatus = new HashMap<>();

    Admin() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a panel with a blue background
        JPanel introPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(135, 206, 250)); // Light blue color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
        introPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel introLabel = new JLabel("WELCOME TO THE ADMIN SYSTEM!");
        introLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        introLabel.setFont(new Font("Arial", Font.BOLD, 18));
        introPanel.add(introLabel);

        add(introPanel, BorderLayout.CENTER);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());

        add(logoutButton, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        patientManagementMenu = new JMenu("Patient Management");
        billingPaymentMenu = new JMenu("Billing Payment");
        inventorySupplyMenu = new JMenu("Inventory/Supply");
        doctorSchedulerMenu = new JMenu("Doctor Scheduler");

        patientManagementMenuItem = new JMenuItem("Patient Management");
        billingPaymentMenuItem = new JMenuItem("Billing Payment");
        inventorySupplyMenuItem = new JMenuItem("Inventory/Supply");
        doctorSchedulerMenuItem = new JMenuItem("Doctor Scheduler");

        patientManagementMenuItem.addActionListener(this);
        billingPaymentMenuItem.addActionListener(this);
        inventorySupplyMenuItem.addActionListener(this);
        doctorSchedulerMenuItem.addActionListener(this);

        patientManagementMenu.add(patientManagementMenuItem);
        billingPaymentMenu.add(billingPaymentMenuItem);
        inventorySupplyMenu.add(inventorySupplyMenuItem);
        doctorSchedulerMenu.add(doctorSchedulerMenuItem);

        menuBar.add(patientManagementMenu);
        menuBar.add(billingPaymentMenu);
        menuBar.add(inventorySupplyMenu);
        menuBar.add(doctorSchedulerMenu);

        setJMenuBar(menuBar);

        loadAppointments(); // Load appointments initially

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == patientManagementMenuItem) {
            // Open patient management window
            List<String> initialPatients = new ArrayList<>(paymentStatus.keySet());
            SwingUtilities.invokeLater(() -> new PatientManagementWindow(initialPatients));
            dispose();
        } else if (e.getSource() == billingPaymentMenuItem) {
            loadAppointments();
            new BillingWindow(paymentStatus);
        } else if (e.getSource() == inventorySupplyMenuItem) {
            // Open inventory/supply window
            new InventorySupplyWindow();
        } else if (e.getSource() == doctorSchedulerMenuItem) {
            // Open Doctor Scheduler window
            new DoctorMonitoringWindow(paymentStatus);
        }
    }

    private void loadAppointments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appointments.dat"))) {
            paymentStatus = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        // Handle logout functionality
        new Menu();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Admin::new);
    }
}
