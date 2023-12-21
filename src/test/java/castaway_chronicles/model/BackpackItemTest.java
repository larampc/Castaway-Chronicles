package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.BackpackItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackpackItemTest {
    private static BackpackItem backpackItem;
    @BeforeAll
    public static void setup() throws IOException {
        backpackItem = new BackpackItem(1,2,3,4,"rope_backpack");
    }

    @Test
    public void constructor() {
        assertThrows(Throwable.class, () -> new BackpackItem(1, 2, 3, 4, "test"));
    }

    @Test
    public void ItemBackpackContent() throws IOException {
        assertEquals(List.of("GIVE"), backpackItem.getChoices().getEntries());
        backpackItem.setInHand(false);
        assertEquals("The perfect rope to make nests with", backpackItem.getText());
        assertEquals("give toot", backpackItem.getCommand());
        backpackItem.setInHand(true);
        assertEquals("No...", backpackItem.getText());
        assertEquals(List.of("NPC toot 4", "Backpack rope I"), backpackItem.getEffects());
    }

    @Test
    public void setNameBackpack() throws IOException {
        assertEquals("rope_backpack", backpackItem.getName());
        backpackItem.setNameBackpack("rock_backpack");
        assertEquals("rock_backpack", backpackItem.getName());
        assertEquals(List.of("DRINK", "THROW", "GIVE"), backpackItem.getChoices().getEntries());
        backpackItem.setInHand(false);
        assertEquals("A very heavy rock", backpackItem.getText());
        assertEquals("use", backpackItem.getCommand());
        backpackItem.setInHand(true);
        assertEquals("No...", backpackItem.getText());
        assertEquals(Collections.emptyList(), backpackItem.getEffects());
    }
//    @Test
//    public void interactable() {
//        assertEquals(new Position(1,2), interactable.getPosition());
//        interactable.setPosition(new Position(2,3));
//        assertEquals(new Position(2,3), interactable.getPosition());
//    }
//    @Test
//    public void contains_inside(){
//        assertTrue(interactable.contains(new Position(2,4)));
//    }
//
//    @Test
//    public void contains_border() {
//        assertTrue(interactable.contains(new Position(1,2)));
//        assertTrue(interactable.contains(new Position(3,2)));
//        assertTrue(interactable.contains(new Position(1,5)));
//        assertTrue(interactable.contains(new Position(3,5)));
//        assertTrue(interactable.contains(new Position(3,3)));
//    }
//
//    @Test
//    public void contains_out() {
//        assertFalse(interactable.contains(new Position(4,2)));
//        assertFalse(interactable.contains(new Position(2,6)));
//        assertFalse(interactable.contains(new Position(10,10)));
//    }
//
//    @Test
//    public void equals_same(){
//        Interactable interactable2 = new Item(1,2,3,4,"item");
//        assertEquals(interactable, interactable2);
//        assertEquals(interactable, interactable);
//    }
//
//    @Test
//    public void equals_different(){
//        Interactable interactable2 = new Item(1,2,3,4,"different item");
//        assertNotEquals(interactable, interactable2);
//        assertNotEquals(interactable, new Object());
//    }
//
//
//    @Test
//    public void hashcode_same(){
//        Interactable interactable2 = new Item(1,2,3,4,"item");
//        assertEquals(interactable.hashCode(), interactable2.hashCode());
//        assertEquals(interactable.hashCode(), interactable.hashCode());
//    }
//
//    @Test
//    public void hashcode_different(){
//        Interactable interactable2 = new Item(1,2,3,4,"different item");
//        assertNotEquals(interactable.hashCode(), interactable2.hashCode());
//        assertNotEquals(interactable.hashCode(), new Object().hashCode());
//    }
}
