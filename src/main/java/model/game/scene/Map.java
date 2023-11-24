package model.game.scene;

import model.game.elements.Background;
import model.game.elements.Interactable;

import java.util.List;

public class Map extends Scene {
    public Map(Background background, List<Interactable> interactables) {
        super(background, interactables);
    }
}
