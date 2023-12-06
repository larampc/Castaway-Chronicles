package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.*;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SceneFactoryTest {
    private static HashMap<String, Interactable> interactables;
    private static HashMap<String, Interactable> visibleInteractables;
    private static Background background;
    private static MainChar mainChar;

    @BeforeEach
    public void init() throws IOException {
        interactables = new HashMap<>();
        visibleInteractables = new HashMap<>();
        interactables.put("new Item", new Item(1,2,3,4,"new item"));
        interactables.put("witch", new NPC(1,2,3,4,"witch", 0));
        background = new Background(2,3,4,5,"New background");
        mainChar = null;
        visibleInteractables.put("new Item", new Item(1,2,3,4,"new item"));
    }
    @Test
    public void Location(){
        mainChar = new MainChar(10, 10, 10, 10, "standing_left");
        Scene scene = SceneFactory.getScene("Location",background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Location.class);
    }
    @Test
    public void Backpack(){
        Scene scene = SceneFactory.getScene("Backpack",background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Backpack.class);
    }
    @Test
    public void Map(){
        Scene scene = SceneFactory.getScene("Map",background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Map.class);
    }
    @Test
    public void noScene(){
        Scene scene = SceneFactory.getScene("Invalid",background,interactables,visibleInteractables, mainChar);
        assertNull(scene);
    }
}
