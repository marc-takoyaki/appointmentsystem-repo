package Appointmentsystem_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServicesWindow implements ActionListener{
    JFrame frame = new JFrame();

    JPanel panel_box1 = new JPanel();
    JPanel panel_box2 = new JPanel();
    JPanel panel_box3 = new JPanel();

    JPanel panel_header = new JPanel();
    //panel within header
    JPanel panel2 = new JPanel();

    //title
    JLabel label = new JLabel();

    //panel 2 button (navigation)
    JButton button_doctor = new JButton("Doctor");
    JButton button_schedule = new JButton("Schedule");
    JButton button_services = new JButton("services");
    JButton button_home = new JButton("home");

    ServicesWindow(){

        frame.setSize(700, 700);
        frame.setBounds(520, 200, 500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        //panel_header
        panel_header.setBackground(new Color(243,224,147));
        panel_header.setPreferredSize(new Dimension(50,80));
        panel_header.setBounds(0, 0, 500, 90);
        panel_header.setBackground(new Color(243,224,147));
        panel_header.setPreferredSize(new Dimension(50,80));
        panel_header.setLayout(null);
        panel_header.setBounds(0, 0, 500, 90);

        //panel within header
        panel2.setBackground(new Color(225, 224, 224));
        panel2.setBounds(110, 20,350, 40);
        panel2.setLayout(new FlowLayout());

        //add panel2
        panel_header.add(panel2);

        //navigation button
        button_doctor.addActionListener(this);
        button_schedule.addActionListener(this);
        button_services.addActionListener(this);
        button_home.addActionListener(this);

        //add button to panel2
        panel2.add(button_doctor);
        panel2.add(button_schedule);
        panel2.add(button_services);
        panel2.add(button_home);

        //title
        label.setText("SERVICES");
        label.setBounds(200, 30, 150, 200);

        panel_box1.setBounds(10, 150, 150, 200);
        panel_box1.setBackground(new Color(247, 247, 247));

        panel_box2.setBounds(170, 150, 150, 200);
        panel_box2.setBackground(new Color(247, 247, 247));

        panel_box3.setBounds(330, 150, 150, 200);
        panel_box3.setBackground(new Color(247, 247, 247));

        frame.add(panel_header);
        frame.add(label);
        frame.add(panel_box3);
        frame.add(panel_box2);
        frame.add(panel_box1);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button_home) {
            new Menu() {};
        }
        if(e.getSource()==button_doctor) {
            new DoctorSpecialization() {};
        }
        if(e.getSource()==button_schedule) {
            new AppointmentWindow() {};
        }

    }
}
