package castaway_chronicles.controller.mainPage;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.MainPage;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    public void step(Application application, InputEvent action, long startTime) throws IOException, InterruptedException, URISyntaxException {
        current.none(startTime);
        if (action instanceof KeyEvent) {
            current.key(((KeyEvent)action).getKeyCode(), application);
        }
        if (action instanceof MouseEvent) {
            current.click(new Position(((MouseEvent)action).getX()/4, ((MouseEvent)action).getY()/4), application);
        }
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
    public ControllerState getCurrent() {
        return current;
    }

}
