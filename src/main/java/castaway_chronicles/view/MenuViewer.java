package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Menu;
import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewer extends ScreenViewer<Menu>{

    private SelectionPanelViewer selectionPanelViewer;

    public MenuViewer() {
        selectionPanelViewer = new SelectionPanelViewer(new Position(98,101), 42,20);
    }

    @Override
    public void draw(Menu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        selectionPanelViewer.draw(model.getSelectionPanel(), gui);
    }
    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
    public SelectionPanelViewer getSelectionPanelViewer() {
        return selectionPanelViewer;
    }
}
