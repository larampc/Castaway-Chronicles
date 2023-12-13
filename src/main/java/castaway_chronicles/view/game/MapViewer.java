package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.Scene;

import java.io.IOException;
import java.net.URISyntaxException;

public class MapViewer extends SceneViewer<Map> {
    @Override
    public void draw(Scene model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawBackground(gui, model.getBackground());
        drawInteractables(gui, model.getVisibleInteractables());
    }
}
