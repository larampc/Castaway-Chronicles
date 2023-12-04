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
    private int toWalk = 0;
    private long lastMovementTime = 0;
    private boolean goRight = false;
    public WalkingController(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean setTowalk(Position walkto) {
        Location location = gameController.getModel().getCurrentLocation();
        int offset = (location.getMainChar().getPosition().getX() - walkto.getX()) + location.getMainChar().getWidth()/2;
        int next_x = location.getBackground().getPosition().getX()+location.getMainChar().getWidth()/2+offset;
        goRight = (offset < 0);
        if (next_x <= 0 && 200-location.getBackground().getWidth() <= next_x && abs(offset)>20) {
            toWalk = offset/10;
            toWalk += (toWalk < 0) ? 1 : -1;
            return true;
        }
        return false;
    }

    public boolean canwalk() {
        if (toWalk == 0) {
            gameController.setControllerState(gameController.getPrevious());
            gameController.getModel().getCurrentLocation().getMainChar().setName("standing_" + ((goRight) ? "right" : "left"));
            return false;
        }
        return true;
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {
        setTowalk(position);
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

    @Override
    public void none(long time) throws IOException, InterruptedException {
        if (canwalk() && time- lastMovementTime >150) {
            Location location = gameController.getModel().getCurrentLocation();
            CommandInvoker invoker = new CommandInvoker();
            MoveCommand move = new MoveCommand(location,(goRight) ? -10 : 10);
            invoker.setCommand(move);
            invoker.execute();
            toWalk += (toWalk < 0) ? 1 : -1;
            lastMovementTime = time;
        }
    }
}
