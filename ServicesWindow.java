package Appointmentsystem_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicesWindow {
    private JFrame frame;

    public ServicesWindow() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 4, 10, 10)); // Grid layout for service panels

        // Create dental care service panels
        createDentalCarePanel("Root Canal Treatment", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed maximus turpis non velit convallis, sed tempus risus vehicula.",
                "$150", "path/to/root_canal.jpg");
        createDentalCarePanel("Cosmetic Dentistry", "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.",
                "$200", "path/to/cosmetic_dentistry.jpg");
        createDentalCarePanel("Dental Crown", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.",
                "$180", "path/to/dental_crown.jpg");
        createDentalCarePanel("Tooth Whitening", "Nunc a ligula eget metus aliquet pharetra vel vel ipsum.",
                "$120", "path/to/tooth_whitening.jpg");
        createDentalCarePanel("Dental Implants", "Vivamus tincidunt, risus vitae tristique porttitor, purus tellus fermentum felis, at tempus ligula libero sed dui.",
                "$300", "path/to/dental_implants.jpg");
        createDentalCarePanel("Dental Bridge", "Ut semper quam et odio ornare, nec ultrices elit feugiat.",
                "$250", "path/to/dental_bridge.jpg");
        createDentalCarePanel("Periodontics", "Fusce dictum diam quis tortor gravida, vel aliquet metus laoreet.",
                "$170", "path/to/periodontics.jpg");
        createDentalCarePanel("Denture", "Nam blandit eros sit amet justo ultrices, in hendrerit orci ultricies.",
                "$220", "path/to/denture.jpg");

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
                new Menu(); // Open the main menu
            }
        });
        homeMenu.add(homeMenuItem);

        frame.setVisible(true);
    }

    private void createDentalCarePanel(String serviceName, String serviceDetails, String cost, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(serviceName);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(nameLabel, BorderLayout.NORTH);

        JTextArea detailsTextArea = new JTextArea(serviceDetails);
        detailsTextArea.setWrapStyleWord(true);
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel costLabel = new JLabel("Price: " + cost);
        costLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(costLabel, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Image");
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open image based on the provided path or handle image display
                // For simplicity, this example does not implement image loading
                JOptionPane.showMessageDialog(frame, "Open image: " + imagePath);
            }
        });
        panel.add(imageButton, BorderLayout.EAST);

        frame.add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ServicesWindow::new);
    }
}
