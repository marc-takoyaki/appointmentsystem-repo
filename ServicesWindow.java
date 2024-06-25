package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicesWindow {
    JFrame frame = new JFrame();

    JButton panel_box1Button = new JButton();
    JButton panel_box2Button = new JButton();
    JButton panel_box3Button = new JButton();

    JPanel panel_box1 = new JPanel();
    JPanel panel_box2 = new JPanel();
    JPanel panel_box3 = new JPanel();

    JPanel panel_header = new JPanel();
    JLabel titleLabel = new JLabel("SERVICES");

    ServicesWindow() {
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

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
                // Handle click action for panel_box1
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
                // Handle click action for panel_box2
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
                // Handle click action for panel_box3
                JOptionPane.showMessageDialog(frame, "Panel 3 clicked!");
            }
        });
        panel_box3.add(panel_box3Button);

        frame.add(panel_header);
        frame.add(panel_box1);
        frame.add(panel_box2);
        frame.add(panel_box3);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServicesWindow::new);
    }
}
