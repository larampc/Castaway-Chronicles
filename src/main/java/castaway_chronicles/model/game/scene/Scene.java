package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public abstract class Scene {
    private final List<Interactable> interactables;
    private final Background background;
    private final List<Interactable> visibleInteractables;

    public Scene(Background background, List<Interactable> interactables, List<Interactable> visibleInteractables) {
        this.interactables = interactables;
        this.background = background;
        this.visibleInteractables = visibleInteractables;
    }
    public List<Interactable> getInteractables() {return interactables;}
    public Background getBackground() {return background;}

    public List<Interactable> getVisibleInteractables() {
        return visibleInteractables;
    }

    public void setVisible(Interactable interactable){
        visibleInteractables.add(interactable);
    }
    public void setInvisible(Interactable interactable){
        visibleInteractables.remove(interactable);
    }
}
