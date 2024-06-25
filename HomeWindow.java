package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;

class HomeWindow extends JFrame {

    private JButton loginButton;

    public HomeWindow() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Home Menu");
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        getContentPane().setBackground(new Color(135, 206, 250)); // Set background color to light blue


        JPanel midPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load an image from the path (replace with your image path)
                ImageIcon imageIcon = new ImageIcon("path_to_your_image.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        midPanel.setLayout(new BorderLayout());

        // Create a panel for the login button at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            // Handle login button action here
            if(e.getSource() == loginButton) {
                Login_menu login = new Login_menu();
                dispose();
            }
        });

        topPanel.add(loginButton);

        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);

        setVisible(true);
    }


}
