package castaway_chronicles.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionPanelTest {
    SelectionPanel panel;
    @BeforeEach
    void setUp() {
        panel = new SelectionPanel(List.of("0", "1", "2", "3"));
    }

    @Test
    void nextEntry() {
        assertTrue(panel.isSelected(0));
        panel.nextEntry();
        assertTrue(panel.isSelected(1));
        assertFalse(panel.isSelected(0));
        panel.nextEntry();
        assertTrue(panel.isSelected(2));
        assertFalse(panel.isSelected(1));
        panel.nextEntry();
        assertTrue(panel.isSelected(3));
        assertFalse(panel.isSelected(2));
        panel.nextEntry();
        assertTrue(panel.isSelected(0));
        assertFalse(panel.isSelected(3));
    }

    @Test
    void previousEntry() {
        assertTrue(panel.isSelected(0));
        panel.previousEntry();
        assertTrue(panel.isSelected(3));
        assertFalse(panel.isSelected(0));
        panel.previousEntry();
        assertTrue(panel.isSelected(2));
        assertFalse(panel.isSelected(3));
        panel.previousEntry();
        assertTrue(panel.isSelected(1));
        assertFalse(panel.isSelected(2));
        panel.previousEntry();
        assertTrue(panel.isSelected(0));
        assertFalse(panel.isSelected(1));
    }

    @Test
    void getEntry() {
        assertEquals("0", panel.getEntry(0));
        assertEquals("1", panel.getEntry(1));
        assertEquals("2", panel.getEntry(2));
        assertEquals("3", panel.getEntry(3));
        assertEquals("", panel.getEntry(4));
    }

    @Test
    void getters() {
        assertEquals(4, panel.getNumberEntries());
        assertEquals(List.of("0", "1", "2", "3"), panel.getEntries());
        assertEquals(0, panel.getCurrentEntry());
    }
}
