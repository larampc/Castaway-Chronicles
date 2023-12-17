package castaway_chronicles.view.mainPage;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.view.SelectionPanelViewer;
import castaway_chronicles.view.SceneViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuViewer extends SceneViewer<MainMenu> {
    private SelectionPanelViewer selectionPanelViewer;

    public MainMenuViewer() {
        selectionPanelViewer = new SelectionPanelViewer(new Position(98,101), 42,20);
    }

    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }

    @Override
    public void draw(MainMenu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        selectionPanelViewer.draw(model.getSelectionPanel(), gui);
    }
}
