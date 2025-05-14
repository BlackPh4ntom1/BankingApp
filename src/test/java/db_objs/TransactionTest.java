package db_objs;

import db_objs.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testConstructorInitializesCorrectly() {
        int userId = 1;
        String transactionType = "Deposit";
        BigDecimal transactionAmount = new BigDecimal("250.00");
        Date transactionDate = Date.valueOf("2023-01-01");

        Transaction transaction = new Transaction(userId, transactionType, transactionAmount, transactionDate);

        assertEquals(userId, transaction.getUserId());
        assertEquals(transactionType, transaction.getTransactionType());
        assertEquals(transactionAmount, transaction.getTransactionAmount());
        assertEquals(transactionDate, transaction.getTransactionDate());
    }

    @Test
    public void testGetUserId() {
        Transaction transaction = new Transaction(2, "Withdrawal", new BigDecimal("150.00"), Date.valueOf("2023-01-02"));

        assertEquals(2, transaction.getUserId());
    }

    @Test
    public void testGetTransactionType() {
        Transaction transaction = new Transaction(3, "Transfer", new BigDecimal("500.00"), Date.valueOf("2023-01-03"));

        assertEquals("Transfer", transaction.getTransactionType());
    }

    @Test
    public void testGetTransactionAmount() {
        Transaction transaction = new Transaction(4, "Deposit", new BigDecimal("300.50"), Date.valueOf("2023-01-04"));

        assertEquals(new BigDecimal("300.50"), transaction.getTransactionAmount());
    }

    @Test
    public void testGetTransactionDate() {
        Date date = Date.valueOf("2023-01-05");
        Transaction transaction = new Transaction(5, "Payment", new BigDecimal("75.25"), date);

        assertEquals(date, transaction.getTransactionDate());
    }
}
