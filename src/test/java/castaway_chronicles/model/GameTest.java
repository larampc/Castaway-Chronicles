package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game;
    private Map mapMock;
    private Backpack backpackMock;
    private HashMap<String, Location> locations;
    private Location locationMock1;
    private Location locationMock2;
    private PauseMenu pauseMenuMock;

    @BeforeEach
    public void init() {
        mapMock = Mockito.mock(Map.class);
        backpackMock = Mockito.mock(Backpack.class);
        pauseMenuMock = Mockito.mock(PauseMenu.class);
        locationMock1 = Mockito.mock(Location.class);
        locationMock2 = Mockito.mock(Location.class);
        locations = new HashMap<>();
        locations.put("test", locationMock1);
        locations.put("test2", locationMock2);
        game = new Game(mapMock, backpackMock, locations, pauseMenuMock, "test");
    }

    @Test
    public void GameContent() {
        assertEquals(backpackMock, game.getBackpack());
        assertEquals(locations, game.getLocations());
        assertEquals(locationMock1, game.getLocation("test"));
        assertEquals(locationMock1, game.getCurrentLocation());
        assertEquals(mapMock, game.getMap());
        assertEquals(pauseMenuMock, game.getPauseMenu());
        assertEquals(TextBox.class, game.getTextBox().getClass());
    }

    @Test
    public void Scene() {
        game.setCurrentScene(Game.SCENE.BACKPACK);
        assertEquals("BACKPACK", game.getScene().name());
        game.setCurrentScene(Game.SCENE.MAP);
        assertEquals("MAP", game.getScene().name());
        game.setCurrentScene(Game.SCENE.LOCATION);
        assertEquals("LOCATION", game.getScene().name());
        game.setCurrentScene(Game.SCENE.PAUSE);
        assertEquals("PAUSE", game.getScene().name());
    }

    @Test
    public void Location() {
        assertEquals(locationMock1, game.getCurrentLocation());
        game.setCurrentLocation("test");
        assertEquals(locationMock1, game.getCurrentLocation());
        game.setCurrentLocation("test2");
        assertEquals(locationMock2, game.getCurrentLocation());
        game.setCurrentLocation("test2");
        assertEquals(locationMock2, game.getCurrentLocation());
        game.setCurrentLocation("test");
        assertEquals(locationMock1, game.getCurrentLocation());
    }
    @Test
    public void TextBox() {
        InteractableWithText mockInteractable = Mockito.mock(InteractableWithText.class);
        game.setTextBox(mockInteractable);
        assertEquals(mockInteractable, game.getTextBox().getInteractable());
    }
}
