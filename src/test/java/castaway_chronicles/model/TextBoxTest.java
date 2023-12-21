package castaway_chronicles.model;

import castaway_chronicles.model.game.scene.TextBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TextBoxTest {
    private TextBox textBox;
    private InteractableWithText mockinteractable;
    @BeforeEach
    void setUp() {
        textBox = new TextBox();
        mockinteractable = Mockito.mock(InteractableWithText.class);
    }

    @Test
    public void activeTextBox(){
        assertFalse(textBox.isActiveTextBox());
        textBox.activateTextBox(mockinteractable);
        assertTrue(textBox.isActiveTextBox());
        textBox.activateTextBox(mockinteractable);
        assertTrue(textBox.isActiveTextBox());
        textBox.closeTextBox();
        assertFalse(textBox.isActiveTextBox());
        textBox.activateTextBox();
        assertTrue(textBox.isActiveTextBox());
        textBox.activateTextBox();
        assertTrue(textBox.isActiveTextBox());
    }

    @Test
    public void element(){
        assertNull(textBox.getInteractable());
        textBox.activateTextBox(mockinteractable);
        assertEquals(mockinteractable, textBox.getInteractable());
    }

    @Test
    public void activeChoice(){
        assertFalse(textBox.isActiveChoice());
        textBox.setActiveChoice(true);
        assertTrue(textBox.isActiveChoice());
        textBox.setActiveChoice(false);
        assertFalse(textBox.isActiveChoice());
        textBox.setActiveChoice(true);
        assertTrue(textBox.isActiveChoice());
        textBox.closeTextBox();
        assertFalse(textBox.isActiveChoice());
    }
}
