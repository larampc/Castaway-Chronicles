package castaway_chronicles.model;
import castaway_chronicles.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    Menu menu;
    @BeforeEach
    public void init(){
        menu = new Menu();
    }

    @Test
    public void getEntry(){
        assertEquals("Start", menu.getEntry(0));
        assertEquals("Exit", menu.getEntry(1));
    }

    @Test
    public void getCurrentEntry(){
        assertEquals(0, menu.getCurrentEntry());
    }

    @Test
    public void getEntries(){
        List<String> entries = Arrays.asList("Start", "Exit");
        assertEquals(entries, menu.getEntries());
    }

    @Test
    public void isSelected(){
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
    }

    @Test
    public void isSelectedExit(){
        assertFalse(menu.isSelectedExit());
        menu.nextEntry();
        assertTrue(menu.isSelectedExit());
    }

    @Test
    public void isSelectedStart(){
        assertTrue(menu.isSelectedStart());
        menu.nextEntry();
        assertFalse(menu.isSelectedStart());
    }

    @Test
    public void getNumberEntries(){
        assertEquals(2, menu.getNumberEntries());
    }
    @Test
    public void nextEntry(){
        menu.nextEntry();
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(0));
        assertEquals(1, menu.getCurrentEntry());
        menu.nextEntry();
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
        assertEquals(0, menu.getCurrentEntry());
    }

    @Test
    public void previousEntry(){
        menu.previousEntry();
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(0));
        assertEquals(1, menu.getCurrentEntry());
        menu.previousEntry();
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
        assertEquals(0, menu.getCurrentEntry());
    }
}
