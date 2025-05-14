package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HubGui extends BaseFrame{
    public HubGui() {
        super("Banking App Hub");
    }

    @Override
    protected void addGuiComponents() {

        JLabel BankingAppLabel = new JLabel("Banking App");
        BankingAppLabel.setBounds(0, 20,super.getWidth() , 40);
        BankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        BankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(BankingAppLabel);

        setLayout(null); // Use null layout for absolute positioning

        JLabel qtLabel = new JLabel("Choose the suited profile");
        qtLabel.setBounds(15, 160, getWidth()-50, 24);
        qtLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        qtLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(qtLabel);

        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(15, 200, getWidth()-50, 40);
        adminButton.setFont(new Font("Dialog", Font.BOLD, 20));
        add(adminButton);

        JButton userButton = new JButton("User");
        userButton.setBounds(15, 270, getWidth()-50, 40);
        userButton.setFont(new Font("Dialog", Font.BOLD, 20));
        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HubGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(userButton);

        JPanel imagePanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("C:/Users/Andrei/IdeaProjects/BankingApp/src/main/resources/bank.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        imagePanel.setBounds(150, 350, 100, 100); // Set bounds for the image panel
        add(imagePanel);



        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HubGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });
        add(userButton);

        adminButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HubGui.this.dispose();
                new AdminLog().setVisible(true);
            }
        });
        add(adminButton);
    }



}
