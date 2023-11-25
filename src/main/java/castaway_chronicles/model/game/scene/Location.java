package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public class Location extends Scene {
    private boolean mainChar = false;
    public Location(Background background, List<Interactable> interactables) {
        super(background,interactables);
    }
    public void leftLocation() {this.mainChar = false;}
    public void enteredLocation() {this.mainChar = true;}
}
