package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu DoctorMenu;
    private JMenu ScheduleMenu;
    private JMenu ServiceMenu;
    private JMenu HomeMenu;
    private JMenuItem DoctorMenuItem;
    private JMenuItem ScheduleMenuItem;
    private JMenuItem ServiceMenuItem;
    private JMenuItem HomeMenuItem;
    private JButton logoutButton; // New logout button

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


        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned panel
        logoutButton = new JButton("Logout");

        logoutButton.addActionListener(this); // Add action listener for logout button

        logoutPanel.add(logoutButton);
        this.add(logoutPanel, BorderLayout.SOUTH); // Add logout panel to the bottom of the frame

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ScheduleMenuItem) {
            AppointmentWindow appointmentWindow = new AppointmentWindow();
            this.dispose();
        } else if (e.getSource() == HomeMenuItem) {
            HomeWindow homeWindow = new HomeWindow();
            this.dispose();
        } else if (e.getSource() == ServiceMenuItem) {
            ServicesWindow servicesWindow = new ServicesWindow();
            this.dispose();
        } else if (e.getSource() == DoctorMenuItem) {
            DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
            this.dispose();
        } else if (e.getSource() == logoutButton) {
            Login_menu menu = new Login_menu();
            this.dispose();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);


}


}
