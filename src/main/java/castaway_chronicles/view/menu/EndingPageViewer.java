package castaway_chronicles.view.menu;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Element;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.view.Viewer;

public class EndingPageViewer extends Viewer<EndingPage> {
    public EndingPageViewer(EndingPage model) {
        super(model);
    }

    @Override
    public void drawScreen(GUI gui) {
        gui.drawImage(new Position(0,0), "EndingsMenu");
        for (Element e: getModel().getVisibleEndings()) {
            gui.drawImage(e.getPosition(), e.getName());
        }
    }
}
