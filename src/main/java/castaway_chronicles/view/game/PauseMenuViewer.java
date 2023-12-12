package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer {
    private final PauseMenu pauseMenu;
    public PauseMenuViewer(PauseMenu model) {
        this.pauseMenu = model;
    }

    protected void draw(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), "Menu");
        int i = 101;
        int x = 97;
        for (int j = 0; j < pauseMenu.getNumberEntries(); j++) {
            gui.drawText(new Position(x, i), 160, pauseMenu.getEntry(j), pauseMenu.isSelected(j));
            i+=20;
            if (j == 1) {x+=48; i = 101;}
        }
    }
}
