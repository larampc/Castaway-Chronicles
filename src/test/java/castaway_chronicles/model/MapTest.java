package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.scene.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    private Map map;
    private HashMap<String, Interactable> interactables;
    private Background mockbackground;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        interactables = new HashMap<>();
        interactables.put(mockinteractable.getName(), mockinteractable);
        map = new Map(mockbackground, interactables, interactables);
    }

    @Test
    public void MapContent(){
        assertEquals(List.copyOf(interactables.values()), map.getInteractables());
        assertEquals(List.copyOf(interactables.values()), map.getVisibleInteractables());
        assertEquals(mockbackground, map.getBackground());
    }

    @Test
    public void MapSetVisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        map.setVisible(mockinteractable1.getName());
        interactables.put(mockinteractable1.getName(), mockinteractable1);

        assertEquals(List.copyOf(interactables.values()), map.getInteractables());
    }

    @Test
    public void MapSetInvisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        map.setInvisible(mockinteractable1.getName());
        interactables.remove(mockinteractable1.getName());

        assertEquals(List.copyOf(interactables.values()), map.getInteractables());
    }
}
