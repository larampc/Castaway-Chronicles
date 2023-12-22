package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.SceneLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneLoaderTest {
    @Test
    public void getInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        List<Interactable> interactables = List.of(new NPC(1,2,3,4,"engineer",0),
                new NPC(1,2,3,4,"witch",0),
                new Icon(2,2,23,17,"MAP_icon"),
                new Item(1, 2, 3, 4, "rope"),
                new BackpackItem(1, 2, 3, 4, "rope_backpack"));
        assertTrue(interactables.size() == scene.getInteractables().size() && interactables.containsAll(scene.getInteractables()));
    }
    @Test
    public void getVisibleInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        List<Interactable> interactables = List.of(new NPC(1,2,3,4,"engineer",0),
                new Icon(2,2,23,17,"MAP_icon"));
        assertTrue(interactables.size() == scene.getVisibleInteractables().size() && interactables.containsAll(scene.getVisibleInteractables()));
    }
    @Test
    public void getBackground() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        assertEquals("Beach", scene.getBackground().getName());
        assertFalse(scene.getBackground().isLoopable());

        sceneBuilder = new SceneLoader("Scenes", "TestScene2", Game.SCENE.LOCATION);
        scene = sceneBuilder.createScene();
        assertNull(scene.getBackground());

        sceneBuilder = new SceneLoader("Scenes","TestScene3", Game.SCENE.LOCATION);
        scene = sceneBuilder.createScene();
        assertNull(scene.getBackground());

        sceneBuilder = new SceneLoader("Scenes","TestScene4", Game.SCENE.LOCATION);
        scene = sceneBuilder.createScene();
        assertEquals("Beach", scene.getBackground().getName());
        assertTrue(scene.getBackground().isLoopable());
    }

    @Test
    public void hasMainChar() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        assertNotNull(((Location) scene).getMainChar());
    }
    @Test
    public void hasNotMainChar() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene3", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        assertNotNull(scene);
        assertNull(((Location) scene).getMainChar());
    }


    @Test
    public void Location() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        assertEquals("Beach",scene.getBackground().getName());
    }
    @Test
    public void BackPack() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestBackpack", Game.SCENE.BACKPACK);
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("backpack", scene.getBackground().getName());
    }
    @Test
    public void Map() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestMap", Game.SCENE.MAP);
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("map", scene.getBackground().getName());
    }
}
