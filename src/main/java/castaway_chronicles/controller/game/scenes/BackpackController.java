package castaway_chronicles.controller.game.scenes;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.Commands.Command;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.GetSideOptionCommand;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.game.gameElements.BackpackItem;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackController implements ControllerState {
    private final GameController gameController;
    public BackpackController(GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void click(Position position, Application application) throws IOException {
        for (Interactable e: gameController.getModel().getBackpack().getVisibleInteractables()) {
            if (e.contains(position)) {
                ((BackpackItem)e).setInHand(false);
                gameController.getModel().getTextBox().activateTextBox((InteractableWithText) e);
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
        if (gameController.getModel().getTextBox().isActiveChoice()) {
            gameController.getModel().getTextBox().getInteractable().getChoices().previousEntry();
        }
    }
    public void keyDown() {
        if (gameController.getModel().getTextBox().isActiveChoice()) {
            gameController.getModel().getTextBox().getInteractable().getChoices().nextEntry();
        }
    }
    public void keySide() throws IOException, URISyntaxException, InterruptedException {
        GetSideOptionCommand getSide = new GetSideOptionCommand(gameController.getModel().getTextBox().getInteractable().getChoices());
        gameController.getCommandInvoker().setCommand(getSide);
        gameController.getCommandInvoker().execute();
    }
    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (gameController.getModel().getTextBox().isActiveTextBox() && !gameController.getModel().getTextBox().isActiveChoice()) {
            gameController.getModel().getTextBox().setActiveChoice(true);
            return;
        }
        if (!gameController.getModel().getTextBox().isActiveChoice()) return;
        String command = ((BackpackItem)gameController.getModel().getTextBox().getInteractable()).getCommand();
        gameController.getModel().getTextBox().closeTextBox();
        String[] s = command.split(" ", -1);
        if (s.length == 1) {
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                ((HandController)gameController.getHandController()).setToGive("");
                gameController.setControllerState(gameController.getHandController());
            }
            else {
                ((BackpackItem) gameController.getModel().getTextBox().getInteractable()).setInHand(true);
                gameController.getModel().getTextBox().activateTextBox();
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                gameController.setControllerState(gameController.getNarratorController());
            }
        }
        else {
            CommandInvoker invoker = gameController.getCommandInvoker();
            if (s[0].equalsIgnoreCase("use")) {
                Command effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextBox().getInteractable().getEffects(), application);
                invoker.setCommand(effects);
                invoker.execute();
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                gameController.setControllerState(gameController.getStandingController());
            }
            if (s[0].equalsIgnoreCase("give")) {
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                ((HandController)gameController.getHandController()).setToGive(s[1]);
                gameController.setControllerState(gameController.getHandController());
            }
        }
    }
    public void escape() {
        if (!(gameController.getModel().getTextBox().isActiveChoice()|| gameController.getModel().getTextBox().isActiveTextBox())) {
            gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
            gameController.setControllerState(gameController.getStandingController());
        }
        else gameController.getModel().getTextBox().closeTextBox();
    }
}
