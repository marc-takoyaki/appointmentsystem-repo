package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class Admin extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu patientManagementMenu;
    private JMenu billingPaymentMenu;
    private JMenu inventorySupplyMenu;
    private JMenu staffManagementMenu;
    private JMenuItem patientManagementMenuItem;
    private JMenuItem billingPaymentMenuItem;
    private JMenuItem inventorySupplyMenuItem;
    private JMenuItem staffManagementMenuItem;
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
        staffManagementMenu = new JMenu("Staff Management");

        patientManagementMenuItem = new JMenuItem("Patient Management");
        billingPaymentMenuItem = new JMenuItem("Billing Payment");
        inventorySupplyMenuItem = new JMenuItem("Inventory/Supply");
        staffManagementMenuItem = new JMenuItem("Staff Management");

        patientManagementMenuItem.addActionListener(this);
        billingPaymentMenuItem.addActionListener(this);
        inventorySupplyMenuItem.addActionListener(this);
        staffManagementMenuItem.addActionListener(this);

        patientManagementMenu.add(patientManagementMenuItem);
        billingPaymentMenu.add(billingPaymentMenuItem);
        inventorySupplyMenu.add(inventorySupplyMenuItem);
        staffManagementMenu.add(staffManagementMenuItem);

        menuBar.add(patientManagementMenu);
        menuBar.add(billingPaymentMenu);
        menuBar.add(inventorySupplyMenu);
        menuBar.add(staffManagementMenu);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == patientManagementMenuItem) {
            // Open patient management window
            // new PatientManagementWindow();
        } else if (e.getSource() == billingPaymentMenuItem) {
            loadAppointments();
            new BillingWindow(paymentStatus);
        } else if (e.getSource() == inventorySupplyMenuItem) {
            // Open inventory/supply window
            // new InventorySupplyWindow();
        } else if (e.getSource() == staffManagementMenuItem) {
            // Open staff management window
            // new StaffManagementWindow();
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
