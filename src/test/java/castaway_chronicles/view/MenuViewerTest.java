package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.game.PauseMenuViewer;
import castaway_chronicles.view.menu.MainMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewerTest {
    private GUI guiMock;

    @BeforeEach
    void setUP() {
        guiMock = Mockito.mock(GUI.class);
    }

    @Test
    void mainMenuTest() throws IOException, URISyntaxException, InterruptedException {
        MainMenu mainMenuMock = Mockito.mock(MainMenu.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        MainMenuViewer mainMenuViewer = new MainMenuViewer();
        mainMenuViewer.setSelectionPanelViewer(selectionPanelViewerMock);
        Background backgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);

        Mockito.when(mainMenuMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("Menu");

        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        mainMenuViewer.draw(mainMenuMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "Menu");
        Mockito.verify(selectionPanelViewerMock).draw(mainMenuMock.getSelectionPanel(), guiMock);
    }

    @Test
    void pauseMenuTest() throws IOException, URISyntaxException, InterruptedException {
        PauseMenu pauseMenuMock = Mockito.mock(PauseMenu.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);

        Mockito.when(pauseMenuMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("Menu");

        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);

        PauseMenuViewer pauseMenuViewer = new PauseMenuViewer();
        pauseMenuViewer.setSelectionPanelViewer(selectionPanelViewerMock);
        pauseMenuViewer.draw(pauseMenuMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "Menu");
        Mockito.verify(selectionPanelViewerMock).draw(pauseMenuMock.getSelectionPanel(), guiMock);
    }
}
