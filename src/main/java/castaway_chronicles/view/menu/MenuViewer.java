package castaway_chronicles.view.menu;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.Viewer;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewer extends Viewer<MainMenu> {

    public MenuViewer(MainMenu model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), "Menu");
        int i = 101;
        for (int j = 0; j < getModel().getNumberEntries(); j++) {
            gui.drawText(new Position(102, i), 160, getModel().getEntry(j), 0, getModel().isSelected(j));
            i+=20;
        }
    }
}
