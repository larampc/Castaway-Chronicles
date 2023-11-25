package model;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InteractableTest {

        @Test
        public void contains(){
            Interactable interactable = new Item(1,2,3,4,"new item");
            assertTrue(interactable.contains(new Position(3,5)));
            assertTrue(interactable.contains(new Position(4,3)));
            assertTrue(interactable.contains(new Position(3,6)));
            assertTrue(interactable.contains(new Position(4,6)));
            assertFalse(interactable.contains(new Position(5,3)));
            assertFalse(interactable.contains(new Position(3,7)));
            assertFalse(interactable.contains(new Position(5,7)));
    }
}
