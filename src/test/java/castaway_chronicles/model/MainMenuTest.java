package castaway_chronicles.model;
import castaway_chronicles.model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuTest {
    MainMenu mainMenu;
    @BeforeEach
    public void init(){
        mainMenu = new MainMenu();
    }

    @Test
    public void getEntry(){
        assertEquals("Start", mainMenu.getEntry(0));
        assertEquals("Exit", mainMenu.getEntry(1));
    }

    @Test
    public void getCurrentEntry(){
        assertEquals(0, mainMenu.getCurrentEntry());
    }

    @Test
    public void getEntries(){
        List<String> entries = Arrays.asList("Start", "Exit", "Continue", "Endings");
        assertEquals(entries, mainMenu.getEntries());
    }

    @Test
    public void isSelected(){
        assertTrue(mainMenu.isSelected(0));
        assertFalse(mainMenu.isSelected(1));
    }

    @Test
    public void isSelectedExit(){
        assertFalse(mainMenu.isSelectedExit());
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelectedExit());
    }

    @Test
    public void isSelectedStart(){
        assertTrue(mainMenu.isSelectedStart());
        mainMenu.nextEntry();
        assertFalse(mainMenu.isSelectedStart());
    }

    @Test
    public void getNumberEntries(){
        assertEquals(4, mainMenu.getNumberEntries());
    }
    @Test
    public void nextEntry(){
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(1));
        assertFalse(mainMenu.isSelected(0));
        assertEquals(1, mainMenu.getCurrentEntry());
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(2));
        assertFalse(mainMenu.isSelected(1));
        assertEquals(2, mainMenu.getCurrentEntry());
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(3));
        assertFalse(mainMenu.isSelected(2));
        assertEquals(3, mainMenu.getCurrentEntry());
        mainMenu.nextEntry();
        assertTrue(mainMenu.isSelected(0));
        assertFalse(mainMenu.isSelected(3));
        assertEquals(0, mainMenu.getCurrentEntry());
    }

    @Test
    public void previousEntry(){
        mainMenu.previousEntry();
        assertTrue(mainMenu.isSelected(3));
        assertFalse(mainMenu.isSelected(0));
        assertEquals(3, mainMenu.getCurrentEntry());
        mainMenu.previousEntry();
        assertTrue(mainMenu.isSelected(2));
        assertFalse(mainMenu.isSelected(3));
        assertEquals(2, mainMenu.getCurrentEntry());
        mainMenu.previousEntry();
        assertTrue(mainMenu.isSelected(1));
        assertFalse(mainMenu.isSelected(2));
        assertEquals(1, mainMenu.getCurrentEntry());
        mainMenu.previousEntry();
        assertTrue(mainMenu.isSelected(0));
        assertFalse(mainMenu.isSelected(1));
        assertEquals(0, mainMenu.getCurrentEntry());
    }
}
