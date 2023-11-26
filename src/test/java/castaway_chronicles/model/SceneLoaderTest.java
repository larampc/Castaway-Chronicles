package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.SceneLoader;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Scene;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneLoaderTest {
    @Test
    public void getInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene","Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals(Arrays.asList(new NPC(1,2,3,4,"engineer"), new NPC(1,2,3,4,"witch")), scene.getInteractables());
    }
    @Test
    public void getVisibleInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene","Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals(List.of(new NPC(1, 2, 3, 4, "engineer")), scene.getVisibleInteractables());
    }
    @Test
    public void getBackground() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene2", "Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals("City", scene.getBackground().getName());
    }
    @Test
    public void hasMainChar() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene2", "Location");
        Scene scene = sceneBuilder.createScene();
        assertNotNull(scene);
        assertTrue(((Location) scene).hasMainChar());
    }
    @Test
    public void hasNotMainChar() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene", "Location");
        Scene scene = sceneBuilder.createScene();
        assertNotNull(scene);
        assertFalse(((Location) scene).hasMainChar());
    }


    @Test
    public void Location() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scene","Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals("Beach",scene.getBackground().getName());
    }
    @Test
    public void BackPack() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("BackPack","BackPack");
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("backpack", scene.getBackground().getName());
    }
    @Test
    public void Map() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Map","Map");
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("map", scene.getBackground().getName());
    }
}
