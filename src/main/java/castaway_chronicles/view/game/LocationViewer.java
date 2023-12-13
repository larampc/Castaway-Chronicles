package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Scene;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationViewer extends SceneViewer<Location> {
    TextBoxViewer textBoxViewer;
    public LocationViewer() {
        this.textBoxViewer = new TextBoxViewer();
    }

    private void drawMainChar(GUI gui, MainChar mainChar) {
        if (mainChar == null) return;
        gui.drawImage(mainChar.getPosition(), mainChar.getName());
    }

    @Override
    public void draw(Scene model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawBackground(gui, model.getBackground());
        drawInteractables(gui, model.getVisibleInteractables());
        drawMainChar(gui, ((Location) model).getMainChar());
        if (((Location)model).getTextDisplay().isActiveChoice()) {
            textBoxViewer.drawChoices(gui, (Location) model);
        }
        else if (((Location)model).getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, (Location) model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
