package castaway_chronicles.model;
import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.mainPage.MainMenu;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class MainMenuTest {
    MainMenu mainMenu;
    ResourceManager resourceManagerMock;

    @BeforeEach
    void init(){
        resourceManagerMock = Mockito.mock(ResourceManager.class);
        mainMenu = new MainMenu(){
            @Override
            public ResourceManager getResourceManager(){
                return resourceManagerMock;
            }
        };
    }

    @Test
    public void getEntry(){
        assertEquals("Continue", mainMenu.getSelectionPanel().getEntry(2));
        assertEquals("Endings", mainMenu.getSelectionPanel().getEntry(3));
        assertEquals("Start", mainMenu.getSelectionPanel().getEntry(0));
        assertEquals("Exit", mainMenu.getSelectionPanel().getEntry(1));
    }

    @Test
    void getCurrentEntry(){
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

    @Test
    void getBackground(){
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getName(), mainMenu.getBackground().getName());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getPosition(), mainMenu.getBackground().getPosition());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getHeight(), mainMenu.getBackground().getHeight());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getWidth(), mainMenu.getBackground().getWidth());
    }

    @Test
    void getEntries(){
        List<String> entries = Arrays.asList("Start", "Exit", "Continue", "Endings");
        assertEquals(entries, mainMenu.getSelectionPanel().getEntries());
    }

    @Test
    void isSelected(){
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
    }

    @Test
    void isSelectedExit(){
        assertFalse(mainMenu.isSelectedExit());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedExit());
    }

    @Test
    void isSelectedStart(){
        assertTrue(mainMenu.isSelectedStart());
        mainMenu.getSelectionPanel().nextEntry();
        assertFalse(mainMenu.isSelectedStart());
    }

    @Test
    void isSelectedContinue(){
        assertFalse(mainMenu.isSelectedContinue());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedContinue());
    }

    @Test
    void isSelectedEndings(){
        assertFalse(mainMenu.isSelectedEndings());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedEndings());
    }

    @Test
    void getNumberEntries(){
        assertEquals(4, mainMenu.getSelectionPanel().getNumberEntries());
    }
    @Test
    void nextEntry(){
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
    void previousEntry(){
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

    @Test
    void cantContinueNotExistsScenesSaved() {
        Mockito.when(resourceManagerMock.notExistsCurrentTimeResourceFile("Scenes_saved")).thenReturn(true);
        assertFalse(mainMenu.canContinue());
    }
    @Test
    void cantContinueButExistsScenesSaved(){
        Mockito.when(resourceManagerMock.notExistsCurrentTimeResourceFile("Scenes_saved")).thenReturn(false);
        Mockito.when(resourceManagerMock.countFiles("Scenes_saved")).thenReturn(0);
        assertFalse(mainMenu.canContinue());
        Mockito.when(resourceManagerMock.countFiles("Scenes_saved")).thenReturn(-1);
        assertFalse(mainMenu.canContinue());
    }
    @Test
    void canContinue(){
        Mockito.when(resourceManagerMock.notExistsCurrentTimeResourceFile("Scenes_saved")).thenReturn(false);
        Mockito.when(resourceManagerMock.countFiles("Scenes_saved")).thenReturn(1);
        assertTrue(mainMenu.canContinue());
    }
}
