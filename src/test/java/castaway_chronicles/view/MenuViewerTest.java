package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Menu;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewerTest {
    private GUI guiMock;
    private MenuViewer menuViewer;
    private SelectionPanelViewer selectionPanelViewerMock;

    @BeforeEach
    void setUP() {
        guiMock = Mockito.mock(GUI.class);
        selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        menuViewer = new MenuViewer() {
            @Override
            public SelectionPanelViewer getSelectionPanelViewer() {
                return selectionPanelViewerMock;
            }
        };
    }

    @Test
    void mainMenuTest() throws IOException, URISyntaxException, InterruptedException {
        Menu menuMock = Mockito.mock(Menu.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);

        Mockito.when(menuMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("Menu");

        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        menuViewer.draw(menuMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "Menu");
        Mockito.verify(selectionPanelViewerMock).draw(menuMock.getSelectionPanel(), guiMock);
    }
}
