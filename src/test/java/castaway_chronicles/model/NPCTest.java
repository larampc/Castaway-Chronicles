package castaway_chronicles.model;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.gameElements.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {
    private NPC npcDialog;
    @BeforeEach
    void setUp() {
        ResourceManager resourceManagerMock = Mockito.mock(ResourceManager.class);
        Mockito.when(resourceManagerMock.readStaticResourceFile("Dialog/test/test0.txt"))
                .thenReturn(List.of("Hello stranger, you look new around here.",
                "I'm Oriole...", "The Toot Oriole.", "Before you go on with your adventure, do you want me to show you the ropes?",
        "-2", "Sure, why not", "Forget this Toot Oriole nonsense, I'm smart", "1", "3"));
        Mockito.when(resourceManagerMock.readStaticResourceFile("Dialog/test/effect_test1.txt"))
                .thenReturn(List.of("Beach rope V", "NPC toot 2"));
        Mockito.when(resourceManagerMock.readStaticResourceFile("Dialog/test/test1.txt"))
                .thenReturn(List.of("Hmm, where did I leave them?", "I was thinking of using them for the nest.",
        "ok."));
        Mockito.when(resourceManagerMock.readStaticResourceFile("Dialog/test/test3.txt"))
                .thenReturn(List.of("Your loss my friend.",
                        "Good luck."));
        Mockito.when(resourceManagerMock.readStaticResourceFile("Dialog/test/effect_test3.txt"))
                .thenReturn(List.of("NPC toot 5 W",
                        "Map City_icon V"));
        Mockito.when(resourceManagerMock.existsStaticResourceFile("Dialog/test/effect_test1.txt")).thenReturn(true);
        Mockito.when(resourceManagerMock.existsStaticResourceFile("Dialog/test/effect_test3.txt")).thenReturn(true);

        npcDialog = new NPC(0,0,0,0, "test", 0) {
            @Override
            public ResourceManager getResourceManager(){
                return resourceManagerMock;
            }
        };
    }

    @Test
    void NPCDialog() {
        assertEquals(List.of(
                "Sure, why not","Forget this Toot Oriole nonsense, I'm smart"
        ), npcDialog.getChoices().getEntries());
        assertEquals(npcDialog.getNextStates(), List.of(1,3
        ));
        assertTrue(npcDialog.getEffects().isEmpty());
    }

    @Test
    void getText() {
        assertEquals("Hello stranger, you look new around here.", npcDialog.getText());
    }

    @Test
    void goToStateChoice() {
        npcDialog.goToStateChoice();
        assertEquals(List.of("Beach rope V", "NPC toot 2"), npcDialog.getEffects());
        assertEquals("Hmm, where did I leave them?", npcDialog.getText());
        assertEquals(1, npcDialog.getState());
    }

    @Test
    void goToState() {
        npcDialog.goToState(3);
        assertEquals(List.of("NPC toot 5 W", "Map City_icon V"), npcDialog.getEffects());
        assertEquals("Your loss my friend.", npcDialog.getText());
        assertEquals(3, npcDialog.getState());
    }

    @Test
    void nextLine() {
        assertEquals("Hello stranger, you look new around here.", npcDialog.getText());
        npcDialog.nextLine();
        assertEquals("I'm Oriole...", npcDialog.getText());
        assertFalse(npcDialog.dialogEnded());
        npcDialog.nextLine();
        npcDialog.nextLine();
        assertTrue(npcDialog.dialogEnded());
    }
}
