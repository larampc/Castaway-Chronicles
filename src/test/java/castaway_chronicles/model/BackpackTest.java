package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Backpack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackpackTest {
    private Backpack backpack;
    private ArrayList<Interactable> interactables;
    private Background mockbackground;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        interactables = new ArrayList<>();
        interactables.add(mockinteractable);
        backpack = new Backpack(mockbackground, interactables, interactables);
    }

    @Test
    public void BackpackContent(){
        assertEquals(interactables, backpack.getInteractables());
        assertEquals(interactables, backpack.getVisibleInteractables());
        assertEquals(mockbackground, backpack.getBackground());
    }

    @Test
    public void BackpackSetVisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        backpack.setVisible(mockinteractable1);
        interactables.add(mockinteractable1);

        assertEquals(interactables, backpack.getInteractables());
    }

    @Test
    public void BackpackSetInvisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        backpack.setInvisible(mockinteractable1);
        interactables.remove(mockinteractable1);

        assertEquals(interactables, backpack.getInteractables());
    }
}
