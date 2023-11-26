package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.LoaderSceneBuilder;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Scene;
import castaway_chronicles.model.game.scene.SceneBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneBuilderTest {
    @Test
    public void getInteractables() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene","Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertEquals(Arrays.asList(new NPC(1,2,3,4,"engineer"), new NPC(1,2,3,4,"witch")), scene.getInteractables());
    }
    @Test
    public void getVisibleInteractables() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene","Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertEquals(List.of(new NPC(1, 2, 3, 4, "engineer")), scene.getVisibleInteractables());
    }
    @Test
    public void getBackground() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene2", "Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertEquals("City", scene.getBackground().getName());
    }
    @Test
    public void hasMainChar() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene2", "Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertNotNull(scene);
        assertTrue(((Location) scene).hasMainChar());
    }
    @Test
    public void hasNotMainChar() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene", "Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertNotNull(scene);
        assertFalse(((Location) scene).hasMainChar());
    }


    @Test
    public void Location() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Scene","Location");
        Scene scene = sceneBuilder.createScene("Location");
        assertEquals("Beach",scene.getBackground().getName());
    }
    @Test
    public void BackPack() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("BackPack","BackPack");
        Scene scene = sceneBuilder.createScene("BackPack");
        assertEquals(2,scene.getInteractables().size());
        assertEquals("backpack", scene.getBackground().getName());
    }
    @Test
    public void Map() throws IOException {
        SceneBuilder sceneBuilder = new LoaderSceneBuilder("Map","Map");
        Scene scene = sceneBuilder.createScene("Map");
        assertEquals(2,scene.getInteractables().size());
        assertEquals("map", scene.getBackground().getName());
    }
}
