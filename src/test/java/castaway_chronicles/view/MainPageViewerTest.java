package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.EndingPage;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.view.mainPage.MainPageViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageViewerTest {
    private MainPage mainPageMock;
    private MainPageViewer mainPageViewer;
    private GUI guiMock;
    private MenuViewer menuViewerMock;
    private SceneViewer sceneViewerMock;
    private SelectionPanelViewer selectionPanelViewerMock;

    @BeforeEach
    void setUp() {
        mainPageMock = Mockito.mock(MainPage.class);
        menuViewerMock = Mockito.mock(MenuViewer.class);
        sceneViewerMock = Mockito.mock(SceneViewer.class);
        guiMock = Mockito.mock(GUI.class);
        selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        Mockito.when(menuViewerMock.getSelectionPanelViewer()).thenReturn(selectionPanelViewerMock);
        mainPageViewer = new MainPageViewer(mainPageMock) {
            @Override
            public MenuViewer getMainMenuViewer() {
                return menuViewerMock;
            }
            @Override
            public SceneViewer getEndingPageViewer() {
                return sceneViewerMock;
            }
        };
    }
    @Test
    void mainPageViewerMenu() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.MENU);
        MainMenu mainMenuMock = Mockito.mock(MainMenu.class);
        Mockito.when(mainPageMock.getMainMenu()).thenReturn(mainMenuMock);

        mainPageViewer.drawScreen(guiMock);

        Mockito.verify(menuViewerMock).draw(mainMenuMock, guiMock);
    }

    @Test
    void mainPageViewerEnding() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.ENDINGS);
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        Mockito.when(mainPageMock.getEndingPage()).thenReturn(endingPageMock);

        mainPageViewer.drawScreen(guiMock);

        Mockito.verify(sceneViewerMock).draw(endingPageMock, guiMock);
    }

    @Test
    void getters() {
        mainPageViewer = new MainPageViewer(mainPageMock);
        assertEquals(MenuViewer.class, mainPageViewer.getMainMenuViewer().getClass());
        assertEquals(SceneViewer.class, mainPageViewer.getEndingPageViewer().getClass());
    }
    @Test
    void constructor() {
        Mockito.verify(selectionPanelViewerMock).setDefinitions(42,20, new Position(98,101));
    }
}
