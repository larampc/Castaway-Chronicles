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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class EndingPageController extends Controller<EndingPage> {
    public EndingPageController(EndingPage model) {
        super(model);
    }

    @Override
    public void step(Application application, Action action, long startTime) throws URISyntaxException, IOException {
        if (action.getType().equalsIgnoreCase("Click")) {
            Position clicked = ((ClickAction)action).getPosition();
            for (EndingItem e: getModel().getVisibleEndings()) {
                if (e.getName().equalsIgnoreCase("reset")){
                    File achievedEnd = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/achieved_endings.txt");
                    achievedEnd.delete();
                    getModel().reset();
                    break;
                }
                else if (!e.getName().contains("question") && e.contains(clicked)) {
                    application.setState(new EndState(new Ending(e.getName())));
                }
            }
        }
        if (action.getType().equalsIgnoreCase("escape")) {
            application.setState(new MenuState(new MainMenu()));
        }
    }
}
