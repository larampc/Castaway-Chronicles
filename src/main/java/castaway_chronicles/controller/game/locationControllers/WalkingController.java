package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ContinuousControllerState;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Math.abs;

public class WalkingController implements ContinuousControllerState {
    private final GameController gameController;
    private int toWalk = 0;
    private long lastMovementTime = 0;
    private boolean goRight = false;
    private static final int dx = 10;
    public WalkingController(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean setTowalk(Position walkto) {
        Location location = gameController.getModel().getCurrentLocation();
        int background_x = location.getBackground().getPosition().getX();
        int character_x = location.getMainChar().getPosition().getX();
        int max_background_x = 200 - location.getBackground().getWidth();
        int offset = (character_x - walkto.getX()) + location.getMainChar().getWidth()/2;
        int next_x = background_x + location.getMainChar().getWidth()/2 + offset;
        goRight = (offset < 0);
        if (abs(offset) < 2*dx || (!location.getBackground().isLoopable() && ((background_x == 0 && !goRight) || (background_x-dx <= max_background_x && goRight)))) {
            toWalk = 0;
            return false;
        }
        if (location.getBackground().isLoopable()) {toWalk = offset/dx; return true;}
        if (next_x <= 0 && next_x >= max_background_x) {
            toWalk = offset/dx;
            toWalk += (toWalk < 0) ? 1 : -1;
        }
        else {
            if (goRight) offset = (max_background_x+dx) - background_x;
            else offset = background_x + location.getMainChar().getWidth()/2;
            toWalk = offset/dx;
            toWalk += (toWalk < 0) ? -1 : 1;
        }
        return true;
    }

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException {
        setTowalk(position);
        ((StandingController)gameController.getLocationController()).setLastCommandNull();
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {

    }

    @Override
    public void none(long time) throws IOException, InterruptedException, URISyntaxException {
        if (toWalk == 0) {
            gameController.getModel().getCurrentLocation().getMainChar().setName("standing_" + (goRight ? "right" : "left"));
            gameController.setControllerState(gameController.getPrevious());
            return;
        }
        if (time - lastMovementTime > 150) {
            Location location = gameController.getModel().getCurrentLocation();
            CommandInvoker invoker = (CommandInvoker) gameController.getCommandInvoker();
            MoveCommand move = new MoveCommand(location,goRight ? -dx : dx);
            invoker.setCommand(move);
            invoker.execute();
            toWalk += (toWalk < 0) ? 1 : -1;
            lastMovementTime = time;
        }
    }

    public int getToWalk() {
        return toWalk;
    }

    public boolean isFacingRight() {return goRight;}
}
