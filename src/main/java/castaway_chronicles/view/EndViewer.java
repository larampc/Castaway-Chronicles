package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public class EndViewer extends Viewer<Ending> {
    public EndViewer(Ending model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) throws IOException, URISyntaxException {
        if (!((LanternaGUI)gui).imageIsLoad(getModel().getCurrentFrame())) ((LanternaGUI)gui).loadEnding(getModel().getName());
        if (gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(0,0), getModel().getCurrentFrame());
    }
}
