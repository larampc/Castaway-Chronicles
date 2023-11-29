package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Map;

public class MapViewer extends SceneViewer<Map> {
    public MapViewer(Map model) {
        super(model);
    }
    @Override
    public void drawElements(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
    }
}
