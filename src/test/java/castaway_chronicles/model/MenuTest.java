package castaway_chronicles.model;
import castaway_chronicles.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    Menu menu;
    @BeforeEach
    public void init(){
        menu = new Menu();
    }

    @Test
    public void getEntry(){
        assertEquals(menu.getEntry(0),"Start");
        assertEquals(menu.getEntry(1),"Exit");
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
        assertEquals(menu.getNumberEntries(), 2);
    }
    @Test
    public void nextEntry(){
        menu.nextEntry();
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(0));
        menu.nextEntry();
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
    }

    @Test
    public void previousEntry(){
        menu.previousEntry();
        assertTrue(menu.isSelected(1));
        assertFalse(menu.isSelected(0));
        menu.previousEntry();
        assertTrue(menu.isSelected(0));
        assertFalse(menu.isSelected(1));
    }
}
