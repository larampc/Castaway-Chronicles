package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;

public abstract class SceneViewer<T extends Scene> {
    private final T model;
    public SceneViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void drawInteractables(GUI gui) {
        for(Interactable interactable: model.getVisibleInteractables()) {
            gui.drawImage(interactable.getPosition(), interactable.getName());
        }
    }
    public void drawBackground(GUI gui) {
        gui.drawImage(model.getBackground().getPosition(), model.getBackground().getName());
    }
}
