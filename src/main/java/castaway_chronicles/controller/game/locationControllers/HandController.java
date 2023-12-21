package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.Commands.Command;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import castaway_chronicles.model.game.gameElements.NPC;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

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
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {
        if (!toGive.isEmpty()) {
            for (Interactable e : gameController.getModel().getCurrentLocation().getVisibleInteractables()) {
                if (e instanceof NPC && e.contains(position) && e.getName().equalsIgnoreCase(toGive)) {
                    CommandInvoker invoker = gameController.getCommandInvoker();
                    Command effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextBox().getInteractable().getEffects(), application);
                    invoker.setCommand(effects);
                    invoker.execute();
                    gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                    if (gameController.getModel().getTextBox().isActiveTextBox()) {
                        gameController.setControllerState(gameController.getDialogController());
                    } else gameController.setControllerState(gameController.getLocationController());
                    return;
                }
            }
        }
        ((BackpackItem)gameController.getModel().getTextBox().getInteractable()).setInHand(true);
        gameController.getModel().getTextBox().activateTextBox();
        gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
        gameController.setControllerState(gameController.getNarratorController());
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        if (key == KeyEvent.VK_ESCAPE) {
            gameController.getModel().setCurrentScene(Game.SCENE.BACKPACK);
            gameController.setControllerState(gameController.getBackpackController());
            gameController.getCommandInvoker().execute();
        }
    }
}
