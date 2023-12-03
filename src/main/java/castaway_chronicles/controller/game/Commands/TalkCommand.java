package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.scene.Location;

public class TalkCommand implements Command{
    private Location location;
    public TalkCommand(Location location) {
        this.location = location;
    }
    @Override
    public void execute() {
        if (location.getDialogState().getNPCDialog().getState().getLine() == location.getDialogState().getNPCDialog().getState().getMax() && location.getDialogState().getNPCDialog().getState().getChoices() == 0) {
            location.getDialogState().leaveDialog();
        }
        else if (location.getDialogState().getNPCDialog().getState().getLine() == location.getDialogState().getNPCDialog().getState().getMax()) {
            location.getDialogState().setChoice(true);
            location.getDialogState().getNPCDialog().getState().nextLine();
            location.getDialogState().getNPCDialog().getState().nextLine();
        }
        else location.getDialogState().getNPCDialog().getState().nextLine();
    }
}
