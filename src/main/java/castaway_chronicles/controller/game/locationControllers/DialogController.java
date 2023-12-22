package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.Commands.AnswerCommand;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.Commands.TalkCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class DialogController implements ControllerState {
    private final GameController gameController;

    public DialogController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position, Application application) {
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
            case KeyEvent.VK_ENTER:
                select(application);
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

    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (gameController.getModel().getTextBox().isActiveChoice()) {
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextBox().getInteractable().getEffects(), application);
            gameController.getCommandInvoker().setCommand(effects);
            gameController.getCommandInvoker().execute();
            AnswerCommand answer = new AnswerCommand(gameController.getModel());
            gameController.getCommandInvoker().setCommand(answer);
        }
        else {
            TalkCommand talk = new TalkCommand(gameController.getModel());
            gameController.getCommandInvoker().setCommand(talk);
        }
        gameController.getCommandInvoker().execute();
        if(!gameController.getModel().getTextBox().isActiveTextBox()){
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getTextBox().getInteractable().getEffects(), application);
            gameController.getCommandInvoker().setCommand(effects);
            gameController.getCommandInvoker().execute();
            if (!gameController.getModel().getTextBox().isActiveTextBox()) gameController.setControllerState(gameController.getLocationController());
        }
    }
}
