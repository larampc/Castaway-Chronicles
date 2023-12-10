package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer {
    private PauseMenu pauseMenu;
    public PauseMenuViewer(PauseMenu model) {
        this.pauseMenu = model;
    }

    protected void draw(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), "PauseMenu");
        int i = 101;
        for (int j = 0; j < pauseMenu.getNumberEntries(); j++) {
            gui.drawText(new Position(100, i), 160, pauseMenu.getEntry(j), 0, pauseMenu.isSelected(j));
            i+=20;
        }
    }
}
