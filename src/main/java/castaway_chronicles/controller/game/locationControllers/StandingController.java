package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.*;
import castaway_chronicles.controller.ContinuousControllerState;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Icon;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.Item;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.Location;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class StandingController implements ContinuousControllerState {

    private final GameController gameController;
    private Command lastCommand = null;

    public StandingController(GameController gameController) {
        this.gameController = gameController;
    }
    public void setLastCommandNull() {lastCommand = null;}

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {
        Location location = gameController.getModel().getCurrentLocation();
        CommandInvoker invoker = new CommandInvoker();
        for (Interactable e: location.getVisibleInteractables()) {
            if (e.contains(position)) {
                if (e instanceof Item) {
                    lastCommand = new PickUpCommand(gameController.getModel(), e.getName());
                }
                else if (e instanceof NPC) {
                    lastCommand = new StartTalkCommand(gameController.getModel(), e.getName());
                }
                else if (e instanceof Icon) {
                    String[] split = e.getName().split("_", -1);
                    if (split[0].equalsIgnoreCase("Backpack")) {
                        gameController.getModel().setCurrentScene(Game.SCENE.BACKPACK);
                        gameController.setControllerState(gameController.getBackpackController());
                    }
                    else if (split[0].equalsIgnoreCase("Map")) {
                        gameController.getModel().setCurrentScene(Game.SCENE.MAP);
                        gameController.setControllerState(gameController.getMapController());
                    }
                    else {
                        lastCommand = new ChangeLocationCommand(gameController.getModel(), split[0]);
                    }
                invoker.execute();
                break;
                }
            }
        }
        invoker.execute();
        location = gameController.getModel().getCurrentLocation();
        if (location.getMainChar() != null && gameController.getCurrent() instanceof StandingController) {
            if (((WalkingController) gameController.getWalkingController()).setToWalk(position)) {
                location.getMainChar().setName("walk1" + ((location.getMainChar().getPosition().getX() - position.getX() < 0) ? "_right" : "_left"));
            }
            gameController.setControllerState(gameController.getWalkingController());
        }
    }

    @Override
    public void key(int key, Application application) {
        if (key == KeyEvent.VK_ESCAPE) {
            gameController.getModel().setCurrentScene(Game.SCENE.PAUSE);
            gameController.setControllerState(gameController.getLocationController());
            gameController.setControllerState(gameController.getPauseController());
        }
    }


    @Override
    public void none(long time) throws IOException, InterruptedException, URISyntaxException {
        if (lastCommand != null) {
            CommandInvoker invoker = new CommandInvoker();
            invoker.setCommand(lastCommand);
            invoker.execute();
            if (lastCommand instanceof StartTalkCommand) {
                gameController.setControllerState(gameController.getDialogController());
            }
            lastCommand = null;
        }
    }
}
