package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;
import castaway_chronicles.view.Viewer;
import java.util.List;

public abstract class SceneViewer<T extends Scene> extends Viewer<Scene> {

    public SceneViewer(T model) {
        super(model);
    }
    public void drawInteractables(GUI gui) {
        List<Interactable> interactableList = getModel().getInteractables();
        for(Interactable interactable: interactableList) {
            gui.drawImage(interactable.getPosition(), interactable.getName());
        }
    }
    public void drawBackground(GUI gui) {
        gui.drawImage(getModel().getBackground().getPosition(), getModel().getBackground().getName());
    }
}
