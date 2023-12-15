package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.states.EndingPageState;
import castaway_chronicles.states.GameState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {
    public MainMenuController(MainMenu model) {
        super(model);
    }
    @Override
    public void step(Application application, Action action, long time) throws IOException {
        switch (action.getType()) {
            case UP:
                getModel().previousEntry();
                if (getModel().isSelectedContinue() && !getModel().canContinue()) getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                if (getModel().isSelectedContinue() && !getModel().canContinue()) getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) application.setState(null);
                if (getModel().isSelectedStart())
                    application.setState(new GameState(new GameBuilder().createGame(false)));
                if (getModel().isSelectedContinue() && getModel().canContinue())
                    application.setState(new GameState(new GameBuilder().createGame(true)));
                if (getModel().isSelectedEndings()) application.setState(new EndingPageState(new EndingPage()));
                break;
            case RIGHT:
            case LEFT:
                if (!getModel().getEntry(
                        getModel().getCurrentEntry() + 2
                ).isEmpty()) {
                    getModel().nextEntry();
                    getModel().nextEntry();
                } else if (!getModel().getEntry(
                        getModel().getCurrentEntry() - 2
                ).isEmpty()) {
                    getModel().previousEntry();
                    getModel().previousEntry();
                }
                if (getModel().isSelectedContinue() && !getModel().canContinue()) {
                    getModel().previousEntry();
                    getModel().previousEntry();
                }
                break;
            default:
        }
    }
}
