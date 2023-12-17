package castaway_chronicles.view.mainPage;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.mainPage.EndingPage;
import castaway_chronicles.view.SceneViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class EndingPageViewer extends SceneViewer<EndingPage> {
    @Override
    public void draw(EndingPage model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
    }
}
