package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public class Location extends Scene {
    private boolean mainChar = false;
    public Location(Background background, List<Interactable> interactables, List<Interactable> visibleInteractables) {
        super(background,interactables, visibleInteractables);
    }
    public void leftLocation() {this.mainChar = false;}
    public void enteredLocation() {this.mainChar = true;}
    public boolean hasMainChar() {return mainChar;}
}
