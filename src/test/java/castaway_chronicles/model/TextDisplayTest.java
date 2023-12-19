package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.model.game.scene.TextDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TextDisplayTest {
    private TextDisplay textDisplay;
    private InteractableWithText mockinteractable;
    @BeforeEach
    void setUp() {
        textDisplay = new TextDisplay();
        mockinteractable = Mockito.mock(InteractableWithText.class);
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
        textDisplay.activateTextBox();
        assertTrue(textDisplay.isActiveTextBox());
        textDisplay.activateTextBox();
        assertTrue(textDisplay.isActiveTextBox());
    }

    @Test
    public void element(){
        assertNull(textDisplay.getInteractable());
        textDisplay.activateTextBox(mockinteractable);
        assertEquals(mockinteractable, textDisplay.getInteractable());
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
