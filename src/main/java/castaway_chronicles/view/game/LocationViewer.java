package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationViewer extends SceneViewer<Location> {

    public LocationViewer(Location model) {
        super(model);
    }

    public void draw(GUI gui, TextBoxViewer textBoxViewer) throws IOException, InterruptedException, URISyntaxException {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
        if (getModel().getTextDisplay().isActiveChoice()) {
            textBoxViewer.drawChoices(gui);
        }
        else if (getModel().getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, Game.SCENE.LOCATION);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }

    private void drawMainChar(GUI gui) {
        if (getModel().getMainChar() == null) return;
        gui.drawImage(getModel().getMainChar().getPosition(), getModel().getMainChar().getName());
    }
}
