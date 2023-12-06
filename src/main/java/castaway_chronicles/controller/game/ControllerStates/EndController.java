package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.io.IOException;

public class EndController implements ControllerState{
    private GameController gameController;
    private String ending;
    public EndController(GameController gameController) {
        this.gameController = gameController;
    }
    public void setEnding(String ending) {
        this.ending = ending;
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {

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

    }

    @Override
    public void escape() {

    }

    @Override
    public void none(long time) throws IOException, InterruptedException {

    }
}
