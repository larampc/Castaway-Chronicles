package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.Viewer;

public class GameViewer extends Viewer<Game> {
    public GameViewer(Game model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        switch (getModel().getScene()) {
            case BACKPACK:
                new BackpackViewer(getModel().getBackpack()).draw(gui);
                break;
            case MAP:
                new MapViewer(getModel().getMap()).draw(gui);
                break;
            case LOCATION:
                new LocationViewer(getModel().getLocation(getModel().getCurrentLocation())).draw(gui);
        }
    }
}
