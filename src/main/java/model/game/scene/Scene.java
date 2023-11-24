package model.game.scene;

import model.game.elements.Background;
import model.game.elements.Interactable;

import java.util.List;

public abstract class Scene {
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
}
