package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.menu.EndingItem;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.view.menu.EndingPageViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class EndingPageViewerTest {

    @Test
    void endingPageView() throws IOException, URISyntaxException, InterruptedException {
        GUI guiMock = Mockito.mock(GUI.class);
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        EndingItem itemMock1 = Mockito.mock(EndingItem.class);
        EndingItem itemMock2 = Mockito.mock(EndingItem.class);
        Position positionMock = Mockito.mock(Position.class);
        Background backgroundMock = Mockito.mock(Background.class);

        Mockito.when(endingPageMock.getVisibleEndings()).thenReturn(List.of(itemMock1, itemMock2));
        Mockito.when(endingPageMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("EndingsMenu");
        Mockito.when(itemMock1.getName()).thenReturn("flower");
        Mockito.when(itemMock2.getName()).thenReturn("drink");
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(itemMock1.getPosition()).thenReturn(positionMock);
        Mockito.when(itemMock2.getPosition()).thenReturn(positionMock);
        EndingPageViewer endingPageViewer = new EndingPageViewer();

        endingPageViewer.draw(endingPageMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "EndingsMenu");
        Mockito.verify(guiMock).drawImage(positionMock, "flower");
        Mockito.verify(guiMock).drawImage(positionMock, "drink");
    }
}
