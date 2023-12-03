package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;

import java.io.IOException;

public class MapController implements ControllerState {
    private GameController gameController;
    public MapController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position) throws IOException, InterruptedException {
        CommandInvoker invoker = new CommandInvoker();
        for (Interactable e: gameController.getModel().getMap().getVisibleInteractables()) {
            if (e.contains(position) && e instanceof Icon) {
                String[] split = e.getName().split("_", -1);
                ChangeLocationCommand changeLocation = new ChangeLocationCommand(gameController.getModel(), split[0]);
                invoker.setCommand(changeLocation);
                invoker.execute();
                gameController.getModel().setCurrentScene("LOCATION");
                gameController.setControllerState(gameController.getLocationController());
                break;
            }
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
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        gameController.getModel().setCurrentScene("LOCATION");
        gameController.setControllerState(gameController.getLocationController());
    }
}
