package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;

public class HandController implements ControllerState {
    private final GameController gameController;
    private String toGive = "";
    public HandController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setToGive(String toGive) {
        this.toGive = toGive;
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {
        if (toGive.isEmpty()) {
            gameController.getModel().getCurrentLocation().getBackpackAnswer().activate(gameController.getModel().getBackpack().getBackpackSelection().getItem());
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getNarratorController());
        }
        else {
            for (Interactable e: gameController.getModel().getCurrentLocation().getVisibleInteractables()) {
                if (e instanceof NPC && e.contains(position) && e.getName().equalsIgnoreCase(toGive)) {
                    CommandInvoker invoker = (CommandInvoker) gameController.getCommandInvoker();
                    Command effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getBackpack().getBackpackSelection().getItem().getEffects());
                    invoker.setCommand(effects);
                    invoker.execute();
                    gameController.getModel().setCurrentScene("LOCATION");
                    gameController.getModel().getCurrentLocation().setDialog(e.getName());
                    gameController.setControllerState(gameController.getDialogController());
                    return;
                }
            }
            gameController.getModel().getCurrentLocation().getBackpackAnswer().activate(gameController.getModel().getBackpack().getBackpackSelection().getItem());
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getNarratorController());
        }
    }

    @Override
    public void keyUp() {
        //does nothing
    }

    @Override
    public void keyDown() {
        //does nothing
    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        gameController.setControllerState(gameController.getBackpackController());
    }

    @Override
    public void none(long time) {

    }
}
