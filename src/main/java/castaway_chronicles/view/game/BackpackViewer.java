package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Scene;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackViewer extends SceneViewer<Backpack> {
    TextBoxViewer textBoxViewer;
    public BackpackViewer() {
        this.textBoxViewer = new TextBoxViewer();
    }
    @Override
    public void draw(Scene model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawBackground(gui, model.getBackground());
        drawInteractables(gui, model.getVisibleInteractables());
        if (((Backpack) model).getBackpackItemInfo().isActiveTextBox() && !((Backpack) model).getBackpackItemInfo().isActiveChoice()) {
            textBoxViewer.drawTextBox(gui, (Backpack) model);
        }
        else if (((Backpack) model).getBackpackItemInfo().isActiveChoice()) {
            textBoxViewer.drawChoices(gui, (Backpack) model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
}
