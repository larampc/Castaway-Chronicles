package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.*;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneFactoryTest {
    private static List<Interactable> interactables;
    private static List<Interactable> visibleInteractables;
    private static Background background;
    private static MainChar mainChar;

    @BeforeEach
    public void init(){
        interactables = new ArrayList<>();
        visibleInteractables = new ArrayList<>();
        interactables.add(new Item(1,2,3,4,"new item"));
        interactables.add(new NPC(1,2,3,4,"new NPC"));
        background = new Background(2,3,4,5,"New background");
        mainChar = null;
        visibleInteractables.add(interactables.get(0));
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
