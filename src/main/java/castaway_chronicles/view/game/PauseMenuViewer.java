package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuViewer implements PageViewer<PauseMenu>{
    @Override
    public void draw(PauseMenu model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.drawImage(new Position(0,0), "Menu");
        int i = 101;
        int x = 97;
        for (int j = 0; j < model.getNumberEntries(); j++) {
            gui.drawText(new Position(x, i), 160, model.getEntry(j), model.isSelected(j));
            i+=20;
            if (j == 1) {x+=48; i = 101;}
        }
    }
}
