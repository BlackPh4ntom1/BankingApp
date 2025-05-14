package guis;

import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AdminAppGui extends BaseFrame implements ActionListener {


    private JTextField currentBalanceField;
    public JTextField getCurrentBalanceField() {
        return currentBalanceField;
    }

    public AdminAppGui(User user) {
        super("Banking App",user);
    }
    @Override
    protected void addGuiComponents() {
        String welcomeMessage = "<html>"+"<body style='text-align:center'>"+"<b>Hello " /*+user.getUsername()*/+"</b><br>"+"What would you like to do today?</body></html>";
        JLabel welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setBounds(0,20,getWidth()-10,40);
        welcomeMessageLabel.setFont(new Font("Dialog",Font.PLAIN,16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);



        JButton depositButton = new JButton("View Accounts");
        depositButton.setBounds(15,180,getWidth()-50,50);
        depositButton.setFont(new Font("Dialog",Font.BOLD,22));
        depositButton.addActionListener(this);
        add(depositButton);

        JButton withdrawButton = new JButton("Register Accounts");
        withdrawButton.setBounds(15,250,getWidth()-50,50);
        withdrawButton.setFont(new Font("Dialog",Font.BOLD,22));
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        JButton pastTransactionsButton = new JButton("Delete Accounts");
        pastTransactionsButton.setBounds(15,320,getWidth()-50,50);
        pastTransactionsButton.setFont(new Font("Dialog",Font.BOLD,22));
        pastTransactionsButton.addActionListener(this);
        add(pastTransactionsButton);


        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(15,500,getWidth()-50,50);
        logoutButton.setFont(new Font("Dialog",Font.BOLD,22));
        logoutButton.addActionListener(this);
        add(logoutButton);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand().trim().toLowerCase();
        BankingAppDialog bankingAppDialog = new BankingAppDialog(this, user);

        switch (buttonPressed) {
            case "logout":
                handleLogout();
                break;

            case "register accounts":
                handleRegisterAccounts();
                break;

            case "view accounts":
                bankingAppDialog.addUserViewComponents();
                bankingAppDialog.setVisible(true);
                break;

            case "delete accounts":
                bankingAppDialog.deleteViewComponents();
                bankingAppDialog.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown action: " + buttonPressed, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }



    private void handleLogout() {
        new HubGui().setVisible(true);
        this.dispose();
    }

    private void handleRegisterAccounts() {
        new RegisterGui().setVisible(true);
        this.dispose();
    }



}
