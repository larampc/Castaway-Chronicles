package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    private Map map;
    private ArrayList<Interactable> interactables;
    private Background mockbackground;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        interactables = new ArrayList<>();
        interactables.add(mockinteractable);
        map = new Map(mockbackground, interactables, interactables);
    }

    @Test
    public void MapContent(){
        assertEquals(interactables, map.getInteractables());
        assertEquals(interactables, map.getVisibleInteractables());
        assertEquals(mockbackground, map.getBackground());
    }

    @Test
    public void MapSetVisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        map.setVisible(mockinteractable1);
        interactables.add(mockinteractable1);

        assertEquals(interactables, map.getInteractables());
    }

    @Test
    public void MapSetInvisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        map.setInvisible(mockinteractable1);
        interactables.remove(mockinteractable1);

        assertEquals(interactables, map.getInteractables());
    }
}
