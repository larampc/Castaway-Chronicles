package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.view.ScreenViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackViewer extends SceneViewer implements ScreenViewer<Backpack> {
    TextBoxViewer textBoxViewer;
    public BackpackViewer() {
        this.textBoxViewer = new TextBoxViewer();
    }
    @Override
    public void draw(Backpack model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
        if (model.getTextDisplay().isActiveChoice()) {
            textBoxViewer.drawChoices(gui, model);
        }
        else if (model.getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
