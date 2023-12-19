package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.InteractableWithText;


public class TextDisplay {
    private boolean activeTextBox;
    private boolean activeChoice;
    private InteractableWithText interactableWithText;
    public TextDisplay() {
        activeTextBox = false;
        activeChoice = false;
    }
    public boolean isActiveTextBox() {return activeTextBox;}
    public InteractableWithText getInteractable() {return interactableWithText;}
    public void activateTextBox(InteractableWithText element) {this.interactableWithText = element; activeTextBox = true;}
    public void activateTextBox() {this.activeTextBox = true;}
    public void closeTextBox() {
        activeTextBox = false; activeChoice = false;}
    public boolean isActiveChoice() {
        return activeChoice;
    }
    public void setActiveChoice(boolean activeChoice) {this.activeChoice = activeChoice;}
}
