package castaway_chronicles.controller.game.scenes;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Icon;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.Item;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapController implements ControllerState {
    private final GameController gameController;
    public MapController(GameController gameController) {this.gameController = gameController;}
    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {
        CommandInvoker invoker = gameController.getCommandInvoker();
        for (Interactable e: gameController.getModel().getMap().getVisibleInteractables()) {
            if (e.contains(position) && e instanceof Icon) {
                String[] split = e.getName().split("_", -1);
                ChangeLocationCommand changeLocation = new ChangeLocationCommand(gameController.getModel(), split[0]);
                invoker.setCommand(changeLocation);
                invoker.execute();
                gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
                gameController.setControllerState(gameController.getStandingController());
                break;
            }
            if (e.contains(position) && e instanceof Item) {
                String[] split = e.getName().split("_", -1);
                gameController.getModel().getMap().setInvisible(e.getName());
                gameController.getModel().getMap().setVisible(split[0]+"_icon");
            }
        }
    }
    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        if (key == KeyEvent.VK_ESCAPE) {
            gameController.getModel().setCurrentScene(Game.SCENE.LOCATION);
            gameController.setControllerState(gameController.getStandingController());
        }
    }
}
