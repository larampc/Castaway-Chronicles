package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.Images;

import java.util.HashMap;

public class LocationViewer extends SceneViewer<Location> {

    public LocationViewer(Location model, HashMap<String, Images> images) {
        super(model, images);
    }
    @Override
    public void drawElements(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
    }
    public void drawMainChar(GUI gui) {
        //
    }
}
