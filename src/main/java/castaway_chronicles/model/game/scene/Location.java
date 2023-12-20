package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.MainChar;

import java.util.HashMap;

public class Location extends Scene {
    private final MainChar mainChar;

    public Location(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, MainChar mainChar) {
        super(background,interactables, visibleInteractables);
        this.mainChar = mainChar;
    }
    public MainChar getMainChar() {return mainChar;}

}
