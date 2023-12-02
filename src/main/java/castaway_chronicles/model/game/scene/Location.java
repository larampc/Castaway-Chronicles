package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;

import java.util.List;

public class Location extends Scene {
    private MainChar mainChar;
    public Location(Background background, List<Interactable> interactables, List<Interactable> visibleInteractables, MainChar mainChar) {
        super(background,interactables, visibleInteractables);
        this.mainChar = mainChar;
    }
    public MainChar getMainChar() {return mainChar;}
    public void setMainChar(MainChar mainChar) {this.mainChar = mainChar;}
}
