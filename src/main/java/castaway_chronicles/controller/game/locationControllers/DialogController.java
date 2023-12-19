package castaway_chronicles.controller.game.locationControllers;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.NPC;

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
        if (gameController.getModel().getTextDisplay().isActiveChoice()) {
            gameController.getModel().getTextDisplay().getInteractable().getChoices().previousEntry();
        }
    }

    public void keyDown() {
        if (gameController.getModel().getTextDisplay().isActiveChoice()) {
            gameController.getModel().getTextDisplay().getInteractable().getChoices().nextEntry();
        }
    }

    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (gameController.getModel().getTextDisplay().isActiveChoice()) {
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), ((NPC)gameController.getModel().getTextDisplay().getInteractable()).getEffects(), application);
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
        if(!gameController.getModel().getTextDisplay().isActiveTextBox()){
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), ((NPC)gameController.getModel().getTextDisplay().getInteractable()).getEffects(), application);
            gameController.getCommandInvoker().setCommand(effects);
            gameController.getCommandInvoker().execute();
            if (!gameController.getModel().getTextDisplay().isActiveTextBox()) gameController.setControllerState(gameController.getLocationController());
        }
    }
}
