package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.*;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class LocationController implements ControllerState{
    private GameController gameController;

    public LocationController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position) throws IOException {
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
                    ChangeSceneCommand changeScene = new ChangeSceneCommand(gameController.getModel(), split[0]);
                    invoker.setCommand(changeScene);
                    if (split[0].equalsIgnoreCase("Backpack")) gameController.setControllerState(gameController.getBackpackController());
                    if (split[0].equalsIgnoreCase("Map")) gameController.setControllerState(gameController.getMapController());
                }
                invoker.execute();
                break;
            }
        }
        MoveCommand move = new MoveCommand(location, position);
        invoker.setCommand(move);
        invoker.execute();
        invoker.setCommand(null);
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
