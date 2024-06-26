package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Item itemMock = Mockito.mock(Item.class);
        NPC npcMock = Mockito.mock(NPC.class);
        interactables.put("new Item", itemMock);
        interactables.put("witch", npcMock);
        background = Mockito.mock(Background.class);
        mainChar = null;
        visibleInteractables.put("new Item", itemMock);
    }
    @Test
    public void Location(){
        mainChar = Mockito.mock(MainChar.class);
        Scene scene = SceneFactory.getScene(Game.SCENE.LOCATION,background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Location.class);
    }
    @Test
    public void Backpack(){
        Scene scene = SceneFactory.getScene(Game.SCENE.BACKPACK,background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Backpack.class);
    }
    @Test
    public void Map(){
        Scene scene = SceneFactory.getScene(Game.SCENE.MAP,background,interactables,visibleInteractables, mainChar);
        assertNotNull(scene);
        assertEquals(scene.getClass(), Map.class);
    }
    @Test
    public void noScene(){
        Scene scene = SceneFactory.getScene(Game.SCENE.PAUSE, background,interactables,visibleInteractables, mainChar);
        assertNull(scene);
    }
}
