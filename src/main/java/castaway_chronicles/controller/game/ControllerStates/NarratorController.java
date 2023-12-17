package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class NarratorController implements ControllerState {

    private final GameController gameController;
    public NarratorController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException {

    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        if (key == KeyEvent.VK_ENTER) {
            gameController.getModel().getCurrentLocation().getTextDisplay().closeTextBox();
            gameController.setControllerState(gameController.getLocationController());
        }
    }

    @Override
    public void none(long time) throws IOException, InterruptedException {

    }
}
