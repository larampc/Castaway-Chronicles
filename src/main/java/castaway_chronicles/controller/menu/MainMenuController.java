package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.states.GameState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {
    public MainMenuController(MainMenu model) {
        super(model);
    }
    @Override
    public void step(Application application, Action action, long time) throws IOException {
        switch (action.getType().charAt(0)) {
            case 'U':
                getModel().previousEntry();
                break;
            case 'D':
                getModel().nextEntry();
                break;
            case 'S':
                if (getModel().isSelectedExit()) application.setState(null);
                if (getModel().isSelectedStart()) application.setState(new GameState(new GameBuilder().createGame()));
                break;
        }
    }
}
