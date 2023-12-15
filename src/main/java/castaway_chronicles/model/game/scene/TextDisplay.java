package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Element;


public class TextDisplay {
    private boolean activeTextBox;
    private boolean activeChoice;
    private Element element;
    public TextDisplay() {
        activeTextBox = false;
        activeChoice = false;
    }
    public boolean isActiveTextBox() {return activeTextBox;}
    public Element getElement() {return element;}
    public void activateTextBox(Element element) {this.element = element; activeTextBox = true;}
    public void closeTextBox() {
        activeTextBox = false; activeChoice = false;}
    public boolean isActiveChoice() {
        return activeChoice;
    }
    public void setActiveChoice(boolean activeChoice) {this.activeChoice = activeChoice;}
}
