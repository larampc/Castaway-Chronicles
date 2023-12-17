package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.MainPageState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class PauseController implements ControllerState {
    private final GameController gameController;
    private final PauseMenu pauseMenu;
    private GameSaver gameSaver;

    public PauseController(GameController gameController){
        this.gameController = gameController;
        this.pauseMenu = gameController.getModel().getPauseMenu();
        this.gameSaver = gameController.getGameSaver();
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
                keyRight();
                break;
            case KeyEvent.VK_ENTER:
                select(application);
                break;
            default:
        }
    }

    public void keyRight() {
        if (!pauseMenu.getSelectionPanel().getEntry(
                pauseMenu.getSelectionPanel().getCurrentEntry() + 2
        ).isEmpty()) {
            pauseMenu.getSelectionPanel().nextEntry();
            pauseMenu.getSelectionPanel().nextEntry();
        } else if (!pauseMenu.getSelectionPanel().getEntry(
                pauseMenu.getSelectionPanel().getCurrentEntry() - 2
        ).isEmpty()) {
            pauseMenu.getSelectionPanel().previousEntry();
            pauseMenu.getSelectionPanel().previousEntry();
        }
    }


    public void select(Application application) throws IOException {
        if (pauseMenu.isSelectedMenu()) application.setState(new MainPageState(new MainPage()));
        if (pauseMenu.isSelectedExit()) application.setState(null);
        if (pauseMenu.isSelectedResume()) {
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getLocationController());
        }
        if (pauseMenu.isSelectedSave()) {
            gameSaver.saveGame();
        }
    }


    @Override
    public void none(long time) {

    }
    public void setGameSaver(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }
}
