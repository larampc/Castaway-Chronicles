package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.view.Images;

public class MapViewer extends SceneViewer<Map> {
    protected MapViewer(Map model, Images images) {
        super(model, images);
    }
    @Override
    protected void drawElements(GUI gui) {
        drawBackground(gui);
        drawInteractables(gui);
    }
}
