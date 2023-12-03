package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

import static java.lang.Math.abs;

public class WalkingController implements ControllerState{
    private GameController gameController;
    private int towalk = 0;
    private long lastMovement = 0;
    public WalkingController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setTowalk(int towalk) {
        this.towalk = towalk;
    }

    public void checkwalk(long time) throws IOException, InterruptedException {
        if (towalk != 0 && time-lastMovement>60) {
            Location location = gameController.getModel().getCurrentLocation();
            CommandInvoker invoker = new CommandInvoker();
            MoveCommand move = new MoveCommand(location,(towalk < 0) ? -10 : 10);
            invoker.setCommand(move);
            invoker.execute();
            if (abs(towalk) == 1) location.getMainChar().setName("standing_" + ((towalk < 0) ? "right" : "left"));
            towalk += (towalk < 0) ? 1 : -1;
            lastMovement = time;
        }
        if(towalk == 0) gameController.setControllerState(gameController.getPrevious());
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {
        Location location = gameController.getModel().getCurrentLocation();
        towalk = (location.getMainChar().getPosition().getX() - position.getX())/10;
        location.getMainChar().setName("walk1" + ((towalk < 0) ? "_right" : "_left"));
    }

    @Override
    public void keyUp() {
        //do nothing
    }

    @Override
    public void keyDown() {
        //do nothing
    }

    @Override
    public void select(Application application) {
        //do nothing
    }

    @Override
    public void escape() {
        //do nothing
    }
}
