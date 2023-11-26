package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {
    private Background mockbackground;
    private Interactable mockinteractable;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        mockinteractable = Mockito.mock(Interactable.class);
    }

    @Test
    public void SceneContent(){
        ArrayList<Interactable> interactables = new ArrayList<>();
        interactables.add(mockinteractable);

        Scene scene = new Scene(mockbackground, interactables,interactables);
        assertEquals(interactables, scene.getInteractables());
        assertEquals(mockbackground, scene.getBackground());
    }

    @Test
    public void SceneAdd(){
        ArrayList<Interactable> interactables = new ArrayList<>();

        Scene scene = new Scene(mockbackground, interactables,interactables);

        scene.addInteractable(mockinteractable);
        interactables.add(mockinteractable);

        assertEquals(interactables, scene.getInteractables());
    }

    @Test
    public void SceneRemove(){
        ArrayList<Interactable> interactables1 = new ArrayList<>();
        ArrayList<Interactable> interactables2 = new ArrayList<>();
        interactables1.add(mockinteractable);

        Scene scene = new Scene(mockbackground, interactables1,interactables1);

        scene.removeInteractable(mockinteractable);

        assertEquals(interactables2, scene.getInteractables());
    }
    @Test
    public void SceneSetInvisible(){
        ArrayList<Interactable> interactables1 = new ArrayList<>();
        interactables1.add(mockinteractable);
        Scene scene = new Scene(mockbackground, interactables1,interactables1);
        scene.setInvisible(mockinteractable);
        assertTrue(scene.getVisibleInteractables().isEmpty());
    }
    @Test
    public void SceneSetVisible(){
        ArrayList<Interactable> interactables1 = new ArrayList<>();
        interactables1.add(mockinteractable);
        Scene scene = new Scene(mockbackground, interactables1,new ArrayList<>());
        assertTrue(scene.getVisibleInteractables().isEmpty());
        scene.setVisible(mockinteractable);
        assertFalse(scene.getVisibleInteractables().isEmpty());
    }
}
