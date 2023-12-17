package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.menu.MainPage;
import castaway_chronicles.states.MainPageState;

import java.io.IOException;

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
    public void keyUp() {
        pauseMenu.getSelectionPanel().previousEntry();
    }

    @Override
    public void keyDown(){
        pauseMenu.getSelectionPanel().nextEntry();
    }

    @Override
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

    @Override
    public void keyLeft() {
        keyRight();
    }

    @Override
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
    public void escape() {
        //does nothing
    }

    @Override
    public void none(long time) {

    }
    public void setGameSaver(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }
}
