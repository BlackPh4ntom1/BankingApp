package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends BaseFrame {

    public LoginGui(){
        super("User Login");
    }
    protected void addGuiComponents() {
        JLabel BankingAppLabel = new JLabel("Banking App");
        BankingAppLabel.setBounds(0, 20,super.getWidth() , 40);
        BankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        BankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(BankingAppLabel);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 120,getWidth()-30, 24);
        usernameLabel.setFont(new Font("Dialog", Font. PLAIN, 20));
        add(usernameLabel);

        JTextField usernameField= new JTextField();
        usernameField.setBounds(20, 160,getWidth()-50, 40);
        usernameField.setFont(new Font("Dialog", Font. PLAIN, 20));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 250,getWidth()-50, 24);
        passwordLabel.setFont(new Font("Dialog", Font. PLAIN, 20));
        add(passwordLabel);

        JPasswordField passwordField= new JPasswordField();
        passwordField.setBounds(20, 290,getWidth()-50, 40);
        passwordField.setFont(new Font("Dialog", Font. PLAIN, 20));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20, 390,getWidth()-50, 40);
        loginButton.setFont(new Font("Dialog", Font. BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                User user= MyJDBC.validateLogin(username,password);

                if(user!=null){
                    LoginGui.this.dispose();
                    BankingAppGui bankingAppGui=new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    JOptionPane.showMessageDialog(bankingAppGui,"Login Successful");

                }else{
                    JOptionPane.showMessageDialog(LoginGui.this,"Login Failed...");
                }
            }
        });
        add(loginButton);

        JButton hubButton = new JButton("Hub Menu");
        hubButton.setBounds(20, 460,getWidth()-50, 40);
        hubButton.setFont(new Font("Dialog", Font. BOLD, 20));
        hubButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();
                new HubGui().setVisible(true);
            }
        });
        add(hubButton);

        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0, 510,getWidth()-10, 30);
        registerLabel.setFont(new Font("Dialog", Font. PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();
                new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);
    }


}
