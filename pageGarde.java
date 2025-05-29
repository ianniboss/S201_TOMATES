package ihm;

import javax.swing.*;
import java.awt.*;

public class pageGarde extends JFrame {

    public pageGarde() {
        // Frame setup
        setTitle("Ô'Tomates");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load background image
        Image backgroundImage = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\backgroundtomate2.jpg").getImage();

        // Custom JPanel that paints a scaled background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Add vertical spacing (top margin)
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 80))); // Push content down

        // Title label
        JLabel titleLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\TOMATESICON.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH); 
        titleLabel.setIcon(new ImageIcon(scaledImage));        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        backgroundPanel.add(titleLabel);
        
        // Commencer button
     // Load and scale the image for the button
        ImageIcon buttonIcon = new ImageIcon("D:\\ian\\SEM2\\S201\\programmation_SAE_S2-01_2025_GX_Y\\src\\main\\resources\\images\\commencerbutton.png");
        Image scaledButtonImage = buttonIcon.getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(scaledButtonImage);



        // More spacing below the title
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
                // Create the button with the scaled icon
                JButton startButton = new JButton(scaledIcon);
                startButton.setBorderPainted(false);
                startButton.setContentAreaFilled(false);
                startButton.setFocusPainted(false);
                startButton.setOpaque(false);
                startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                backgroundPanel.add(startButton);

        // Add remaining space below button
        backgroundPanel.add(Box.createVerticalGlue());

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(pageGarde::new);
    }
}
