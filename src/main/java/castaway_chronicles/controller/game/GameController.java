package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.game.Game;

public class GameController extends Controller<Game> {
    public GameController(Game model) {
        super(model);
    }

    @Override
    public void step(Application application, Action action)  {
        System.out.println("hii");
    }
}
