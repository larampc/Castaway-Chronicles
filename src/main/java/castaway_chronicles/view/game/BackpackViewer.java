package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.view.Viewer;

import java.util.List;

public class BackpackViewer extends Viewer<Backpack> {
    private final Images images;
    protected BackpackViewer(Backpack model, Images images) {
        super(model);
        this.images = images;
    }
    @Override
    protected void drawElements(GUI gui) {
        List<Interactable> interactableList = getModel().getInteractables();
        ImageViewer viewer = new ImageViewer();
        viewer.draw(gui, getModel().getBackground(), images);
        for(Interactable interactable: interactableList) {
            viewer.draw(gui, interactable, images);
        }
    }
}
