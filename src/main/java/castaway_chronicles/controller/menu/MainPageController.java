package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.game.ControllerStates.ControllerState;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.menu.MainPage;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainPageController extends Controller<MainPage> {
    private final ControllerState mainMenuController;
    private final ControllerState endingPageController;
    private ControllerState current;
    public MainPageController(MainPage model) {
        super(model);
        this.endingPageController = new EndingPageController(this);
        this.mainMenuController = new MainMenuController(this);
        switch (model.getCurrent()) {
            case MENU:
                current = mainMenuController;
                break;
            case ENDINGS:
                current = endingPageController;
        }
    }

    @Override
    public void step(Application application, Action action, long startTime) throws IOException, InterruptedException, URISyntaxException {
        current.none(startTime);
        if (action.getType().equalsIgnoreCase("UP")) current.keyUp();
        if (action.getType().equalsIgnoreCase("DOWN")) current.keyDown();
        if (action.getType().equalsIgnoreCase("LEFT")) current.keyLeft();
        if (action.getType().equalsIgnoreCase("RIGHT")) current.keyRight();
        if (action.getType().equalsIgnoreCase("SELECT")) current.select(application);
        if (action.getType().equalsIgnoreCase("ESCAPE")) current.escape();
        if (action.getType().equalsIgnoreCase("CLICK")) current.click(((ClickAction)action).getPosition(), application);
    }

    public ControllerState getMainMenuController() {
        return mainMenuController;
    }

    public ControllerState getEndingPageController() {
        return endingPageController;
    }

    public void setCurrent(ControllerState current) {
        this.current = current;
    }
}
