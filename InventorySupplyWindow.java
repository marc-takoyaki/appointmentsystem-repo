package Appointmentsystem_package;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InventorySupplyWindow extends JFrame {

    private JPanel supplyPanel;
    private JButton menuButton; // Button to access the main menu
    private Map<String, JTextField> supplyNameFields = new HashMap<>();
    private Map<String, JTextField> supplyQuantityFields = new HashMap<>();
    private Map<String, Integer> supplyCount = new HashMap<>();

    public InventorySupplyWindow() {
        setTitle("Inventory / Supply");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false); // Prevent resizing for simplicity

        // Simulating some initial supplies
        supplyCount.put("Syringes", 50);
        supplyCount.put("Bandages", 100);
        supplyCount.put("Gloves", 200);
        supplyCount.put("Medication", 30);

        // Panel for holding controls
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Button to access the main menu
        menuButton = new JButton("Main Menu");
        menuButton.addActionListener(e -> showMainMenu());
        controlPanel.add(menuButton);

        add(controlPanel, BorderLayout.NORTH);

        // Panel for displaying supply list dynamically
        supplyPanel = new JPanel();
        supplyPanel.setLayout(new GridLayout(supplyCount.size(), 3, 10, 10));
        supplyPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (Map.Entry<String, Integer> entry : supplyCount.entrySet()) {
            String supplyName = entry.getKey();
            int count = entry.getValue();

            // Supply name field
            JTextField nameField = new JTextField(supplyName);
            nameField.setEditable(false); // Prevent editing supply names
            supplyPanel.add(nameField);
            supplyNameFields.put(supplyName, nameField);

            // Supply quantity field
            JTextField quantityField = new JTextField(String.valueOf(count));
            supplyPanel.add(quantityField);
            supplyQuantityFields.put(supplyName, quantityField);

            // Update button
            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(e -> updateSupply(supplyName));
            supplyPanel.add(updateButton);
        }

        JScrollPane scrollPane = new JScrollPane(supplyPanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateSupply(String supplyName) {
        JTextField quantityField = supplyQuantityFields.get(supplyName);
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            int previousQuantity = supplyCount.get(supplyName);
            supplyCount.put(supplyName, quantity);
            displayUpdateMessage(String.format("%s updated: Quantity changed from %d to %d", supplyName, previousQuantity, quantity));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for " + supplyName,
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }

        // Update the displayed quantity fields after update
        updateSupplyList();
    }

    private void updateSupplyList() {
        for (Map.Entry<String, JTextField> entry : supplyQuantityFields.entrySet()) {
            String supplyName = entry.getKey();
            JTextField quantityField = entry.getValue();
            int quantity = supplyCount.get(supplyName);
            quantityField.setText(String.valueOf(quantity));
        }
    }

    private void displayUpdateMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Supply Update", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMainMenu() {
        new Admin();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventorySupplyWindow::new);
    }
}
