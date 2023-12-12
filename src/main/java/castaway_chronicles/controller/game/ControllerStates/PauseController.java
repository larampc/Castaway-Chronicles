package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.states.MenuState;

import java.io.IOException;

public class PauseController implements ControllerState {
    private final GameController gameController;
    private final PauseMenu pauseMenu;
    private final GameSaver gameSaver;

    public PauseController(GameController gameController, GameSaver gameSaver){
        this.gameController = gameController;
        this.pauseMenu = gameController.getModel().getPauseMenu();
        this.gameSaver = gameSaver;
    }

    @Override
    public void click(Position position, Application application){
        //does nothing
    }

    @Override
    public void keyUp() {
        pauseMenu.previousEntry();
    }

    @Override
    public void keyDown(){
        pauseMenu.nextEntry();
    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void select(Application application) throws IOException {
        if (pauseMenu.isSelectedMenu()) application.setState(new MenuState(new MainMenu()));
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
}
