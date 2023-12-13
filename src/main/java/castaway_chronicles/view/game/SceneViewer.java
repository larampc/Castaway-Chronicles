package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;

import java.util.List;

public abstract class SceneViewer<T extends Scene> implements PageViewer<Scene> {
    public void drawInteractables(GUI gui, List<Interactable> interactableList) {
        for(Interactable interactable: interactableList) {
            gui.drawImage(interactable.getPosition(), interactable.getName());
        }
    }
    public void drawBackground(GUI gui, Background background) {
        gui.drawImage(background.getPosition(), background.getName());
    }
}
