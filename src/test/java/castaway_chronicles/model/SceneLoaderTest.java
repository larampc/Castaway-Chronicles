package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.SceneLoader;
import castaway_chronicles.model.game.scene.Scene;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneLoaderTest {
    @Test
    public void getInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene","Location");
        Scene scene = sceneBuilder.createScene();
        List<Interactable> interactables = List.of(new NPC(1,2,3,4,"engineer",0),
                new NPC(1,2,3,4,"witch",0), new Icon(2,2,23,17,"MAP_Icon"), new Icon(180,2,17,25, "BACKPACK_icon"));
        assertTrue(interactables.size() == scene.getInteractables().size() && interactables.containsAll(scene.getInteractables()));
    }
    @Test
    public void getVisibleInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene","Location");
        Scene scene = sceneBuilder.createScene();
        List<Interactable> interactables = List.of(new NPC(1,2,3,4,"engineer",0),
                new Icon(2,2,23,17,"MAP_Icon"), new Icon(180,2,17,25, "BACKPACK_icon"));
        assertTrue(interactables.size() == scene.getVisibleInteractables().size() && interactables.containsAll(scene.getVisibleInteractables()));
    }
    @Test
    public void getBackground() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", "Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals("Beach", scene.getBackground().getName());

        sceneBuilder = new SceneLoader("Scenes", "TestScene2", "Location");
        scene = sceneBuilder.createScene();
        assertNull(scene.getBackground());

        sceneBuilder = new SceneLoader("Scenes","TestScene3", "Location");
        scene = sceneBuilder.createScene();
        assertNull(scene.getBackground());
    }
//    @Test
//    public void hasMainChar() throws IOException {
//        SceneLoader sceneBuilder = new SceneLoader("TestScene2", "Location");
//        Scene scene = sceneBuilder.createScene();
//        assertNotNull(scene);
//        assertTrue(((Location) scene).hasMainChar());
//    }
//    @Test
//    public void hasNotMainChar() throws IOException {
//        SceneLoader sceneBuilder = new SceneLoader("TestScene", "Location");
//        Scene scene = sceneBuilder.createScene();
//        assertNotNull(scene);
//        assertFalse(((Location) scene).hasMainChar());
//    }


    @Test
    public void Location() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene","Location");
        Scene scene = sceneBuilder.createScene();
        assertEquals("Beach",scene.getBackground().getName());
    }
    @Test
    public void BackPack() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestBackpack","Backpack");
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("backpack", scene.getBackground().getName());
    }
    @Test
    public void Map() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestMap","Map");
        Scene scene = sceneBuilder.createScene();
        assertEquals(2,scene.getInteractables().size());
        assertEquals("map", scene.getBackground().getName());
    }
}
