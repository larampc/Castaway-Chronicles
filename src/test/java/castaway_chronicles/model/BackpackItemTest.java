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
    public static void setup() {
        backpackItem = new BackpackItem(1,2,3,4,"rope_backpack");
    }

    @Test
    public void constructor() {
        assertThrows(Throwable.class, () -> new BackpackItem(1, 2, 3, 4, "test"));
    }

    @Test
    public void ItemBackpackContent() {
        assertEquals(List.of("GIVE"), backpackItem.getChoices().getEntries());
        backpackItem.setInHand(false);
        assertEquals("The perfect rope to make nests with", backpackItem.getText());
        assertEquals("give toot", backpackItem.getCommand());
        backpackItem.setInHand(true);
        assertEquals("No...", backpackItem.getText());
        assertEquals(List.of("NPC toot 4", "Backpack rope I"), backpackItem.getEffects());
    }

    @Test
    public void setNameBackpack() {
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
}
