package guis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import db_objs.User;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.math.BigDecimal;

public class BankingAppGuiTest {

    private BankingAppGui bankingAppGui;

    @BeforeEach
    public void setUp() {
        User user = new User(1, "testuser", "password123", new BigDecimal("100.00"));
        bankingAppGui = new BankingAppGui(user);
        bankingAppGui.setSize(500, 600);
    }

    @Test
    public void testComponentsInitialization() {

        assertNotNull(bankingAppGui.getCurrentBalanceField(), "Current Balance field should be initialized.");
        assertEquals("$100.00", bankingAppGui.getCurrentBalanceField().getText(), "Balance should be initialized correctly.");


    }




}
