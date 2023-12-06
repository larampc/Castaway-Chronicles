package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Map;

public class MapViewer extends SceneViewer<Map> {
    public MapViewer(Map model) {
        super(model);
    }

    public void draw(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
    }
}
