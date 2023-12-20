package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.scene.Backpack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackpackTest {
    private Backpack backpack;
    private HashMap<String, Interactable> interactables;
    private Background mockbackground;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        interactables = new HashMap<>();
        interactables.put(mockinteractable.getName(),mockinteractable);
        backpack = new Backpack(mockbackground, interactables, interactables);
    }

    @Test
    public void BackpackContent(){
        assertEquals(List.copyOf(interactables.values()), backpack.getInteractables());
        assertEquals(List.copyOf(interactables.values()), backpack.getVisibleInteractables());
        assertEquals(mockbackground, backpack.getBackground());
    }

    @Test
    public void BackpackSetVisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        backpack.setVisible(mockinteractable1.getName());
        interactables.put(mockinteractable1.getName(), mockinteractable1);

        assertEquals(List.copyOf(interactables.values()), backpack.getInteractables());
    }

    @Test
    public void BackpackSetInvisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        backpack.setInvisible(mockinteractable1.getName());
        interactables.remove(mockinteractable1.getName());

        assertEquals(List.copyOf(interactables.values()), backpack.getInteractables());
    }

//    @Test
//    public void getBackpackSelection(){
//        assertEquals(TextDisplay.class, backpack.getTextDisplay().getClass());
//    }
}
