package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.scene.Backpack;

import java.io.IOException;


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
                backpack.getBackpackSelection().activate((ItemBackpack) e);
            }
        }
    }

    @Override
    public void keyUp() {
        backpack.getBackpackSelection().getItem().getItemOptions().previousEntry();
    }

    @Override
    public void keyDown() {
        backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
    }

    @Override
    public void keyLeft() {
//        backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
//        backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
    }

    @Override
    public void keyRight() {
//        backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
//        backpack.getBackpackSelection().getItem().getItemOptions().nextEntry();
    }

    @Override
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        if (!backpack.getBackpackSelection().isSelection()) {
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
