package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NPCTest {
    private NPC npcDialog;
    @BeforeEach
    void init() throws IOException {
        npcDialog = new NPC(0,0,0,0, "toot", 0);
    }

    @Test
    void NPCDialog() {
        assertEquals(npcDialog.getChoices().getEntries(),List.of(
                "Sure, why not","Forget this Toot Oriole nonsense, I'm smart"
        ));
        assertEquals(npcDialog.getNextStates(), List.of(1,3
        )); //rever
        assertTrue(npcDialog.getEffects().isEmpty());
    }

    @Test
    void getText() {
        assertEquals("Hello stranger, you look new around here.", npcDialog.getText());
    }

    @Test
    void goToStateChoice() throws IOException {
        npcDialog.goToStateChoice();
        assertEquals(List.of("Beach rope V", "NPC toot 2"), npcDialog.getEffects());
        assertEquals(0, npcDialog.getLine());
        assertEquals("Hmm, where did I leave them?", npcDialog.getCurrentLine());
        assertEquals(1, npcDialog.getState());
    }

    @Test
    void goToState() throws IOException {
        npcDialog.goToState(3);
        assertEquals(List.of("NPC toot 5 W", "Map City_icon V"), npcDialog.getEffects());
        assertEquals(0, npcDialog.getLine());
        assertEquals("Your loss my friend.", npcDialog.getCurrentLine());
        assertEquals(1, npcDialog.getMax());
        assertEquals(3, npcDialog.getState());
    }

    @Test
    void nextLine() {
        assertEquals(0, npcDialog.getLine());
        assertEquals("Hello stranger, you look new around here.", npcDialog.getCurrentLine());
        npcDialog.nextLine();
        assertEquals(1, npcDialog.getLine());
        assertEquals("I'm Oriole...", npcDialog.getCurrentLine());
    }
}
