package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.io.IOException;

public class NarratorController implements ControllerState {

    private final GameController gameController;
    public NarratorController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException {

    }

    @Override
    public void keyUp() {

    }

    @Override
    public void keyDown() {

    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void select(Application application) throws IOException, InterruptedException {
        gameController.getModel().getCurrentLocation().getTextDisplay().closeTextBox();
        gameController.setControllerState(gameController.getLocationController());
    }

    @Override
    public void escape() {

    }

    @Override
    public void none(long time) throws IOException, InterruptedException {

    }
}
