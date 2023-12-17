package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
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

        MainMenuViewer mainMenuViewer = new MainMenuViewer(mainMenuMock);
        mainMenuViewer.setSelectionPanelViewer(selectionPanelViewerMock);
        mainMenuViewer.drawScreen(guiMock);

        Mockito.verify(guiMock).drawImage(new Position(0,0), "Menu");
        Mockito.verify(selectionPanelViewerMock).draw(mainMenuMock, guiMock);
    }

    @Test
    void pauseMenuTest() throws IOException, URISyntaxException, InterruptedException {
        PauseMenu pauseMenuMock = Mockito.mock(PauseMenu.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);

        PauseMenuViewer pauseMenuViewer = new PauseMenuViewer();
        pauseMenuViewer.setSelectionPanelViewer(selectionPanelViewerMock);
        pauseMenuViewer.draw(pauseMenuMock, guiMock);

        Mockito.verify(guiMock).drawImage(new Position(0,0), "Menu");
        Mockito.verify(selectionPanelViewerMock).draw(pauseMenuMock, guiMock);
    }
}
