package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Backpack;

public class BackpackViewer extends SceneViewer<Backpack> {
    public BackpackViewer(Backpack model) {
        super(model);
    }
    public void draw(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
    }
}
