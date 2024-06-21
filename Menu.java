package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu DoctorMenu;
    JMenu ScheduleMenu;
    JMenu ServiceMenu;
    JMenu HomeMenu;
    JMenuItem DoctorMenuItem;
    JMenuItem ScheduleMenuItem;
    JMenuItem ServiceMenuItem;
    JMenuItem HomeMenuItem;

    Menu() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setBounds(520, 200, 500, 400);
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
        DoctorMenu = new JMenu("Doctor Appointment");
        ScheduleMenu = new JMenu("Schedule");
        ServiceMenu = new JMenu("Service Offered");
        HomeMenu = new JMenu("Home");

        DoctorMenuItem = new JMenuItem("Doctor");
        ScheduleMenuItem = new JMenuItem("Schedule");
        ServiceMenuItem = new JMenuItem("Service");
        HomeMenuItem = new JMenuItem("Home");

        DoctorMenuItem.addActionListener(this);
        ScheduleMenuItem.addActionListener(this);
        ServiceMenuItem.addActionListener(this);
        HomeMenuItem.addActionListener(this);

        menuBar.add(DoctorMenuItem);
        menuBar.add(ScheduleMenuItem);
        menuBar.add(ServiceMenuItem);
        menuBar.add(HomeMenuItem);

        // Add the menu bar to the frame
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ScheduleMenuItem) {
            AppointmentWindow appointmentWindow = new AppointmentWindow();
        } else if (e.getSource() == HomeMenuItem) {
            Login_menu menu = new Login_menu();
        } else if (e.getSource() == ServiceMenuItem) {
            // Replace with actual service handling logic
            ServicesWindow servicesWindow = new ServicesWindow();
        } else if (e.getSource() == DoctorMenuItem) {
            DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
        }
    }

}