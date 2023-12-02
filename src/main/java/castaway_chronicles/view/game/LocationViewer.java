package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Location;

public class LocationViewer extends SceneViewer<Location> {

    public LocationViewer(Location model) {
        super(model);
    }

    public void draw(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
    }
    public void drawMainChar(GUI gui) {
        gui.drawImage(getModel().getMainChar().getPosition(), getModel().getMainChar().getName());
    }
}
