package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.ControllerStates.NarratorController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.BackpackAnswer;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class NarratorControllerTest {
    private Game game;
    private GameController gameController;
    private NarratorController narratorController;


    @BeforeEach
    void init() {
        game = Mockito.mock(Game.class);
        gameController = new GameController(game);
        narratorController = (NarratorController) gameController.getNarratorController();
        gameController.setControllerState(narratorController);
    }
    @Test
    public void select() throws IOException, InterruptedException {
        Location currentLocation = Mockito.mock(Location.class);
        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocation);
        BackpackAnswer backpackAnswer = Mockito.mock(BackpackAnswer.class);
        Mockito.when(currentLocation.getBackpackAnswer()).thenReturn(backpackAnswer);

        narratorController.select(Mockito.mock(Application.class));
        Mockito.verify(backpackAnswer,Mockito.times(1)).deactivate();
        assertEquals(gameController.getCurrent(),gameController.getLocationController());
    }
}
