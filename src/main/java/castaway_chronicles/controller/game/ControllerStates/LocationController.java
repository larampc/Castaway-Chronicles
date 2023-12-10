package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.*;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.net.URISyntaxException;

public class LocationController implements ControllerState {

    private final GameController gameController;
    private Command lastCommand = null;

    public LocationController(GameController gameController) {
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
                    lastCommand = new StartTalkCommand(location, e.getName());
                }
                else if (e instanceof Icon) {
                    String[] split = e.getName().split("_", -1);
                    if (split[0].equalsIgnoreCase("Backpack")) {
                        ChangeSceneCommand changeScene2 = new ChangeSceneCommand(gameController.getModel(), "BACKPACK");
                        invoker.setCommand(changeScene2);
                        gameController.setControllerState(gameController.getBackpackController());
                    }
                    else if (split[0].equalsIgnoreCase("Map")) {
                        ChangeSceneCommand changeScene2 = new ChangeSceneCommand(gameController.getModel(), "MAP");
                        invoker.setCommand(changeScene2);
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
        if (location.getMainChar() != null && gameController.getCurrent() instanceof LocationController) {
            if (((WalkingController) gameController.getWalkingController()).setTowalk(position)) {
                location.getMainChar().setName("walk1" + ((location.getMainChar().getPosition().getX() - position.getX() < 0) ? "_right" : "_left"));
            }
            gameController.setControllerState(gameController.getWalkingController());
        }
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
    public void select(Application application) {

    }

    @Override
    public void escape() {
        gameController.getModel().setCurrentScene("PAUSE");
        gameController.setControllerState(gameController.getLocationController());
        gameController.setControllerState(gameController.getPauseController());
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
