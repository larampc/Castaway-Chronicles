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
    public void drawElements(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), "Menu");
        int y = 101;
        int x = 98;
        for (int j = 0; j < getModel().getNumberEntries(); j++) {
            gui.drawText(new Position(x, y), 160, getModel().getEntry(j), getModel().isSelected(j));
            y+=20;
            if (j == 1) {x+=42; y = 101;}
        }
    }
}
