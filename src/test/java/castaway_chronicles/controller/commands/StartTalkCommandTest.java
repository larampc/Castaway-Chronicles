package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.StartTalkCommand;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StartTalkCommandTest {
    @Test
    void StartTalkCommand() {
        Game gameMock = Mockito.mock(Game.class);
        Location locationMock = Mockito.mock(Location.class);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(locationMock.getInteractable("Test")).thenReturn(npcMock);

        new StartTalkCommand(gameMock,"Test").execute();

        Mockito.verify(gameMock).setTextBox(npcMock);
    }
}
