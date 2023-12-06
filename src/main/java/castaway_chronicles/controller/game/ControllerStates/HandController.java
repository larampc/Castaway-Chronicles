package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

public class HandController implements ControllerState {
    private GameController gameController;
    public HandController(GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void click(Position position) {

    }

    @Override
    public void keyUp() {
        //does nothing
    }

    @Override
    public void keyDown() {
        //does nothing
    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        gameController.setControllerState(gameController.getBackpackController());
    }

    @Override
    public void none(long time) {

    }
}
