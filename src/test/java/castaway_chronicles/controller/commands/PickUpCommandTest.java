package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.PickUpCommand;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PickUpCommandTest {
    @Test
    void pickUpCommand() {
        Game gameMock = Mockito.mock(Game.class);
        Location currentLocationMock = Mockito.mock(Location.class);
        Backpack backpackMock = Mockito.mock(Backpack.class);

        Mockito.when(gameMock.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);

        PickUpCommand pickUpCommand = new PickUpCommand(gameMock,"Test");
        pickUpCommand.execute();

        Mockito.verify(currentLocationMock).setInvisible("Test");
        Mockito.verify(backpackMock).setVisible("Test_backpack");
    }
}
