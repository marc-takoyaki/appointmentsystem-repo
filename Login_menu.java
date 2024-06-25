package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_menu implements ActionListener {

    private JFrame frame;
    private JButton button;
    private JLabel label1;
    private JLabel label;
    private JPanel panel;
    private JTextField textField;
    private JPasswordField passwordField;

    public Login_menu() {
        frame = new JFrame("Login Menu");
        frame.setSize(500, 400);
        frame.setBounds(520, 200, 500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null); // Using absolute layout for simplicity, not recommended for complex layouts
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        JLabel titleLabel = new JLabel("Appointment Scheduler");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBounds(150, 60, 250, 30);
        panel.add(titleLabel);

        label = new JLabel("Username:");
        label.setBounds(150, 110, 80, 20);
        panel.add(label);

        textField = new JTextField();
        textField.setBounds(220, 110, 150, 20);
        panel.add(textField);

        label1 = new JLabel("Password:");
        label1.setBounds(150, 140, 80, 20);
        panel.add(label1);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 140, 150, 20);
        panel.add(passwordField);

        button = new JButton("Login");
        button.setBounds(220, 180, 100, 20);
        button.addActionListener(this);
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String username = textField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.equals("marc") && password.equals("ebreo")) {
                JOptionPane.showMessageDialog(frame, "Welcome!");

                // Open the main menu window
                Menu menu = new Menu();
                frame.dispose(); // Close the login window after successful login
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
