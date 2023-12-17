package castaway_chronicles.model;

import castaway_chronicles.model.game.scene.PauseMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PauseMenuTest {
    private PauseMenu pauseMenu;
    @BeforeEach
    void setUp() {
        pauseMenu = new PauseMenu();
    }

    @Test
    public void PauseMenuContent(){
        assertEquals(List.of("Resume", "Save", "Menu", "Exit"), pauseMenu.getSelectionPanel().getEntries());
    }

    @Test
    public void PauseMenuSelection(){
        assertTrue(pauseMenu.isSelectedResume());
        assertFalse(pauseMenu.isSelectedSave());
        assertFalse(pauseMenu.isSelectedMenu());
        assertFalse(pauseMenu.isSelectedExit());
        pauseMenu.getSelectionPanel().nextEntry();
        assertFalse(pauseMenu.isSelectedResume());
        assertTrue(pauseMenu.isSelectedSave());
        assertFalse(pauseMenu.isSelectedMenu());
        assertFalse(pauseMenu.isSelectedExit());
        pauseMenu.getSelectionPanel().nextEntry();
        assertFalse(pauseMenu.isSelectedResume());
        assertFalse(pauseMenu.isSelectedSave());
        assertTrue(pauseMenu.isSelectedMenu());
        assertFalse(pauseMenu.isSelectedExit());
        pauseMenu.getSelectionPanel().nextEntry();
        assertFalse(pauseMenu.isSelectedResume());
        assertFalse(pauseMenu.isSelectedSave());
        assertFalse(pauseMenu.isSelectedMenu());
        assertTrue(pauseMenu.isSelectedExit());
        pauseMenu.getSelectionPanel().nextEntry();
        assertTrue(pauseMenu.isSelectedResume());
        assertFalse(pauseMenu.isSelectedSave());
        assertFalse(pauseMenu.isSelectedMenu());
        assertFalse(pauseMenu.isSelectedExit());
    }
}
