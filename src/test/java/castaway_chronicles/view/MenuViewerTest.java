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
        Mockito.when(mainMenuMock.getNumberEntries()).thenReturn(3);
        Mockito.when(mainMenuMock.getEntry(0)).thenReturn("Start");
        Mockito.when(mainMenuMock.getEntry(1)).thenReturn("Exit");
        Mockito.when(mainMenuMock.getEntry(2)).thenReturn("Continue");
        Mockito.when(mainMenuMock.getEntry(3)).thenReturn("");
        Mockito.when(mainMenuMock.isSelected(0)).thenReturn(true);
        Mockito.when(mainMenuMock.isSelected(1)).thenReturn(false);
        Mockito.when(mainMenuMock.isSelected(2)).thenReturn(false);
        Mockito.when(mainMenuMock.isSelected(3)).thenReturn(false);
        MenuViewer menuViewer = new MenuViewer(mainMenuMock);
        menuViewer.drawElements(guiMock);
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "Menu");
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(98, 101), 160, "Start", true);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(98, 121), 160, "Exit", false);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(140, 101), 160, "Continue", false);
        Mockito.verify(guiMock, Mockito.times(3)).drawText(Mockito.any(Position.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }

    @Test
    void pauseMenuView() throws IOException, URISyntaxException, InterruptedException {
        PauseMenu pauseMenuMock = Mockito.mock(PauseMenu.class);
        Mockito.when(pauseMenuMock.getNumberEntries()).thenReturn(3);
        Mockito.when(pauseMenuMock.getEntry(0)).thenReturn("Start");
        Mockito.when(pauseMenuMock.getEntry(1)).thenReturn("Exit");
        Mockito.when(pauseMenuMock.getEntry(2)).thenReturn("Continue");
        Mockito.when(pauseMenuMock.getEntry(3)).thenReturn("");
        Mockito.when(pauseMenuMock.isSelected(0)).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelected(1)).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelected(2)).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelected(3)).thenReturn(false);

        PauseMenuViewer pauseMenuViewer = new PauseMenuViewer(pauseMenuMock);
        pauseMenuViewer.draw(guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "Menu");
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(97, 101), 160, "Start", true);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(97, 121), 160, "Exit", false);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(145, 101), 160, "Continue", false);
        Mockito.verify(guiMock, Mockito.times(3)).drawText(Mockito.any(Position.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }
}
