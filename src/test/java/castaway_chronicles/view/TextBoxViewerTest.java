package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.TextDisplay;
import castaway_chronicles.view.game.TextBoxViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class TextBoxViewerTest {
    private GUI guiMock;

    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
    }

    @Test
    void drawTextBoxNPCLocation() throws IOException, URISyntaxException, InterruptedException {
        Location locationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC elementMock = Mockito.mock(NPC.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(elementMock);
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Mockito.when(elementMock.getCurrentLine()).thenReturn("hi");

        TextBoxViewer textBoxViewer = new TextBoxViewer();
        textBoxViewer.drawTextBox(guiMock, locationMock);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(guiMock).drawImage(new Position(2,151), "dialog");
        Mockito.verify(guiMock).drawText(new Position(6,155),190, "hi", false);
    }

    @Test
    void drawTextBoxItemLocation() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(guiMock.isBigger()).thenReturn(true);

        Location locationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        ItemBackpack elementMock = Mockito.mock(ItemBackpack.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(elementMock);
        Mockito.when(elementMock.getAnswer()).thenReturn("hi");

        TextBoxViewer textBoxViewer = new TextBoxViewer();
        textBoxViewer.drawTextBox(guiMock, locationMock);

        Mockito.verify(guiMock, Mockito.never()).resizeTerminal();
        Mockito.verify(guiMock).drawImage(new Position(2,151), "dialog");
        Mockito.verify(guiMock).drawText(new Position(6,155),190, "hi", false);
    }

    @Test
    void drawTextBoxBackpack() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(guiMock.isBigger()).thenReturn(true);
        Backpack backpackMock = Mockito.mock(Backpack.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        ItemBackpack elementMock = Mockito.mock(ItemBackpack.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(elementMock);
        Mockito.when(elementMock.getDescription()).thenReturn("hi");

        TextBoxViewer textBoxViewer = new TextBoxViewer();
        textBoxViewer.drawTextBox(guiMock, backpackMock);

        Mockito.verify(guiMock, Mockito.never()).resizeTerminal();
        Mockito.verify(guiMock).drawImage(new Position(2,151), "dialog");
        Mockito.verify(guiMock).drawText(new Position(6,155),190, "hi", false);
    }

    @Test
    void drawChoicesBackpack() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Backpack backpackMock = Mockito.mock(Backpack.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        ItemBackpack elementMock = Mockito.mock(ItemBackpack.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(backpackMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(elementMock);
        Mockito.when(elementMock.getItemOptions()).thenReturn(selectionPanelMock);

        TextBoxViewer textBoxViewer = new TextBoxViewer();
        textBoxViewer.drawChoices(guiMock, backpackMock, selectionPanelViewerMock);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(selectionPanelViewerMock).draw(selectionPanelMock, guiMock);
    }

    @Test
    void drawChoicesLocation() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Location locationMock = Mockito.mock(Location.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC elementMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(elementMock);
        Mockito.when(elementMock.getChoices()).thenReturn(selectionPanelMock);

        TextBoxViewer textBoxViewer = new TextBoxViewer();
        textBoxViewer.drawChoices(guiMock, locationMock, selectionPanelViewerMock);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(selectionPanelViewerMock).draw(selectionPanelMock, guiMock);
    }
}
