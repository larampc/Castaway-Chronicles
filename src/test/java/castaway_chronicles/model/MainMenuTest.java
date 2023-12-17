package castaway_chronicles.model;
import castaway_chronicles.model.mainPage.MainMenu;
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
        assertEquals("Continue", mainMenu.getSelectionPanel().getEntry(2));
        assertEquals("Endings", mainMenu.getSelectionPanel().getEntry(3));
        assertEquals("Start", mainMenu.getSelectionPanel().getEntry(0));
        assertEquals("Exit", mainMenu.getSelectionPanel().getEntry(1));
    }

    @Test
    public void getCurrentEntry(){
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

    @Test
    public void getEntries(){
        List<String> entries = Arrays.asList("Start", "Exit", "Continue", "Endings");
        assertEquals(entries, mainMenu.getSelectionPanel().getEntries());
    }

    @Test
    public void isSelected(){
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
    }

    @Test
    public void isSelectedExit(){
        assertFalse(mainMenu.isSelectedExit());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedExit());
    }

    @Test
    public void isSelectedStart(){
        assertTrue(mainMenu.isSelectedStart());
        mainMenu.getSelectionPanel().nextEntry();
        assertFalse(mainMenu.isSelectedStart());
    }

    @Test
    public void isSelectedContinue(){
        assertFalse(mainMenu.isSelectedContinue());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedContinue());
    }

    @Test
    public void isSelectedEndings(){
        assertFalse(mainMenu.isSelectedEndings());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedEndings());
    }

    @Test
    public void getNumberEntries(){
        assertEquals(4, mainMenu.getSelectionPanel().getNumberEntries());
    }
    @Test
    public void nextEntry(){
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(1));
        assertFalse(mainMenu.getSelectionPanel().isSelected(0));
        assertEquals(1, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
        assertEquals(2, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(3));
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertEquals(3, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

    @Test
    public void previousEntry(){
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(3));
        assertFalse(mainMenu.getSelectionPanel().isSelected(0));
        assertEquals(3, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertEquals(2, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(1));
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertEquals(1, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }
}
