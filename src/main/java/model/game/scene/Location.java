package model.game.scene;

import model.game.elements.Background;
import model.game.elements.Interactable;

import java.util.List;

public class Location extends Scene {
    private boolean mainChar = false;
    public Location(Background background, List<Interactable> interactables) {
        super(background,interactables);
    }
    public void leftLocation() {this.mainChar = false;}
    public void enteredLocation() {this.mainChar = true;}
}
