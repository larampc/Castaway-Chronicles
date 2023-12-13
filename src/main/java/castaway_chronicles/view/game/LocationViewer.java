package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.ScreenViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationViewer extends SceneViewer implements ScreenViewer<Location> {
    TextBoxViewer textBoxViewer;
    public LocationViewer() {
        this.textBoxViewer = new TextBoxViewer();
    }

    @Override
    public void draw(Location model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
        if (model.getMainChar() != null) drawElement(gui, model.getMainChar());
        if (model.getTextDisplay().isActiveChoice()) {
            textBoxViewer.drawChoices(gui, model);
        }
        else if (model.getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
