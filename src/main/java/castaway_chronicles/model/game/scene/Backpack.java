package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;

import java.util.HashMap;

public class Backpack extends Scene {
    public Backpack(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables) {
        super(background, interactables, visibleInteractables);
    }
}
