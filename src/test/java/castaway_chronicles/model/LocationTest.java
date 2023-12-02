package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location location;
    private HashMap<String, Interactable> interactables;
    private Background mockbackground;
    @BeforeEach
    void setUp() {
        mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        interactables = new HashMap<>();
        interactables.put(mockinteractable.getName(), mockinteractable);
        MainChar mockmainchar = Mockito.mock(MainChar.class);
        location = new Location(mockbackground, interactables, interactables, mockmainchar);
    }

    @Test
    public void LocationContent(){
        assertEquals(List.copyOf(interactables.values()), location.getInteractables());
        assertEquals(List.copyOf(interactables.values()), location.getVisibleInteractables());
        assertEquals(mockbackground, location.getBackground());
    }

    @Test
    public void LocationSetVisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        location.setVisible(mockinteractable1.getName());
        interactables.put(mockinteractable1.getName(), mockinteractable1);

        assertEquals(List.copyOf(interactables.values()), location.getInteractables());
    }

    @Test
    public void LocationSetInvisible(){
        Interactable mockinteractable1 = Mockito.mock(Interactable.class);
        location.setInvisible(mockinteractable1.getName());
        interactables.remove(mockinteractable1.getName());

        assertEquals(List.copyOf(interactables.values()), location.getInteractables());
    }

//    @Test
//    public void LocationLeftEntered(){
//        assertFalse(location.hasMainChar());
//        location.leftLocation();
//        assertFalse(location.hasMainChar());
//        location.enteredLocation();
//        assertTrue(location.hasMainChar());
//        location.enteredLocation();
//        assertTrue(location.hasMainChar());
//        location.leftLocation();
//        assertFalse(location.hasMainChar());
//    }
}
