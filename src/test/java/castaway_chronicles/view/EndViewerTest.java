package castaway_chronicles.view;

import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class EndViewerTest {

    @Test
    void endViewer() throws IOException, URISyntaxException {
        LanternaGUI guiMock = Mockito.mock(LanternaGUI.class);
        Ending endingMock = Mockito.mock(Ending.class);
        Mockito.when(endingMock.getCurrentFrame()).thenReturn("first");
        Mockito.when(endingMock.getName()).thenReturn("first");
        Mockito.when(guiMock.imageIsLoaded("first")).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(true);

        EndViewer endViewer = new EndViewer(endingMock);
        endViewer.drawScreen(guiMock);

        Mockito.verify(guiMock).loadEnding("first");
        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(guiMock).drawImage(new Position(0,0), "first");
    }
}
