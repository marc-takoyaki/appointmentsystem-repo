package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu PatientManagementMenu;
    JMenu BillingPaymentMenu;
    JMenu InventorySupplyMenu;
    JMenu StaffManagementMenu;
    JMenuItem PatientManagementMenuItem;
    JMenuItem BillingPaymentMenuItem;
    JMenuItem InventorySupplyMenuItem;
    JMenuItem StaffManagementMenuItem;

    Admin() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setBounds(520, 200, 650, 500);
        this.setLayout(new BorderLayout()); // Use BorderLayout for better component management

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
        introPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JLabel introLabel = new JLabel("WELCOME TO THE SYSTEM");
        introLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the label

        introPanel.add(Box.createVerticalGlue()); // Align components at the top
        introPanel.add(introLabel);
        introPanel.add(Box.createVerticalGlue()); // Align components at the bottom

        // Add the intro panel to the frame
        this.add(introPanel, BorderLayout.CENTER);

        // Create the menu bar
        menuBar = new JMenuBar();
        PatientManagementMenu = new JMenu("Patient Management");
        BillingPaymentMenu = new JMenu("Billing and Payment");
        InventorySupplyMenu = new JMenu("Inventory Supply");
        StaffManagementMenu = new JMenu("Staff Management");

        PatientManagementMenuItem = new JMenuItem("Patient Management");
        BillingPaymentMenuItem = new JMenuItem("Billing and Payment");
        InventorySupplyMenuItem = new JMenuItem("Inventory Supply");
        StaffManagementMenuItem = new JMenuItem("Staff Management");

        PatientManagementMenuItem.addActionListener(this);
        BillingPaymentMenuItem.addActionListener(this);
        InventorySupplyMenuItem.addActionListener(this);
        StaffManagementMenuItem.addActionListener(this);

        menuBar.add(PatientManagementMenuItem);
        menuBar.add(BillingPaymentMenuItem);
        menuBar.add(InventorySupplyMenuItem);
        menuBar.add(StaffManagementMenuItem);

        // Add the menu bar to the frame
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PatientManagementMenuItem) {
            // Replace with actual patient management logic
        } else if (e.getSource() == BillingPaymentMenuItem) {
            // Replace with actual billing and payment handling logic
        } else if (e.getSource() == InventorySupplyMenuItem) {
            // Replace with actual inventory supply handling logic
        } else if (e.getSource() == StaffManagementMenuItem) {
            // Replace with actual staff management handling logic
        }
    }

    public static void main(String[] args) {
        new Admin();
    }
}