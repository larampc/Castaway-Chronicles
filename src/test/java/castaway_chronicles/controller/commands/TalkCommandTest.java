package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.TextBox;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TalkCommandTest {
    @Test
    void TalkCommand() {
        Game gameMock = Mockito.mock(Game.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC NPCMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        Mockito.when(gameMock.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(NPCMock);
        Mockito.when(NPCMock.getChoices()).thenReturn(selectionPanelMock);
        Mockito.when(NPCMock.dialogEnded()).thenReturn(false);

        Mockito.when(selectionPanelMock.getNumberEntries()).thenReturn(1);
        new TalkCommand(gameMock).execute();

        Mockito.verify(NPCMock).nextLine();
        Mockito.verify(textBoxMock, Mockito.never()).closeTextBox();
        Mockito.verify(textBoxMock, Mockito.never()).setActiveChoice(true);

        Mockito.when(NPCMock.dialogEnded()).thenReturn(true);
        Mockito.when(selectionPanelMock.getNumberEntries()).thenReturn(1);
        new TalkCommand(gameMock).execute();

        Mockito.verify(NPCMock).nextLine();
        Mockito.verify(textBoxMock, Mockito.never()).closeTextBox();
        Mockito.verify(textBoxMock).setActiveChoice(true);

        Mockito.when(NPCMock.dialogEnded()).thenReturn(true);
        Mockito.when(selectionPanelMock.getNumberEntries()).thenReturn(0);
        new TalkCommand(gameMock).execute();

        Mockito.verify(NPCMock).nextLine();
        Mockito.verify(textBoxMock).closeTextBox();
        Mockito.verify(textBoxMock).setActiveChoice(true);
    }
}
