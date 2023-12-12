package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.EndingItem;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.view.menu.EndingPageViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class EndingPageViewerTest {
    private GUI guiMock;
    private EndingPageViewer endingPageViewer;

    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        Mockito.when(endingPageMock.getVisibleEndings()).thenReturn(List.of(new EndingItem(1,1,1,1, "flower"), new EndingItem(1,1,1,1,"drink")));
        endingPageViewer = new EndingPageViewer(endingPageMock);
    }

    @Test
    void endingPageView() {
        endingPageViewer.drawElements(guiMock);
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "EndingsMenu");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(1, 1), "flower");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(1, 1), "drink");

    }
}
