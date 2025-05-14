package guis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.Component;

import static org.junit.jupiter.api.Assertions.*;

class HubGuiTest {

    private HubGui hubGui;

    @BeforeEach
    void setUp() throws Exception {
        // Create a new instance of the HubGui
        hubGui = new HubGui();
        hubGui.setSize(800, 600);


        SwingUtilities.invokeAndWait(() -> {
            hubGui.setVisible(true);
        });
    }

    @Test
    void testComponentsExist() {

        JComponent contentPane = (JComponent) hubGui.getContentPane();


        JButton adminButton = (JButton) findComponentByName(contentPane, "Admin");
        assertNotNull(adminButton, "Admin button should exist");
        assertEquals("Admin", adminButton.getText(), "Admin button should have correct text");


        JButton userButton = (JButton) findComponentByName(contentPane, "User");
        assertNotNull(userButton, "User button should exist");
        assertEquals("User", userButton.getText(), "User button should have correct text");
    }


    private JComponent findComponentByName(JComponent container, String name) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JComponent) {
                JComponent component = (JComponent) comp;

                if (comp instanceof JLabel) {
                    JLabel label = (JLabel) comp;
                    if (label.getText().contains(name)) {
                        return label;
                    }
                } else if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    if (button.getText().contains(name)) {
                        return button;
                    }
                }
            }
        }
        return null;
    }
}
