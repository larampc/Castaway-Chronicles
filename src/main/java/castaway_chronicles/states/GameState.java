package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.game.GameViewer;

public class GameState extends State<Game>{
    public GameState(Game model) {super(model);}
    @Override
    protected Viewer<Game> getViewer() {return new GameViewer(getModel());}
    @Override
    protected Controller<Game> getController() {return new GameController(getModel());}
}
