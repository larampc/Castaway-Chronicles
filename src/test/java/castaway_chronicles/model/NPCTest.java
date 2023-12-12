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
        ));
        assertTrue(npcDialog.getEffects().isEmpty());
    }

    @Test
    void changeState() throws IOException {
        npcDialog.goToStateChoice();
        assertEquals(npcDialog.getEffects(),List.of("Beach rope V",
                "NPC toot 2"));
    }
}