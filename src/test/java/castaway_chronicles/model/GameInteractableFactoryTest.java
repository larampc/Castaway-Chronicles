package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GameInteractableFactoryTest {
    @Test
    public void getInteractable() throws IOException {
        assertEquals(new Item(1, 2, 3, 4, "rope"), GameInteractableFactory.getInteractable("ITEM", 1, 2, 3, 4, "rope", 0));
        assertEquals(new NPC(1, 2, 3, 4, "toot", 4), GameInteractableFactory.getInteractable("NPC", 1, 2, 3, 4, "toot", 4));
        assertEquals(new Icon(1, 2, 3, 4, "MAP"), GameInteractableFactory.getInteractable("ICON", 1, 2, 3, 4, "MAP", 0));
        assertEquals(new BackpackItem(1, 2, 3, 4, "rope_backpack"), GameInteractableFactory.getInteractable("BITEM", 1, 2, 3, 4, "rope_backpack", 0));
        assertNull(GameInteractableFactory.getInteractable("TEST", 1, 2, 3, 4, "toot", 4));
    }
}
