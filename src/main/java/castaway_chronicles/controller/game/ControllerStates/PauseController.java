package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.PauseMenu;

public class PauseController implements ControllerState {
    private final GameController gameController;
    private final PauseMenu pauseMenu;

    public PauseController(GameController gameController){
        this.gameController = gameController;
        this.pauseMenu = gameController.getModel().getPauseMenu();
    }

    @Override
    public void click(Position position){
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
    public void select(Application application) {
        if (pauseMenu.isSelectedExit()) application.setState(null);
        if (pauseMenu.isSelectedResume()) {
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getLocationController());
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
