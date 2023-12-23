package castaway_chronicles.model;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackpackItemTest {
    private static BackpackItem backpackItem;
    private ResourceManager resourceManagerMock;
    @BeforeEach
    public void setUp() {
        resourceManagerMock = Mockito.mock(ResourceManager.class);
        Mockito.when(resourceManagerMock.readStaticResourceFile("BackpackItems/testBackpackItem.txt"))
                .thenReturn(List.of("The perfect rope to make nests with",
                "GIVE - give toot",
                "No..."));
        Mockito.when(resourceManagerMock.readStaticResourceFile("BackpackItems/rock.txt"))
                .thenReturn(List.of("A rock", "DRINK - use",
                        "I'm not gonna drink a rock...",
                        "THROW - give witchwindow",
                        "You have really bad aim...",
                        "EATT - give",
        "The rock is gonna be sad if you give her away..."));
        Mockito.when(resourceManagerMock.existsStaticResourceFile("BackpackItems/testBackpackItem_give_effects.txt")).thenReturn(true);
        Mockito.when(resourceManagerMock.readStaticResourceFile("BackpackItems/testBackpackItem_give_effects.txt"))
                .thenReturn(List.of("NPC toot 4", "Backpack rope I"));
        Mockito.when(resourceManagerMock.existsStaticResourceFile("BackpackItems/rock_use_effects.txt")).thenReturn(false);
        backpackItem = new BackpackItem(1,2,3,4,"testBackpackItem_backpack"){
            @Override
            public ResourceManager getResourceManager(){
                return resourceManagerMock;
            }
        };
    }
    @Test
    void ItemBackpackContent() {
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
        assertEquals("testBackpackItem_backpack", backpackItem.getName());
        backpackItem.setNameBackpack("rock_backpack");
        assertEquals("rock_backpack", backpackItem.getName());
        backpackItem.setInHand(true);
        Mockito.verify(resourceManagerMock).readStaticResourceFile("BackpackItems/rock.txt");
        assertEquals("I'm not gonna drink a rock...", backpackItem.getText());
        assertEquals("use", backpackItem.getCommand());
        assertEquals(Collections.emptyList(), backpackItem.getEffects());
    }
}
