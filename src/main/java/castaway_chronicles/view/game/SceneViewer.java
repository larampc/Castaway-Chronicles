package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;

import java.util.List;

public abstract class SceneViewer<T extends Scene> {
    private final T model;
    public SceneViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void drawInteractables(GUI gui) {
        List<Interactable> interactableList = model.getInteractables();
        for(Interactable interactable: interactableList) {
            gui.drawImage(interactable.getPosition(), interactable.getName());
        }
    }
    public void drawBackground(GUI gui) {
        gui.drawImage(model.getBackground().getPosition(), model.getBackground().getName());
    }
}
