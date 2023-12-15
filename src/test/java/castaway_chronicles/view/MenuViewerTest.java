package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.game.PauseMenuViewer;
import castaway_chronicles.view.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewerTest {
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
    }

    @Test
    void mainMenuView() throws IOException, URISyntaxException, InterruptedException {
        MainMenu mainMenuMock = Mockito.mock(MainMenu.class);
        Mockito.when(mainMenuMock.getNumberEntries()).thenReturn(2);
        Mockito.when(mainMenuMock.getEntry(0)).thenReturn("Start");
        Mockito.when(mainMenuMock.getEntry(1)).thenReturn("Exit");
        MenuViewer menuViewer = new MenuViewer(mainMenuMock);
        menuViewer.drawElements(guiMock);
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "Menu");
        Mockito.verify(guiMock, Mockito.times(2)).drawText(Mockito.any(Position.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }

    @Test
    void pauseMenuView() throws IOException, URISyntaxException, InterruptedException {
        PauseMenu pauseMenuMock = Mockito.mock(PauseMenu.class);
        Mockito.when(pauseMenuMock.getNumberEntries()).thenReturn(2);
        Mockito.when(pauseMenuMock.getEntry(0)).thenReturn("Start");
        Mockito.when(pauseMenuMock.getEntry(1)).thenReturn("Exit");
        PauseMenuViewer pauseMenuViewer = new PauseMenuViewer(pauseMenuMock);
        pauseMenuViewer.draw(guiMock);
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "Menu");
        Mockito.verify(guiMock, Mockito.times(2)).drawText(Mockito.any(Position.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }
}
