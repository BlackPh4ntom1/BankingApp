package db_objs;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class MyJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bankingapp";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "ghiozdanul1";

    public static User validateLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userid = resultSet.getInt("id");
                BigDecimal currentBalance = resultSet.getBigDecimal("current_balance");
                return new User(userid, username, password, currentBalance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(String username, String password) {
        try {
            if(checkUser(username)){
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password,current_balance)"+ "VALUES (?, ?, ?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setBigDecimal(3,new BigDecimal(0));
                preparedStatement.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean checkUser(String username) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    private static boolean checkExist(String username) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If resultSet has a row, the user exists, so we can delete them
            if (resultSet.next()) {
                return true;  // User exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // User does not exist
    }


    public static boolean addTransactionToDatabase(Transaction transaction) {
        try{
            Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement insertTransaction = connection.prepareStatement("INSERT INTO transactions(user_id, transaction_type, transaction_amount, transaction_date)" +
                    "VALUES(? , ?, ?,NOW())");

            insertTransaction.setInt(1,transaction.getUserId());
            insertTransaction.setString(2,transaction.getTransactionType());
            insertTransaction.setBigDecimal(3,transaction.getTransactionAmount());

            insertTransaction.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateCurrentBalance(User user){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement updateBalance = connection.prepareStatement("UPDATE users SET current_balance = ? WHERE id = ?");
            updateBalance.setBigDecimal(1, user.getCurrentBalance());
            updateBalance.setInt(2, user.getId());
            updateBalance.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean transfer(User user, String transferredUsername, float transferAmount){
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);

            PreparedStatement queryUser = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            queryUser.setString(1, transferredUsername);
            ResultSet resultSet = queryUser.executeQuery();

            while(resultSet.next()){
                User transferredUser = new User(resultSet.getInt("id"), transferredUsername, resultSet.getString("password"), resultSet.getBigDecimal("current_balance"));
                Transaction transferTransaction = new Transaction(user.getId(),"Transfer",new BigDecimal(transferAmount),null);
                Transaction receivedTransaction = new Transaction(transferredUser.getId(),"Transfer",new BigDecimal(transferAmount),null);

                transferredUser.setCurrentBalance(transferredUser.getCurrentBalance().add(BigDecimal.valueOf(transferAmount)));
                updateCurrentBalance(transferredUser);

                user.setCurrentBalance(user.getCurrentBalance().subtract(BigDecimal.valueOf(transferAmount)));
                updateCurrentBalance(user);
                addTransactionToDatabase(transferTransaction);
                addTransactionToDatabase(receivedTransaction);

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Transaction> getPastTransactions(User user){
        ArrayList<Transaction> pastTransactions = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement selectAllTransaction = connection.prepareStatement("SELECT * FROM transactions WHERE user_id = ?");
            selectAllTransaction.setInt(1,user.getId());
            ResultSet resultSet = selectAllTransaction.executeQuery();
            while(resultSet.next()){
                Transaction transaction = new Transaction(user.getId(), resultSet.getString("transaction_type"),resultSet.getBigDecimal("transaction_amount"),resultSet.getDate("transaction_date"));
                pastTransactions.add(transaction);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pastTransactions;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement selectAllUsers = connection.prepareStatement("SELECT * FROM users")) {

            ResultSet resultSet = selectAllUsers.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBigDecimal("current_balance")
                );
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }


    public static boolean delete(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // First, delete all transactions for the user
            String deleteTransactionsQuery = "DELETE FROM transactions WHERE user_id = (SELECT id FROM users WHERE username = ?)";
            try (PreparedStatement deleteTransactionsStatement = connection.prepareStatement(deleteTransactionsQuery)) {
                deleteTransactionsStatement.setString(1, username);
                deleteTransactionsStatement.executeUpdate();
            }

            // Then, delete the user
            if (checkExist(username)) {
                String query = "DELETE FROM users WHERE username = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}