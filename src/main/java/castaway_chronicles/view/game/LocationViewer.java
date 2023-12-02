package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class LocationViewer extends SceneViewer<Location> {

    public LocationViewer(Location model) {
        super(model);
    }

    public void draw(GUI gui) throws IOException, InterruptedException {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
        if (getModel().isDialog()) {
            new NPCDialogViewer(getModel().getNPCDialog()).drawNPCDialog(gui);
        }
    }

    private void drawMainChar(GUI gui) {
        gui.drawImage(getModel().getMainChar().getPosition(), getModel().getMainChar().getName());
    }
}
