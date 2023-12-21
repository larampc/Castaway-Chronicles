package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.ChangeLocationCommand;
import castaway_chronicles.model.game.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangeLocationCommandTest {
    @Test
    void changeLocation() {
        Game gameMock = Mockito.mock(Game.class);
        ChangeLocationCommand changeLocationCommand = new ChangeLocationCommand(gameMock,"City");
        changeLocationCommand.execute();
        Mockito.verify(gameMock).setCurrentLocation("City");
    }
}
