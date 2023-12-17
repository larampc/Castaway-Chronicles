package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Map;

import java.io.IOException;
import java.net.URISyntaxException;

public class MapViewer extends SceneViewer<Map> {
    @Override
    public void draw(Map model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
    }
}
