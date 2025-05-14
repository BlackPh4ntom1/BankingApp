package db_objs;
import db_objs.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testConstructorInitializesCorrectly() {
        int id = 1;
        String username = "testuser";
        String password = "password123";
        BigDecimal balance = new BigDecimal("100.00");

        User user = new User(id, username, password, balance);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(balance, user.getCurrentBalance());
    }

    @Test
    public void testSetCurrentBalanceScalesCorrectly() {
        User user = new User(1, "testuser", "password123", new BigDecimal("100.00"));
        BigDecimal newBalance = new BigDecimal("123.456");

        user.setCurrentBalance(newBalance);

        assertEquals(new BigDecimal("123.45"), user.getCurrentBalance());
    }

    @Test
    public void testGettersReturnCorrectValues() {
        User user = new User(2, "john_doe", "securePass", new BigDecimal("500.00"));

        assertEquals(2, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("securePass", user.getPassword());
        assertEquals(new BigDecimal("500.00"), user.getCurrentBalance());
    }
}
