package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.EndingItem;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.states.EndState;
import castaway_chronicles.states.MenuState;

import java.net.URISyntaxException;

public class EndingPageController extends Controller<EndingPage> {
    public EndingPageController(EndingPage model) {
        super(model);
    }

    @Override
    public void step(Application application, Action action, long startTime) throws URISyntaxException {
        if (action.getType().equalsIgnoreCase("Click")) {
            Position clicked = ((ClickAction)action).getPosition();
            for (EndingItem e: getModel().getVisibleEndings()) {
                if (!e.getName().contains("question") && e.contains(clicked)) {
                    application.setState(new EndState(new Ending(e.getName())));
                }
            }
        }
        if (action.getType().equalsIgnoreCase("escape")) {
            application.setState(new MenuState(new MainMenu()));
        }
    }
}
