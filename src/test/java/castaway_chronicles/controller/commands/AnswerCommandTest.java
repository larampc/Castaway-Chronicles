package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.Commands.AnswerCommand;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.TextBox;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class AnswerCommandTest {
    @Test
    void AnswerCommand() throws IOException {
        Game gameMock = Mockito.mock(Game.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC NPCMock = Mockito.mock(NPC.class);

        Mockito.when(gameMock.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(NPCMock);

        new AnswerCommand(gameMock).execute();

        Mockito.verify(NPCMock).goToStateChoice();
        Mockito.verify(textBoxMock).setActiveChoice(false);
    }
}
