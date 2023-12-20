package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.mainPage.EndingPage;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.view.mainPage.MainPageViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainPageViewerTest {
    @Test
    void mainPageViewerMenu() throws IOException, URISyntaxException, InterruptedException {
        MainPage mainPageMock = Mockito.mock(MainPage.class);
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.MENU);
        MenuViewer mainMenuViewerMock = Mockito.mock(MenuViewer.class);
        MainMenu mainMenuMock = Mockito.mock(MainMenu.class);
        Mockito.when(mainPageMock.getMainMenu()).thenReturn(mainMenuMock);
        GUI guiMock = Mockito.mock(GUI.class);
        MainPageViewer mainPageViewer = new MainPageViewer(mainPageMock);
        mainPageViewer.setMainMenuViewer(mainMenuViewerMock);
        mainPageViewer.drawScreen(guiMock);

        Mockito.verify(mainMenuViewerMock).draw(mainMenuMock, guiMock);
    }

    @Test
    void mainPageViewerEnding() throws IOException, URISyntaxException, InterruptedException {
        MainPage mainPageMock = Mockito.mock(MainPage.class);
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.ENDINGS);
        SceneViewer endingPageViewerMock = Mockito.mock(SceneViewer.class);
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        Mockito.when(mainPageMock.getEndingPage()).thenReturn(endingPageMock);
        GUI guiMock = Mockito.mock(GUI.class);
        MainPageViewer mainPageViewer = new MainPageViewer(mainPageMock);
        mainPageViewer.setEndingPageViewer(endingPageViewerMock);
        mainPageViewer.drawScreen(guiMock);

        Mockito.verify(endingPageViewerMock).draw(endingPageMock, guiMock);
    }
}
