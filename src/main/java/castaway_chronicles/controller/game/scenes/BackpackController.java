package castaway_chronicles.controller.game.scenes;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.GetSideOptionCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.InteractableWithText;
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
                ((ItemBackpack)e).setInHand(false);
                gameController.getModel().getTextDisplay().activateTextBox((InteractableWithText) e);
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
                keySide();
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
        if (gameController.getModel().getTextDisplay().isActiveChoice()) {
            gameController.getModel().getTextDisplay().getInteractable().getChoices().previousEntry();
        }
    }

    public void keyDown() {
        if (gameController.getModel().getTextDisplay().isActiveChoice()) {
            gameController.getModel().getTextDisplay().getInteractable().getChoices().nextEntry();
        }
    }

    public void keySide() throws IOException, URISyntaxException, InterruptedException {
        GetSideOptionCommand getSide = new GetSideOptionCommand(gameController.getModel().getTextDisplay().getInteractable().getChoices());
        gameController.getCommandInvoker().setCommand(getSide);
        gameController.getCommandInvoker().execute();
    }

    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (gameController.getModel().getTextDisplay().isActiveTextBox() && !gameController.getModel().getTextDisplay().isActiveChoice()) {
            gameController.getModel().getTextDisplay().setActiveChoice(true);
            return;
        }
        if (!gameController.getModel().getTextDisplay().isActiveChoice()) return;

        String command = ((ItemBackpack)gameController.getModel().getTextDisplay().getInteractable()).getCommand();
        gameController.getModel().getTextDisplay().closeTextBox();
        String[] s = command.split(" ", -1);
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                ((HandController)gameController.getHandController()).setToGive("");
                gameController.setControllerState(gameController.getHandController());
            }
            else {
                ((ItemBackpack) gameController.getModel().getTextDisplay().getInteractable()).setInHand(true);
                gameController.getModel().getTextDisplay().activateTextBox();
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                gameController.setControllerState(gameController.getNarratorController());
            }
        }
        else {
            CommandInvoker invoker = new CommandInvoker();
            if (s[0].equalsIgnoreCase("use")) {
                Command effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextDisplay().getInteractable().getEffects(), application);
                invoker.setCommand(effects);
                invoker.execute();
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                gameController.setControllerState(gameController.getLocationController());
            }
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                ((HandController)gameController.getHandController()).setToGive(s[1]);
                gameController.setControllerState(gameController.getHandController());
            }
        }
    }

    public void escape() {
        if (!(gameController.getModel().getTextDisplay().isActiveChoice()|| gameController.getModel().getTextDisplay().isActiveTextBox())) {
            gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
            gameController.setControllerState(gameController.getLocationController());
        }
        else {
            gameController.getModel().getTextDisplay().closeTextBox();
        }
    }

}
