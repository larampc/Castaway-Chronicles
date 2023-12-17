package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InteractableFactoryTest {
    @Test
    public void getInteractable() throws IOException {
        assertEquals(new Item(1, 2, 3, 4, "rope"), InteractableFactory.getInteractable("ITEM", 1, 2, 3, 4, "rope", 0));
        assertEquals(new NPC(1, 2, 3, 4, "toot", 4), InteractableFactory.getInteractable("NPC", 1, 2, 3, 4, "toot", 4));
        assertEquals(new Icon(1, 2, 3, 4, "MAP"), InteractableFactory.getInteractable("ICON", 1, 2, 3, 4, "MAP", 0));
        assertEquals(new ItemBackpack(1, 2, 3, 4, "rope_backpack"), InteractableFactory.getInteractable("BITEM", 1, 2, 3, 4, "rope_backpack", 0));
        assertNull(InteractableFactory.getInteractable("TEST", 1, 2, 3, 4, "toot", 4));
    }
}
