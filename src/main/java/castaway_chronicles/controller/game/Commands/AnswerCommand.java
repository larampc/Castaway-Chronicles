package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class AnswerCommand implements Command {
    private Location location;
    public AnswerCommand(Location location) {
        this.location = location;
    }
    @Override
    public void execute() throws IOException {
        location.getDialogState().getNPCDialog().getState().goToStateChoice(location.getDialogState().getNPCDialog().getState().getLine());
        location.getDialogState().setChoice(false);
    }
}
