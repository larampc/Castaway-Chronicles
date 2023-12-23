package castaway_chronicles.controller.game.scenes;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.Commands.GetSideOptionCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.MainPageState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class PauseController implements ControllerState {
    private final GameController gameController;
    private final PauseMenu pauseMenu;

    public PauseController(GameController gameController){
        this.gameController = gameController;
        this.pauseMenu = gameController.getModel().getPauseMenu();
    }

    @Override
    public void click(Position position, Application application){
        //does nothing
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        switch (key) {
            case KeyEvent.VK_UP:
                pauseMenu.getSelectionPanel().previousEntry();
                break;
            case KeyEvent.VK_DOWN:
                pauseMenu.getSelectionPanel().nextEntry();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                keySide();
                break;
            case KeyEvent.VK_ENTER:
                select(application);
                break;
            default:
        }
    }

    public void keySide() throws IOException, URISyntaxException, InterruptedException {
        GetSideOptionCommand getSide = new GetSideOptionCommand(pauseMenu.getSelectionPanel());
        gameController.getCommandInvoker().setCommand(getSide);
        gameController.getCommandInvoker().execute();
    }


    public void select(Application application) {
        if (pauseMenu.isSelectedMenu()) application.setState(new MainPageState(new MainPage()));
        if (pauseMenu.isSelectedExit()) application.setState(null);
        if (pauseMenu.isSelectedResume()) {
            gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
            gameController.setControllerState(gameController.getStandingController());
        }
        if (pauseMenu.isSelectedSave()) {
            gameController.getGameSaver().saveGame();
        }
    }
}
