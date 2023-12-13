package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.view.MenuViewer;
import castaway_chronicles.view.ScreenViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer implements ScreenViewer<PauseMenu> {
    private final MenuViewer menuViewer;

    public PauseMenuViewer() {
        menuViewer = new MenuViewer(new Position(97,101), 48,20, "Menu");
    }
    @Override
    public void draw(PauseMenu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        menuViewer.draw(model, gui);
    }
}
