package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.Position;

import java.io.IOException;

public class EndViewer extends Viewer<Ending> {
    public EndViewer(Ending model) {
        super(model);
    }

    @Override
    public void drawScreen(GUI gui) throws IOException {
        if (!((LanternaGUI)gui).imageIsLoaded(getModel().getCurrentFrame())) ((LanternaGUI)gui).loadEnding(getModel().getName());
        if (gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(0,0), getModel().getCurrentFrame());
    }
}
