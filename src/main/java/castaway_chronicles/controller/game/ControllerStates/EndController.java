package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.states.MenuState;

import java.io.IOException;

public class EndController implements ControllerState{
    private GameController gameController;
    private long lastFrame = 0;
    public EndController(GameController gameController) {
        this.gameController = gameController;
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
        application.setState(new MenuState(new MainMenu()));
    }

    @Override
    public void escape() {

    }

    @Override
    public void none(long time) throws IOException, InterruptedException {
        if (time-lastFrame > 200 && gameController.getModel().getEnd().getMax() > gameController.getModel().getEnd().getCurrent()) {
            gameController.getModel().getEnd().setNext();
            lastFrame = time;
        }
    }
}
