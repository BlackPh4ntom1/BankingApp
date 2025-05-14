package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLog extends BaseFrame {
    public AdminLog() {
        super("Admin Login");
        addGuiComponents();
    }

    protected void addGuiComponents() {
        setLayout(null); // Set layout to null for absolute positioning

        JLabel adminLabel = new JLabel("Admin:");
        adminLabel.setBounds(25, 185, 250, 24);
        adminLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(adminLabel);

        JTextField adminField = new JTextField();
        adminField.setBounds(100, 180, 250, 40);
        adminField.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(adminField);

        JLabel adminpassLabel = new JLabel("Password:");
        adminpassLabel.setBounds(15, 295, 250, 24);
        adminpassLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(adminpassLabel);

        JPasswordField adminpassField = new JPasswordField();
        adminpassField.setBounds(100, 290, 250, 40);
        adminpassField.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(adminpassField);

        JButton AdminlogButton = new JButton("Login");
        AdminlogButton.setBounds(20, 440, getWidth()-50, 40);
        AdminlogButton.setFont(new Font("Dialog", Font.BOLD, 20));
        AdminlogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Admin= "Andrei";
                String Password= "12345";
                String username = adminField.getText();
                String password = String.valueOf(adminpassField.getPassword());
                User user= MyJDBC.validateLogin(username,password);



                if(Admin.equals(username) && password.equals(Password)){
                    AdminLog.this.dispose();
                    AdminAppGui adminAppGui=new AdminAppGui(user);
                    adminAppGui.setVisible(true);

                    JOptionPane.showMessageDialog(adminAppGui,"Login Successful");

                }else{
                    JOptionPane.showMessageDialog(AdminLog.this,"Login Failed...");
                }
            }
        });
        add(AdminlogButton);

        JButton HubButton = new JButton("Hub Menu");
        HubButton.setBounds(20, 500, getWidth()-50, 40);
        HubButton.setFont(new Font("Dialog", Font.BOLD, 20));
        HubButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AdminLog.this.dispose();
                new HubGui().setVisible(true);
            }
        });
        add(HubButton);

        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("C:/Users/Andrei/IdeaProjects/BankingApp/src/main/resources/bank.png");
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        imagePanel.setBounds(150, 60, 100, 100); // Set bounds for the image panel
        add(imagePanel);

        setSize(420,620 ); // Set the frame size

    }


}
