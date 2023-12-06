package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Backpack;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackViewer extends SceneViewer<Backpack> {
    public BackpackViewer(Backpack model) {
        super(model);
    }
    public void draw(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawBackground(gui);
        drawInteractables(gui);
        if (getModel().getBackpackSelection().isSelection()) {
            new BackpackSelectionViewer(getModel().getBackpackSelection().getItem()).drawBackpackSelectionViewer(gui);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
