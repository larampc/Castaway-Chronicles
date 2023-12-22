package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.Commands.GetSideOptionCommand;
import castaway_chronicles.model.SelectionPanel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;


public class GetSideOptionCommandTest {
    @Test
    void GetSideOptionCommand() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        Mockito.when(selectionPanelMock.getCurrentEntry()).thenReturn(0);
        Mockito.when(selectionPanelMock.getEntry(2)).thenReturn("");
        Mockito.when(selectionPanelMock.getEntry(-2)).thenReturn("");

        new GetSideOptionCommand(selectionPanelMock).execute();

        Mockito.verify(selectionPanelMock, Mockito.never()).nextEntry();
        Mockito.verify(selectionPanelMock, Mockito.never()).previousEntry();

        Mockito.when(selectionPanelMock.getEntry(-2)).thenReturn("test");

        new GetSideOptionCommand(selectionPanelMock).execute();

        Mockito.verify(selectionPanelMock, Mockito.never()).nextEntry();
        Mockito.verify(selectionPanelMock, Mockito.times(2)).previousEntry();

        Mockito.when(selectionPanelMock.getEntry(2)).thenReturn("test");

        new GetSideOptionCommand(selectionPanelMock).execute();

        Mockito.verify(selectionPanelMock, Mockito.times(2)).nextEntry();
        Mockito.verify(selectionPanelMock, Mockito.times(2)).previousEntry();
    }
}
