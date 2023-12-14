package castaway_chronicles.view.menu;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.SelectionPanelViewer;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuViewer extends Viewer<MainMenu> {
    private SelectionPanelViewer selectionPanelViewer;

    public MainMenuViewer(MainMenu model) {
        super(model);
        selectionPanelViewer = new SelectionPanelViewer(new Position(98,101), 42,20);
    }

    @Override
    public void drawScreen(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), "Menu");
        selectionPanelViewer.draw(getModel(), gui);
    }

    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
}
