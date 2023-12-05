package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationViewer extends SceneViewer<Location> {

    public LocationViewer(Location model) {
        super(model);
    }

    public void draw(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
        if (getModel().getDialogState().isActiveDialog()) {
            new NPCDialogViewer(getModel().getDialogState().getNPCDialog()).drawNPCDialog(gui);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
        if (getModel().getDialogState().isActiveChoice()) {
            new NPCDialogViewer(getModel().getDialogState().getNPCDialog()).drawNPCDialogChoices(gui);
        }
    }

    private void drawMainChar(GUI gui) {
        if (getModel().getMainChar() == null) return;
        gui.drawImage(getModel().getMainChar().getPosition(), getModel().getMainChar().getName());
    }
}
