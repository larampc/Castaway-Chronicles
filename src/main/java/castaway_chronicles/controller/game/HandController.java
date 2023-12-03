package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.model.Position;

public class HandController implements ControllerState{
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
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        gameController.setControllerState(gameController.getBackpackController());
    }
}
