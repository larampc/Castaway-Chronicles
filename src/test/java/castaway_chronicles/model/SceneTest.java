package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.*;
import castaway_chronicles.model.game.scene.Scene;
import castaway_chronicles.model.game.scene.SceneFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {
    private Background mockbackground;
    private Interactable mockinteractable;
    private static HashMap<String, Interactable> interactables;
    private static HashMap<String, Interactable> visibleInteractables;
    private static MainChar mainChar;
    private static Scene scene;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        mockinteractable = Mockito.mock(Interactable.class);
        interactables = new HashMap<>();
        interactables.put("new Item", mockinteractable);
        interactables.put("old Item", mockinteractable);
        visibleInteractables = new HashMap<>();
        visibleInteractables.put("new Item", mockinteractable);
        mainChar = null;
        scene = SceneFactory.getScene("Location", mockbackground, interactables, visibleInteractables, mainChar);

    }

    @Test
    public void SceneContent(){
        assert scene != null;
        assertEquals(List.of(mockinteractable, mockinteractable), scene.getInteractables());
        assertEquals(mockbackground, scene.getBackground());
        assertEquals(mockinteractable, scene.getInteractable("new Item"));
        assertEquals(mockinteractable, scene.getInteractable("old Item"));
        assertNull(scene.getInteractable("Item"));
    }

    @Test
    public void SceneSetInvisible(){
        Scene scene = SceneFactory.getScene("Location", mockbackground, interactables, visibleInteractables, mainChar);
        scene.setInvisible("test");
        assertEquals(1, scene.getVisibleInteractables().size());
        scene.setInvisible("new Item");
        assertTrue(scene.getVisibleInteractables().isEmpty());
    }

    @Test
    public void SceneSetVisible(){
        Scene scene = SceneFactory.getScene("Location", mockbackground, interactables, visibleInteractables, mainChar);
        scene.setVisible("new Item");
        assertEquals(1, scene.getVisibleInteractables().size());
        scene.setVisible("old Item");
        assertEquals(2, scene.getVisibleInteractables().size());
    }
}
