package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("Hmm, where did I leave them?", npcDialog.getText());
        assertEquals(1, npcDialog.getState());
    }

    @Test
    void goToState() throws IOException {
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
