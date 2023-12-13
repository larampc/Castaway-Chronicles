package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.view.SelectionPanelViewer;
import castaway_chronicles.view.ScreenViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer implements ScreenViewer<PauseMenu> {
    private final SelectionPanelViewer selectionPanelViewer;

    public PauseMenuViewer() {
        selectionPanelViewer = new SelectionPanelViewer(new Position(97,101), 48,20);
    }
    @Override
    public void draw(PauseMenu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.drawImage(new Position(0,0), "Menu");
        selectionPanelViewer.draw(model, gui);
    }
}
