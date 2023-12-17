package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.view.SceneViewer;
import castaway_chronicles.view.SelectionPanelViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer extends SceneViewer<PauseMenu> {
    private SelectionPanelViewer selectionPanelViewer;

    public PauseMenuViewer() {
        selectionPanelViewer = new SelectionPanelViewer(new Position(97,101), 48,20);
    }
    @Override
    public void draw(PauseMenu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        selectionPanelViewer.draw(model.getSelectionPanel(), gui);
    }
    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
}
