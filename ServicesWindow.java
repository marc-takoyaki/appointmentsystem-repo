package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicesWindow {
    private JFrame frame;

    private JButton panel_box1Button = new JButton();
    private JButton panel_box2Button = new JButton();
    private JButton panel_box3Button = new JButton();

    private JPanel panel_box1 = new JPanel();
    private JPanel panel_box2 = new JPanel();
    private JPanel panel_box3 = new JPanel();

    private JPanel panel_header = new JPanel();
    private JLabel titleLabel = new JLabel("SERVICES");

    public ServicesWindow() {
        frame = new JFrame();
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null); // Using absolute layout for simplicity

        // Panel header
        panel_header.setBackground(new Color(243, 224, 147));
        panel_header.setBounds(0, 0, 700, 90);
        panel_header.setLayout(new BorderLayout());

        // Title label
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        panel_header.add(titleLabel, BorderLayout.CENTER);

        // Panels for boxes
        panel_box1.setBounds(50, 150, 200, 200);
        panel_box1.setBackground(new Color(247, 247, 247));
        panel_box1.setLayout(new BorderLayout());
        panel_box1Button.setOpaque(false);
        panel_box1Button.setContentAreaFilled(false);
        panel_box1Button.setBorderPainted(false);
        panel_box1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Panel 1 clicked!");
            }
        });
        panel_box1.add(panel_box1Button);

        panel_box2.setBounds(280, 150, 200, 200);
        panel_box2.setBackground(new Color(247, 247, 247));
        panel_box2.setLayout(new BorderLayout());
        panel_box2Button.setOpaque(false);
        panel_box2Button.setContentAreaFilled(false);
        panel_box2Button.setBorderPainted(false);
        panel_box2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Panel 2 clicked!");
            }
        });
        panel_box2.add(panel_box2Button);

        panel_box3.setBounds(510, 150, 200, 200);
        panel_box3.setBackground(new Color(247, 247, 247));
        panel_box3.setLayout(new BorderLayout());
        panel_box3Button.setOpaque(false);
        panel_box3Button.setContentAreaFilled(false);
        panel_box3Button.setBorderPainted(false);
        panel_box3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Panel 3 clicked!");
            }
        });
        panel_box3.add(panel_box3Button);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create a menu
        JMenu homeMenu = new JMenu("Home");
        menuBar.add(homeMenu);

        // Create a menu item for home
        JMenuItem homeMenuItem = new JMenuItem("Go to Home");
        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle going back to home
                frame.dispose(); // Close the current window
                new Admin(); // Open the main menu
            }
        });
        homeMenu.add(homeMenuItem);

        frame.add(panel_header);
        frame.add(panel_box1);
        frame.add(panel_box2);
        frame.add(panel_box3);

        frame.setVisible(true);
    }


}
