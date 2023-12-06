package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Ending;

import java.io.IOException;
import java.net.URISyntaxException;


public class EndViewer {
    private Ending end;
    protected EndViewer(GUI gui, Ending end) throws URISyntaxException, IOException {
        this.end = end;
        ((LanternaGUI)gui).loadEnding(end.getName());
    }

    protected void draw(GUI gui) throws IOException {
        if (gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(0,0), end.getCurrentFrame());
    }
}
