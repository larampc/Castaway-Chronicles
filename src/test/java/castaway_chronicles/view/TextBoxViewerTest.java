package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.view.game.TextBoxViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class TextBoxViewerTest {

    @Test
    void drawTextBox() throws IOException, URISyntaxException, InterruptedException {
        TextBoxViewer textBoxViewer = new TextBoxViewer();
        GUI guiMock = Mockito.mock(GUI.class);
        InteractableWithText interactableMock = Mockito.mock(InteractableWithText.class);
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Mockito.when(interactableMock.getText()).thenReturn("hello");

        textBoxViewer.drawTextBox(guiMock, interactableMock, false);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(guiMock).drawImage(new Position(2,151), "dialog");
        Mockito.verify(guiMock).drawText(new Position(6,155),190, "hello",false);
    }

    @Test
    void drawTextBoxChoice() throws IOException, URISyntaxException, InterruptedException {
        TextBoxViewer textBoxViewer = new TextBoxViewer();
        GUI guiMock = Mockito.mock(GUI.class);
        InteractableWithText interactableMock = Mockito.mock(InteractableWithText.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Mockito.when(interactableMock.getText()).thenReturn("hello");
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(interactableMock.getChoices()).thenReturn(selectionPanelMock);
        textBoxViewer.setSelectionPanelViewer(selectionPanelViewerMock);

        textBoxViewer.drawTextBox(guiMock, interactableMock, true);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(selectionPanelViewerMock).draw(selectionPanelMock, guiMock);
    }
}
