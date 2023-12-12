package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.scene.Backpack;

import java.io.IOException;
import java.net.URISyntaxException;


public class BackpackController implements ControllerState {
    private final Backpack backpack;
    private final GameController gameController;
    public BackpackController(GameController gameController) {
        this.gameController = gameController;
        backpack = gameController.getModel().getBackpack();
    }

    @Override
    public void click(Position position, Application application) throws IOException {
        for (Interactable e: backpack.getVisibleInteractables()) {
            if (e.contains(position)) {
                backpack.getBackpackSelection().activateTextBox((ItemBackpack) e);
            }
        }
    }

    @Override
    public void keyUp() {
        if (backpack.getBackpackSelection().isActiveChoice()) {
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().previousEntry();
        }
    }

    @Override
    public void keyDown() {
        if (backpack.getBackpackSelection().isActiveChoice()) {
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().nextEntry();
        }
    }

    @Override
    public void keyLeft() {
        if (!((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().getEntry(
                ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().getCurrentEntry() + 2
        ).isEmpty()) {
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().nextEntry();
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().nextEntry();
        } else if (!((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().getEntry(
                ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().getCurrentEntry() - 2
        ).isEmpty()) {
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().previousEntry();
            ((ItemBackpack)backpack.getBackpackSelection().getElement()).getItemOptions().previousEntry();
        }
    }

    @Override
    public void keyRight() {
        keyLeft(); //keyLeft and keyRight behave identically, given at most 4 options
    }

    @Override
    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (backpack.getBackpackSelection().isActiveTextBox() && !backpack.getBackpackSelection().isActiveChoice()) {
            backpack.getBackpackSelection().setActiveChoice(true);
            return;
        }
        if (!backpack.getBackpackSelection().isActiveChoice()) return;

        String command = ((ItemBackpack)backpack.getBackpackSelection().getElement()).getCommand();
        backpack.getBackpackSelection().closeTextBox();
        String[] s = command.split(" ", -1);
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene("LOCATION");
                ((HandController)gameController.getHandController()).setToGive("");
                gameController.setControllerState(gameController.getHandController());
            }
            else {
                gameController.getModel().getCurrentLocation().getTextDisplay().activateTextBox(backpack.getBackpackSelection().getElement());
                gameController.getModel().setCurrentScene("LOCATION");
                gameController.setControllerState(gameController.getNarratorController());
            }
        }
        else {
            CommandInvoker invoker = new CommandInvoker();
            if (s[0].equalsIgnoreCase("use")) {
                Command effects = new HandleEffectsCommand(gameController.getModel(), ((ItemBackpack)backpack.getBackpackSelection().getElement()).getEffects(), application);
                invoker.setCommand(effects);
                invoker.execute();
                gameController.getModel().setCurrentScene("LOCATION");
                gameController.setControllerState(gameController.getLocationController());
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
        if (!(backpack.getBackpackSelection().isActiveChoice()|| backpack.getBackpackSelection().isActiveTextBox())) {
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getLocationController());
        }
        else {
            backpack.getBackpackSelection().closeTextBox();
        }
    }

    @Override
    public void none(long time) {

    }
}
