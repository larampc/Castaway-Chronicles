package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.game.Game;

import java.io.IOException;

public class GameController extends Controller<Game> {
    public GameController(Game model) {
        super(model);
    }

    @Override
    public void step(Application application, Action action) throws IOException {
        switch (getModel().getScene()) {
            case BACKPACK:
                new BackpackController(getModel().getBackpack()).step(application, action);
                break;
            case MAP:
                new MapController(getModel().getMap()).step(application, action);
                break;
            case LOCATION:
                new LocationController(getModel()).step(application, action);
                break;
        }
    }
}
