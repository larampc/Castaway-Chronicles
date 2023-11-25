package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.Viewer;

import java.util.List;

public class LocationViewer extends Viewer<Location> {
    private final Images images;
    protected LocationViewer(Location model, Images images) {
        super(model);
        this.images = images;
    }
    @Override
    public void drawElements(GUI gui) {
        List<Interactable> interactableList = getModel().getInteractables();
        ImageViewer viewer = new ImageViewer();
        viewer.draw(gui, getModel().getBackground(), images);
        for(Interactable interactable: interactableList) {
            viewer.draw(gui, interactable, images);
        }
        if (getModel().hasMainChar()) {} //drawMainChar(GUI gui, Images images);
    }
}
