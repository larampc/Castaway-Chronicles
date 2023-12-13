package castaway_chronicles.view.menu;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.MenuViewer;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuViewer extends Viewer<MainMenu> {
    private final MenuViewer menuViewer;

    public MainMenuViewer(MainMenu model) {
        super(model);
        menuViewer = new MenuViewer(new Position(98,101), 42,20, "Menu");
    }

    @Override
    public void drawScreen(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        menuViewer.draw(getModel(), gui);
    }
}
