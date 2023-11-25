package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public class Scene {
    private List<Interactable> interactables;
    private final Background background;

    public Scene(Background background, List<Interactable> interactables) {
        this.interactables = interactables;
        this.background = background;
    }
    public void addInteractable(Interactable interactable) {
        interactables.add(interactable);
    }
    public void removeInteractable(Interactable interactable) {
        interactables.remove(interactable);
    }
    public List<Interactable> getInteractables() {return interactables;}
    public Background getBackground() {return background;}
}
