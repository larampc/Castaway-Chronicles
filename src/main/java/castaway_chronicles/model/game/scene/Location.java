package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;

import java.util.HashMap;

public class Location extends Scene {
    private final MainChar mainChar;
    private final TextDisplay textDisplay = new TextDisplay();

    public Location(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, MainChar mainChar) {
        super(background,interactables, visibleInteractables);
        this.mainChar = mainChar;
    }
    public MainChar getMainChar() {return mainChar;}
    public TextDisplay getTextDisplay() {return textDisplay;}
    public void setTextDisplay(String element) {
        textDisplay.activateTextBox(getInteractable(element));
    }
}
