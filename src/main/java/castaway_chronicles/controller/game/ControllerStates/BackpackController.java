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

import java.awt.event.KeyEvent;
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
                backpack.getTextDisplay().activateTextBox(e);
            }
        }
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        switch (key) {
            case KeyEvent.VK_UP:
                keyUp();
                break;
            case KeyEvent.VK_DOWN:
                keyDown();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                keyLeft();
                break;
            case KeyEvent.VK_ENTER:
                select(application);
                break;
            case KeyEvent.VK_ESCAPE:
                escape();
                break;
            default:
        }
    }

    public void keyUp() {
        if (backpack.getTextDisplay().isActiveChoice()) {
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().previousEntry();
        }
    }

    public void keyDown() {
        if (backpack.getTextDisplay().isActiveChoice()) {
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().nextEntry();
        }
    }

    public void keyLeft() {
        if (!((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().getEntry(
                ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().getCurrentEntry() + 2
        ).isEmpty()) {
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().nextEntry();
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().nextEntry();
        } else if (!((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().getEntry(
                ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().getCurrentEntry() - 2
        ).isEmpty()) {
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().previousEntry();
            ((ItemBackpack)backpack.getTextDisplay().getElement()).getItemOptions().previousEntry();
        }
    }

    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (backpack.getTextDisplay().isActiveTextBox() && !backpack.getTextDisplay().isActiveChoice()) {
            backpack.getTextDisplay().setActiveChoice(true);
            return;
        }
        if (!backpack.getTextDisplay().isActiveChoice()) return;

        String command = ((ItemBackpack)backpack.getTextDisplay().getElement()).getCommand();
        backpack.getTextDisplay().closeTextBox();
        String[] s = command.split(" ", -1);
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene("LOCATION");
                ((HandController)gameController.getHandController()).setToGive("");
                gameController.setControllerState(gameController.getHandController());
            }
            else {
                gameController.getModel().getCurrentLocation().getTextDisplay().activateTextBox(backpack.getTextDisplay().getElement());
                gameController.getModel().setCurrentScene("LOCATION");
                gameController.setControllerState(gameController.getNarratorController());
            }
        }
        else {
            CommandInvoker invoker = new CommandInvoker();
            if (s[0].equalsIgnoreCase("use")) {
                Command effects = new HandleEffectsCommand(gameController.getModel(), ((ItemBackpack)backpack.getTextDisplay().getElement()).getEffects(), application);
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

    public void escape() {
        if (!(backpack.getTextDisplay().isActiveChoice()|| backpack.getTextDisplay().isActiveTextBox())) {
            gameController.getModel().setCurrentScene("LOCATION");
            gameController.setControllerState(gameController.getLocationController());
        }
        else {
            backpack.getTextDisplay().closeTextBox();
        }
    }

    @Override
    public void none(long time) {

    }
}
