package guis;

import db_objs.MyJDBC;
import db_objs.Transaction;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BankingAppDialog extends JDialog implements ActionListener {
    private User user;
    private BankingAppGui bankingAppGui;
    private AdminAppGui adminAppGui;
    private JLabel balanceLabel, enterAmountLabel, enterUserLabel,deleteUserLabel;
    private JTextField enterAmountField, enterUserField,deleteUserField;
    private JButton actionButton;
    private JPanel pastTransactionPanel,userViewComponents;
    private ArrayList<Transaction> pastTransactions;
    private ArrayList<User> pastUsers;
    private HubGui hubGui;

    public BankingAppDialog(BaseFrame parent, User user) {
        setSize(400, 400);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        this.user = user;

        if (parent instanceof BankingAppGui) {
            this.bankingAppGui = (BankingAppGui) parent;
        } else if (parent instanceof AdminAppGui) {
            this.adminAppGui = (AdminAppGui) parent;
        }
    }

    public JButton getActionButton() {
        return actionButton;
    }




    public void addCurrentBalanceAmount() {
        balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
        balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
        balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        enterAmountLabel = new JLabel("Enter Amount: " + user.getCurrentBalance());
        enterAmountLabel.setBounds(0, 50, getWidth() - 20, 20);
        enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

        enterAmountField = new JTextField();
        enterAmountField.setBounds(15, 80, getWidth() - 50, 40);
        enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);
    }

    public void addActionButton(String actionButtonType) {
        actionButton = new JButton(actionButtonType);
        actionButton.setBounds(15, 300, getWidth() - 50, 40);
        actionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        actionButton.addActionListener(this);
        add(actionButton);
    }

    public void addUserField() {
        enterUserLabel = new JLabel("Enter User:");
        enterUserLabel.setBounds(0, 160, getWidth() - 20, 20);
        enterUserLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserLabel);

        enterUserField = new JTextField();
        enterUserField.setBounds(15, 190, getWidth() - 50, 40);
        enterUserField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserField);
    }

    public void addPastTransactionComponents() {
        pastTransactionPanel = new JPanel();
        pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(pastTransactionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 20, getWidth() - 15, getHeight() - 60);

        pastTransactions = MyJDBC.getPastTransactions(user);

        for (Transaction pastTransaction : pastTransactions) {
            JPanel pastTransactionContainer = new JPanel();
            pastTransactionContainer.setLayout(new BorderLayout());

            JLabel transactionTypeLabel = new JLabel(pastTransaction.getTransactionType());
            transactionTypeLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel transactionAmountLabel = new JLabel(String.valueOf(pastTransaction.getTransactionAmount()));
            transactionAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel transactionDateLabel = new JLabel(String.valueOf(pastTransaction.getTransactionDate()));
            transactionDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            pastTransactionContainer.add(transactionTypeLabel, BorderLayout.WEST);
            pastTransactionContainer.add(transactionAmountLabel, BorderLayout.EAST);
            pastTransactionContainer.add(transactionDateLabel, BorderLayout.SOUTH);

            pastTransactionContainer.setBackground(Color.WHITE);
            pastTransactionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pastTransactionPanel.add(pastTransactionContainer);
        }
        pastTransactionPanel.revalidate();
        pastTransactionPanel.repaint();
        add(scrollPane);
    }

    public void addUserViewComponents() {
        userViewComponents = new JPanel();
        userViewComponents.setLayout(new BoxLayout(userViewComponents, BoxLayout.Y_AXIS));
        JScrollPane scrollPane1 = new JScrollPane(userViewComponents);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(0, 20, getWidth() - 15, getHeight() -60);

        pastUsers = MyJDBC.getAllUsers();

        for (User pastUsers : pastUsers) {
            JPanel pastUserContainer = new JPanel();
            pastUserContainer.setLayout(new BorderLayout());

            JLabel usernameLabel = new JLabel(pastUsers.getUsername());
            usernameLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel passwordLabel = new JLabel(String.valueOf(pastUsers.getPassword()));
            passwordLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            JLabel currentBalanceLabel = new JLabel(String.valueOf(pastUsers.getCurrentBalance()));
            currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            pastUserContainer.add(usernameLabel, BorderLayout.WEST);
            pastUserContainer.add(passwordLabel, BorderLayout.SOUTH);
            pastUserContainer.add(currentBalanceLabel, BorderLayout.EAST);

            pastUserContainer.setBackground(Color.WHITE);
            pastUserContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            userViewComponents.add(pastUserContainer);
        }
        userViewComponents.revalidate();
        userViewComponents.repaint();
        add(scrollPane1);
    }

    public void deleteViewComponents() {

        deleteUserLabel = new JLabel("Enter User:");
        deleteUserLabel.setBounds(0, 160, getWidth() - 20, 20);
        deleteUserLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        deleteUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(deleteUserLabel);

        deleteUserField = new JTextField();
        deleteUserField.setBounds(15, 190, getWidth() - 50, 40);
        deleteUserField.setFont(new Font("Dialog", Font.BOLD, 20));
        deleteUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(deleteUserField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(15, 250,getWidth()-50, 40);
        deleteButton.setFont(new Font("Dialog", Font. BOLD, 20));


        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete(user);
            }
        });
        add(deleteButton);
    }
    private void handleTransaction(String transactionType, int amountVal) {
        Transaction transaction;
        if (transactionType.equalsIgnoreCase("Deposit")) {
            user.setCurrentBalance(user.getCurrentBalance().add(new BigDecimal(amountVal)));
            transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);
        } else {
            user.setCurrentBalance(user.getCurrentBalance().subtract(new BigDecimal(amountVal)));
            transaction = new Transaction(user.getId(), transactionType, new BigDecimal(amountVal), null);
        }
        if (MyJDBC.addTransactionToDatabase(transaction) && MyJDBC.updateCurrentBalance(user)) {
            JOptionPane.showMessageDialog(this, transactionType + " Successfully");
            resetFieldsAndUpdateCurrentBalance();
        } else {
            JOptionPane.showMessageDialog(this, transactionType + " Failed...");
        }
    }

    private void resetFieldsAndUpdateCurrentBalance() {
        enterAmountField.setText("");
        if (enterUserField != null) {
            enterUserField.setText("");
        }
        balanceLabel.setText("Balance: $" + user.getCurrentBalance());
        bankingAppGui.getCurrentBalanceField().setText("$" + user.getCurrentBalance());
    }

    private void handleTransfer(User user, String transferredUser, int amount) {
        if (MyJDBC.transfer(user, transferredUser, amount)) {
            JOptionPane.showMessageDialog(this, "Transfer Successfully!");
            resetFieldsAndUpdateCurrentBalance();
        } else {
            JOptionPane.showMessageDialog(this, "Transfer Failed...");
        }
    }
    public void handleDelete(User user) {
        if (MyJDBC.delete(user.getUsername())) {
            JOptionPane.showMessageDialog(this, "Delete Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Delete Failed...");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand().trim();
        try {
            String amountText = enterAmountField.getText().trim();
            if (amountText.isEmpty()) {
                throw new NumberFormatException("Amount field is empty");
            }

            BigDecimal amountVal = new BigDecimal(amountText);

            // Check for negative or zero values
            if (amountVal.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount greater than zero.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convert BigDecimal to int for methods that require int
            int amountInt = amountVal.intValue();

            switch (buttonPressed.toLowerCase()) {
                case "deposit":
                    handleTransaction("Deposit", amountInt);
                    break;

                case "withdraw":
                    if (user.getCurrentBalance().compareTo(amountVal) < 0) {
                        JOptionPane.showMessageDialog(this, buttonPressed + " value is more than current balance.");
                        return;
                    }
                    handleTransaction("Withdraw", amountInt);
                    break;

                case "transfer":
                    if (user.getCurrentBalance().compareTo(amountVal) < 0) {
                        JOptionPane.showMessageDialog(this, buttonPressed + " value is more than current balance.");
                        return;
                    }

                    String transferredUser = enterUserField.getText().trim();
                    if (transferredUser.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter a recipient for transfer.", "Missing Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Prevent negative values in transfer
                    if (amountVal.compareTo(BigDecimal.ZERO) <= 0) {
                        JOptionPane.showMessageDialog(this, "Transfer amount must be greater than zero.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    handleTransfer(user, transferredUser, amountInt);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Unknown action: " + buttonPressed, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

}
