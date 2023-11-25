package castaway_chronicles.model;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InteractableTest {

        @Test
        public void contains(){
            Interactable interactable = new Item(1,2,3,4,"new item");
            assertTrue(interactable.contains(new Position(2,4)));
            assertTrue(interactable.contains(new Position(3,2)));
            assertTrue(interactable.contains(new Position(2,5)));
            assertTrue(interactable.contains(new Position(3,5)));
            assertFalse(interactable.contains(new Position(4,2)));
            assertFalse(interactable.contains(new Position(2,6)));
            assertFalse(interactable.contains(new Position(4,6)));
            Interactable interactable2 = new Item(1,2,1,1,"new item");
            assertTrue(interactable2.contains(new Position(1,2)));
            assertFalse(interactable2.contains(new Position(1,1)));
            assertFalse(interactable2.contains(new Position(1,3)));
            assertFalse(interactable2.contains(new Position(0,2)));
            assertFalse(interactable2.contains(new Position(2,2)));
    }
}
