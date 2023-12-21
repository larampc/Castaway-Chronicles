package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class SelectionPanelViewerTest {

    @Test
    void selectionPanelTest() throws IOException, URISyntaxException, InterruptedException {
        GUI guiMock = Mockito.mock(GUI.class);
        SelectionPanel selectionPanel = Mockito.mock(SelectionPanel.class);
        Mockito.when(selectionPanel.getNumberEntries()).thenReturn(3);
        Mockito.when(selectionPanel.getEntry(0)).thenReturn("Start");
        Mockito.when(selectionPanel.getEntry(1)).thenReturn("Exit");
        Mockito.when(selectionPanel.getEntry(2)).thenReturn("Continue");
        Mockito.when(selectionPanel.getEntry(3)).thenReturn("");
        Mockito.when(selectionPanel.isSelected(0)).thenReturn(true);
        Mockito.when(selectionPanel.isSelected(1)).thenReturn(false);
        Mockito.when(selectionPanel.isSelected(2)).thenReturn(false);
        Mockito.when(selectionPanel.isSelected(3)).thenReturn(false);
        SelectionPanelViewer selectionPanelViewer = new SelectionPanelViewer(new Position(98, 101), 42,20);

        selectionPanelViewer.draw(selectionPanel, guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(98, 101), 190, "Start", true);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(98, 121), 190, "Exit", false);
        Mockito.verify(guiMock, Mockito.times(1)).drawText(new Position(140, 101), 190, "Continue", false);
        Mockito.verify(guiMock, Mockito.times(3)).drawText(Mockito.any(Position.class), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean());
    }
}
