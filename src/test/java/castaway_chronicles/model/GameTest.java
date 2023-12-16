package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.PauseMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    private Game game;
    @BeforeEach
    public void init() {
        game = new Game();
    }

    @Test
    public void Backpack() {
        Backpack backpack = Mockito.mock(Backpack.class);
        game.setBackpack(backpack);
        assertEquals(backpack, game.getBackpack());
    }

    @Test
    public void Locations() {
        Location location = Mockito.mock(Location.class);
        HashMap<String, Location> locationHashMap = new HashMap<>();
        locationHashMap.put("test", location);
        game.setLocations(locationHashMap);
        assertEquals(locationHashMap, game.getLocations());
        assertEquals(location, game.getLocation("test"));
        game.setCurrentLocation("test");
        assertEquals(location, game.getCurrentLocation());
    }

    @Test
    public void Map() {
        Map map = Mockito.mock(Map.class);
        game.setMap(map);
        assertEquals(map, game.getMap());
    }

    @Test
    public void PauseMenu() {
        PauseMenu pauseMenu = Mockito.mock(PauseMenu.class);
        game.setPauseMenu(pauseMenu);
        assertEquals(pauseMenu, game.getPauseMenu());
    }

    @Test
    public void Scene() {
        game.setCurrentScene("BACKPACK");
        assertEquals("BACKPACK", game.getScene().name());
        game.setCurrentScene("MAP");
        assertEquals("MAP", game.getScene().name());
        game.setCurrentScene("LOCATION");
        assertEquals("LOCATION", game.getScene().name());
        game.setCurrentScene("PAUSE");
        assertEquals("PAUSE", game.getScene().name());
        assertThrows(Throwable.class, () -> game.setCurrentScene("TEST"));
    }
}
