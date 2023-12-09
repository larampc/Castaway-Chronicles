package castaway_chronicles.controller;

import castaway_chronicles.controller.game.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.game.Commands.ChangeSceneCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    private Game game;
    GameController gameController;


    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void changeLocation() {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        Location newLocation = Mockito.mock(Location.class);
        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        locations.put("City",newLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");
        gameController = new GameController(game);

        assertEquals(startLocation, game.getCurrentLocation());

        ChangeLocationCommand changeLocationCommand = new ChangeLocationCommand(game,"City");

        changeLocationCommand.execute();

        assertEquals(newLocation, game.getCurrentLocation());
    }

    @Test
    void changeScene() {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");
        Backpack backpack = Mockito.mock(Backpack.class);
        game.setBackpack(backpack);
        gameController = new GameController(game);

        assertEquals(Game.SCENE.LOCATION, game.getScene());

        ChangeSceneCommand changeSceneCommand = new ChangeSceneCommand(game,"BACKPACK");
        changeSceneCommand.execute();

        assertEquals(Game.SCENE.BACKPACK, game.getScene());
    }



}
