package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

        JLabel introLabel = new JLabel("WELCOME TO THE ADMIN SYSTEM");
        introLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        introPanel.add(Box.createVerticalGlue());
        introPanel.add(introLabel);
        introPanel.add(Box.createVerticalGlue());

        // Add the intro panel to the frame
        add(introPanel, BorderLayout.CENTER);

        // Create the menu bar and menus
        menuBar = new JMenuBar();
        patientManagementMenu = new JMenu("Patient Management");
        billingPaymentMenu = new JMenu("Billing and Payment");
        inventorySupplyMenu = new JMenu("Inventory Supply");
        staffManagementMenu = new JMenu("Staff Management");

        // Create menu items
        patientManagementMenuItem = new JMenuItem("Patient Management");
        billingPaymentMenuItem = new JMenuItem("Billing and Payment");
        inventorySupplyMenuItem = new JMenuItem("Inventory Supply");
        staffManagementMenuItem = new JMenuItem("Staff Management");

        // Add action listener to menu items
        patientManagementMenuItem.addActionListener(this);
        billingPaymentMenuItem.addActionListener(this);
        inventorySupplyMenuItem.addActionListener(this);
        staffManagementMenuItem.addActionListener(this);

        // Add menu items to respective menus
        patientManagementMenu.add(patientManagementMenuItem);
        billingPaymentMenu.add(billingPaymentMenuItem);
        inventorySupplyMenu.add(inventorySupplyMenuItem);
        staffManagementMenu.add(staffManagementMenuItem);

        // Add menus to menu bar
        menuBar.add(patientManagementMenu);
        menuBar.add(billingPaymentMenu);
        menuBar.add(inventorySupplyMenu);
        menuBar.add(staffManagementMenu);

        // Set menu bar to frame
        setJMenuBar(menuBar);

        // Create a logout button panel
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        logoutPanel.add(logoutButton);

        // Add logout panel to the frame
        add(logoutPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Patient Management")) {
            JOptionPane.showMessageDialog(this, "Patient Management functionality to be implemented.");
        } else if (e.getActionCommand().equals("Billing and Payment")) {
            new BillingWindow(new HashMap<>()); // Initialize with an empty map or load from persistent storage
        } else if (e.getActionCommand().equals("Inventory Supply")) {
            JOptionPane.showMessageDialog(this, "Inventory Supply functionality to be implemented.");
        } else if (e.getActionCommand().equals("Staff Management")) {
            JOptionPane.showMessageDialog(this, "Staff Management functionality to be implemented.");
        } else if (e.getSource() == logoutButton) {
            // Handle logout button click
            new Login_menu();
            dispose(); // Close the admin window
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Admin::new);
    }
}
