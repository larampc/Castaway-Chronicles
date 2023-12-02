package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.game.scene.SceneLoader;
import castaway_chronicles.model.menu.Menu;
import castaway_chronicles.states.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu model) {
        super(model);
    }
    @Override
    public void step(Application application, Action action) throws IOException {
        switch (action.getType().charAt(0)) {
            case 'U':
                getModel().previousEntry();
                break;
            case 'D':
                getModel().nextEntry();
                break;
            case 'S':
                if (getModel().isSelectedExit()) application.setState(null);
                if (getModel().isSelectedStart()) application.setState(new GameState(new SceneLoader("Beach", "Location").createScene()));
                break;
        }
    }
}
