package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.HashMap;

public class Map extends Scene {
    public Map(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables) {
        super(background, interactables, visibleInteractables);
    }
}
