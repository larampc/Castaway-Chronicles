package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public class DialogController implements ControllerState {
    private final GameController gameController;

    public DialogController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position, Application application) {
        //does nothing
    }

    @Override
    public void keyUp() {
        if (gameController.getModel().getCurrentLocation().getDialogState().isActiveChoice()) {
            gameController.getModel().getCurrentLocation()
                    .getDialogState().getNPCDialog().getDialogState().getChoices().previousEntry();
        }
    }

    @Override
    public void keyDown() {
        if (gameController.getModel().getCurrentLocation().getDialogState().isActiveChoice()) {
            gameController.getModel().getCurrentLocation()
                    .getDialogState().getNPCDialog().getDialogState().getChoices().nextEntry();
        }
    }

    @Override
    public void keyRight() {

    }

    @Override
    public void keyLeft() {

    }

    @Override
    public void select(Application application) throws IOException, InterruptedException, URISyntaxException {
        if (gameController.getModel().getCurrentLocation().getDialogState().isActiveChoice()) {
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getCurrentLocation().getDialogState().getNPCDialog().getDialogState().getEffects(), application);
            gameController.getCommandInvoker().setCommand(effects);
            gameController.getCommandInvoker().execute();
            AnswerCommand answer = new AnswerCommand(gameController.getModel().getCurrentLocation());
            gameController.getCommandInvoker().setCommand(answer);
        }
        else {
            TalkCommand talk = new TalkCommand(gameController.getModel().getCurrentLocation());
            gameController.getCommandInvoker().setCommand(talk);
        }
        gameController.getCommandInvoker().execute();
        if(!gameController.getModel().getCurrentLocation().getDialogState().isActiveDialog()){
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getCurrentLocation().getDialogState().getNPCDialog().getDialogState().getEffects(), application);
            gameController.getCommandInvoker().setCommand(effects);
            gameController.getCommandInvoker().execute();
            if (!gameController.getModel().getCurrentLocation().getDialogState().isActiveDialog()) gameController.setControllerState(gameController.getLocationController());
        }
    }

    @Override
    public void escape() {

    }

    @Override
    public void none(long time) {

    }
}
