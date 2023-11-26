package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location location;
    @BeforeEach
    void setUp() {
        Background mockbackground = Mockito.mock(Background.class);
        Interactable mockinteractable = Mockito.mock(Interactable.class);
        ArrayList<Interactable> interactables = new ArrayList<>();
        interactables.add(mockinteractable);
        location = new Location(mockbackground, interactables,interactables);
    }

    @Test
    public void LocationLeftEntered(){
        assertFalse(location.hasMainChar());
        location.leftLocation();
        assertFalse(location.hasMainChar());
        location.enteredLocation();
        assertTrue(location.hasMainChar());
        location.enteredLocation();
        assertTrue(location.hasMainChar());
        location.leftLocation();
        assertFalse(location.hasMainChar());
    }
}
