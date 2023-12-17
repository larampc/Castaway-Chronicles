package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.TextDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TextDisplayTest {
    private TextDisplay textDisplay;
    private Interactable mockinteractable;
    @BeforeEach
    void setUp() {
        textDisplay = new TextDisplay();
        mockinteractable = Mockito.mock(Interactable.class);
    }

    @Test
    public void activeTextBox(){
        assertFalse(textDisplay.isActiveTextBox());
        textDisplay.activateTextBox(mockinteractable);
        assertTrue(textDisplay.isActiveTextBox());
        textDisplay.activateTextBox(mockinteractable);
        assertTrue(textDisplay.isActiveTextBox());
        textDisplay.closeTextBox();
        assertFalse(textDisplay.isActiveTextBox());
    }

    @Test
    public void element(){
        assertNull(textDisplay.getElement());
        textDisplay.activateTextBox(mockinteractable);
        assertEquals(mockinteractable, textDisplay.getElement());
    }

    @Test
    public void activeChoice(){
        assertFalse(textDisplay.isActiveChoice());
        textDisplay.setActiveChoice(true);
        assertTrue(textDisplay.isActiveChoice());
        textDisplay.setActiveChoice(false);
        assertFalse(textDisplay.isActiveChoice());
        textDisplay.setActiveChoice(true);
        assertTrue(textDisplay.isActiveChoice());
        textDisplay.closeTextBox();
        assertFalse(textDisplay.isActiveChoice());
    }
}
