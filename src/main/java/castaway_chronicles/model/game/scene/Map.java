package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public class Map extends Scene {
    public Map(Background background, List<Interactable> interactables, List<Interactable> visibleInteractables) {
        super(background, interactables, visibleInteractables);
    }
}
