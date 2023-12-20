package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.InteractableWithText;


public class TextBox {
    private boolean activeTextBox;
    private boolean activeChoice;
    private InteractableWithText interactableWithText;
    public TextBox() {
        activeTextBox = false;
        activeChoice = false;
    }
    public boolean isActiveTextBox() {return activeTextBox;}
    public boolean isActiveChoice() {
        return activeChoice;
    }
    public InteractableWithText getInteractable() {return interactableWithText;}
    public void activateTextBox(InteractableWithText interactable) {this.interactableWithText = interactable; activeTextBox = true;}
    public void activateTextBox() {this.activeTextBox = true;}
    public void closeTextBox() {
        activeTextBox = false; activeChoice = false;}
    public void setActiveChoice(boolean activeChoice) {this.activeChoice = activeChoice;}
}
