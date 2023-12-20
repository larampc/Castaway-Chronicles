package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.locationControllers.NarratorController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.TextBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

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
    public void select() throws IOException, URISyntaxException, InterruptedException {
        Location currentLocation = Mockito.mock(Location.class);
        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocation);
        TextBox backpackAnswer = Mockito.mock(TextBox.class);
        Mockito.when(game.getTextDisplay()).thenReturn(backpackAnswer);

        narratorController.key(KeyEvent.VK_ENTER,Mockito.mock(Application.class));
        Mockito.verify(backpackAnswer,Mockito.times(1)).closeTextBox();
        assertEquals(gameController.getCurrent(),gameController.getLocationController());
    }
}
