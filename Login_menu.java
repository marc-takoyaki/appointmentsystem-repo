package Appointmentsystem_package;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_menu implements ActionListener {

    //this makes the scope bigger for these variables
    private JFrame frame;
    private JButton button;
    private JLabel label1;
    private JLabel label;
    private JPanel panel;
    private JTextField textField;
    private JPasswordField passwordField;

    public Login_menu() {
        // this woudl create a new frame
        frame = new JFrame("Login Menu");

        // Set frame attributes
        frame.setSize(500, 400);
        frame.setBounds(520, 200, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        // tis would creat a new panel named "panel"
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);

        // Add the panel to the frame
        frame.add(panel);


        JLabel titleLabel = new JLabel("Appointment Scheduler");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBounds(150, 60, 250, 30);
        panel.add(titleLabel);


        // username label
        label = new JLabel("Username");
        label.setBounds(150, 80, 80, 80);
        panel.add(label);



        textField = new JTextField(20);
        textField.setBounds(220, 110, 100, 20);
        panel.add(textField);

        label1 = new JLabel("Password");
        label1.setBounds(150, 140, 80, 80);
        panel.add(label1);


        passwordField = new JPasswordField(20);
        passwordField.setBounds(220, 170, 100, 20);
        panel.add(passwordField);


        button = new JButton("Login");
        button.setBounds(220, 220, 100, 20);
        button.addActionListener(this);
        panel.add(button);

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Handle login action
        String username = textField.getText();
        String password = new String(passwordField.getPassword());
        System.out.println("Username: " + username + ", Password: " + password);

        if(username.equals("marc") && password.equals("ebreo")){

           if(e.getSource()==button){

                //opens new window
               MenuWindow menu = new MenuWindow();
//               frame.dispose();


           }
        }
        else{

            System.out.println("wrong credentials");
        }
    }

    public static void main(String[] args) {
        // Run the login menu
        new Login_menu();
    }
}
