package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.view.ScreenViewer;
import castaway_chronicles.view.SelectionPanelViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackViewer extends SceneViewer implements ScreenViewer<Backpack> {
    TextBoxViewer textBoxViewer;
    SelectionPanelViewer selectionPanelViewer;
    public BackpackViewer() {
        this.textBoxViewer = new TextBoxViewer();
        this.selectionPanelViewer = new SelectionPanelViewer(new Position(50, 157), 75, 10);
    }
    @Override
    public void draw(Backpack model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
        if (model.getTextDisplay().isActiveChoice()) {
            textBoxViewer.drawChoices(gui, model, selectionPanelViewer);
        }
        else if (model.getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, model);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
    public void setTextBoxViewer(TextBoxViewer textBoxViewer) {
        this.textBoxViewer = textBoxViewer;
    }
    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
}
