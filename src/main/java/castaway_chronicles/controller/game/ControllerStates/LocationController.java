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

public class LocationController implements ControllerState {

    private GameController gameController;

    public LocationController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {
        Location location = gameController.getModel().getCurrentLocation();
        CommandInvoker invoker = new CommandInvoker();

        for (Interactable e: location.getVisibleInteractables()) {
            if (e.contains(position)) {
                if (e instanceof Item) {
                    PickUpCommand pickup = new PickUpCommand(gameController.getModel(), e.getName());
                    invoker.setCommand(pickup);
                }
                else if (e instanceof NPC) {
                    StartTalkCommand talk = new StartTalkCommand(location, e.getName());
                    invoker.setCommand(talk);
                    gameController.setControllerState(gameController.getDialogController());
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
                        ChangeLocationCommand changeLocation = new ChangeLocationCommand(gameController.getModel(), split[0]);
                        invoker.setCommand(changeLocation);
                    }
                invoker.execute();
                break;
                }
            }
        }
        invoker.execute();
        location = gameController.getModel().getCurrentLocation();
        if (location.getMainChar() != null) {
            int towalk = (location.getMainChar().getPosition().getX() - position.getX())/15;
            if (location.getBackground().getPosition().getX() + towalk <= 0 && location.getBackground().getPosition().getX() + towalk >= -location.getBackground().getWidth()+200) {
                location.getMainChar().setName("walk1" + ((towalk < 0) ? "_right" : "_left"));
                ((WalkingController)gameController.getWalkingController()).setTowalk((towalk < 0) ? towalk+=2: towalk);
                gameController.setControllerState(gameController.getWalkingController());
            }
        }
    }

    @Override
    public void keyUp() {

    }

    @Override
    public void keyDown() {

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
}
