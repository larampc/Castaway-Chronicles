package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.scene.Backpack;

import java.io.IOException;
import java.net.URISyntaxException;


public class BackpackController implements ControllerState {
    private Backpack backpack;
    private GameController gameController;
    public BackpackController(GameController gameController) {
        this.gameController = gameController;
        backpack = gameController.getModel().getBackpack();
    }

    @Override
    public void click(Position position) throws IOException {
        for (Interactable e: backpack.getVisibleInteractables()) {
            if (e.contains(position)) {
                backpack.getBackpackSelection().activateDescription((ItemBackpack) e);
            }
        }
    }

    @Override
    public void keyUp() {
        if (backpack.getBackpackSelection().isSelection()) {
            backpack.getBackpackSelection().getItem().getItemOptions().previousEntry();
        }
    }

    @Override
    public void keyDown() {
        if (backpack.getBackpackSelection().isSelection()) {
            backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
        }
    }

    @Override
    public void keyLeft() {
        if (!backpack.getBackpackSelection().getItem().getItemOptions().getEntry(
                backpack.getBackpackSelection().getItem().getItemOptions().getCurrentEntry() + 2
        ).isEmpty()) {
            backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
            backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
        } else if (!backpack.getBackpackSelection().getItem().getItemOptions().getEntry(
                backpack.getBackpackSelection().getItem().getItemOptions().getCurrentEntry() - 2
        ).isEmpty()) {
            backpack.getBackpackSelection().getItem().getItemOptions().previousEntry();
            backpack.getBackpackSelection().getItem().getItemOptions().previousEntry();
        }
    }

    @Override
    public void keyRight() {
        keyLeft(); //keyLeft and keyRight behave identically, given at most 4 options
    }

    @Override
    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (backpack.getBackpackSelection().isDescription()) {
            backpack.getBackpackSelection().activateSelection();
            return;
        }
        if (!backpack.getBackpackSelection().isSelection()) return;

        String command = backpack.getBackpackSelection().getItem().getCommand();
        backpack.getBackpackSelection().deactivate();
        String[] s = command.split(" ", -1);
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene("LOCATION");
                ((HandController)gameController.getHandController()).setToGive("");
                gameController.setControllerState(gameController.getHandController());
            }
            else {
                gameController.getModel().getCurrentLocation().getBackpackAnswer().activate(backpack.getBackpackSelection().getItem());
                gameController.getModel().setCurrentScene("LOCATION");
                gameController.setControllerState(gameController.getNarratorController());
            }
        }
        else {
            CommandInvoker invoker = new CommandInvoker();
            if (s[0].equalsIgnoreCase("use")) {
                Command effects = new HandleEffectsCommand(gameController.getModel(), backpack.getBackpackSelection().getItem().getEffects());
                invoker.setCommand(effects);
                invoker.execute();
                if (gameController.getModel().getScene()!= Game.SCENE.END) {
                    gameController.getModel().setCurrentScene("LOCATION");
                    gameController.setControllerState(gameController.getLocationController());
                }
            }
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene("LOCATION");
                ((HandController)gameController.getHandController()).setToGive(s[1]);
                gameController.setControllerState(gameController.getHandController());
            }
        }
    }

    @Override
    public void escape() {
        if (!(backpack.getBackpackSelection().isSelection()|| backpack.getBackpackSelection().isDescription())) {
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getLocationController());
        }
        else {
            backpack.getBackpackSelection().deactivate();
        }
    }

    @Override
    public void none(long time) {

    }
}
