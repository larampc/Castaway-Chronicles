package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.SceneLoader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneLoaderTest {
    @Test
    public void getInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(npcMock.getName()).thenReturn("engineer");
        NPC npcMock2 = Mockito.mock(NPC.class);
        Mockito.when(npcMock2.getName()).thenReturn("witch");
        Item itemMock = Mockito.mock(Item.class);
        Mockito.when(itemMock.getName()).thenReturn("rope");
        Icon iconMock = Mockito.mock(Icon.class);
        Mockito.when(iconMock.getName()).thenReturn("MAP_icon");
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        Mockito.when(backpackItemMock.getName()).thenReturn("rope_backpack");
        List<Interactable> interactables = List.of(npcMock,
                npcMock2,
                iconMock,
                itemMock,
                backpackItemMock);
        assertTrue(interactables.size() == scene.getInteractables().size() && interactables.containsAll(scene.getInteractables()));
    }
    @Test
    public void getVisibleInteractables() throws IOException {
        SceneLoader sceneBuilder = new SceneLoader("Scenes", "TestScene", Game.SCENE.LOCATION);
        Scene scene = sceneBuilder.createScene();
        Icon iconMock = Mockito.mock(Icon.class);
        Mockito.when(iconMock.getName()).thenReturn("MAP_icon");
        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(npcMock.getName()).thenReturn("engineer");
        List<Interactable> interactables = List.of(npcMock,
                iconMock);
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
