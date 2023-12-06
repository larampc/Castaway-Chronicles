package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;

import java.io.IOException;

public class DialogController implements ControllerState {
    private final GameController gameController;

    public DialogController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void click(Position position) {
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
    public void select(Application application) throws IOException, InterruptedException {
        CommandInvoker invoker = new CommandInvoker();
        if (gameController.getModel().getCurrentLocation().getDialogState().isActiveChoice()) {
            AnswerCommand answer = new AnswerCommand(gameController.getModel().getCurrentLocation());
            invoker.setCommand(answer);
            invoker.execute();
            HandleEffectsCommand effects = new HandleEffectsCommand(gameController.getModel(), gameController.getModel().getCurrentLocation().getDialogState().getNPCDialog().getDialogState().getEffects());
            invoker.setCommand(effects);
        }
        else {
            TalkCommand talk = new TalkCommand(gameController.getModel().getCurrentLocation());
            invoker.setCommand(talk);
        }
        invoker.execute();
        if(!gameController.getModel().getCurrentLocation().getDialogState().isActiveDialog()){
            gameController.setControllerState(gameController.getLocationController());
        }
    }

    @Override
    public void escape() {

    }

    @Override
    public void none(long time) {

    }
}
