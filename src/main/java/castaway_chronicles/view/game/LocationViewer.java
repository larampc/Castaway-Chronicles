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

    public void draw(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        drawBackground(gui);
        drawInteractables(gui);
        drawMainChar(gui);
        if (getModel().getTextDisplay().isActiveTextBox()) {
            new TextBoxViewer(getModel().getTextDisplay().getElement()).drawTextBox(gui, Game.SCENE.LOCATION);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
        if (getModel().getTextDisplay().isActiveChoice()) {
            new TextBoxViewer(getModel().getTextDisplay().getElement()).drawChoices(gui);
        }
    }

    private void drawMainChar(GUI gui) {
        if (getModel().getMainChar() == null) return;
        gui.drawImage(getModel().getMainChar().getPosition(), getModel().getMainChar().getName());
    }
}
