package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.SceneViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameSceneViewer extends SceneViewer<Scene> {
    @Override
    public void draw(Scene model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        drawElement(gui, model.getBackground());
        drawElements(gui, model.getVisibleInteractables());
        if (model instanceof Location) {
            MainChar mainChar = ((Location)model).getMainChar();
            if (mainChar != null) drawElement(gui, mainChar);
        }
    }
}
