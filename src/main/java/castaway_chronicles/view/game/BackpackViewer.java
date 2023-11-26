package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Backpack;


public class BackpackViewer extends SceneViewer<Backpack> {
    protected BackpackViewer(Backpack model, Images images) {
        super(model, images);
    }
    @Override
    protected void drawElements(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
    }
}
