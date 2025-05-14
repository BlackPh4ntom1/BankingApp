package guis;


import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class BaseFrameTest {

    @Test
    public void testConstructorWithUserInitializesWithoutException() {
        // Create a User object with appropriate parameters (id, username, password, currentBalance)
        User user = new User(1, "testUser", "testPassword", new java.math.BigDecimal("1000.00"));
        assertDoesNotThrow(() -> {
            BaseFrame frame = new DummyFrame("Test Title", user);
            assertNotNull(frame);
            assertEquals("Test Title", frame.getTitle());
        });
    }

    @Test
    public void testConstructorWithoutUserInitializesWithoutException() {
        assertDoesNotThrow(() -> {
            BaseFrame frame = new DummyFrame("Test Title");
            assertNotNull(frame);
            assertEquals("Test Title", frame.getTitle());
        });
    }

    @Test
    public void testFrameProperties() {
        User user = new User(1, "testUser", "testPassword", new java.math.BigDecimal("1000.00"));
        BaseFrame frame = new DummyFrame("Test Title", user);

        assertEquals(420, frame.getSize().width);
        assertEquals(600, frame.getSize().height);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertFalse(frame.isResizable());
    }

    // DummyFrame is a concrete subclass for testing the abstract BaseFrame
    static class DummyFrame extends BaseFrame {
        public DummyFrame(String title, User user) {
            super(title, user);
        }

        public DummyFrame(String title) {
            super(title);
        }

        @Override
        protected void addGuiComponents() {
            // No-op for testing purposes
        }
    }
}
