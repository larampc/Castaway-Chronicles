package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.scene.Location;

public class TalkCommand implements Command{
    private final Location location;
    public TalkCommand(Location location) {
        this.location = location;
    }
    @Override
    public void execute() {
        if (location.getDialogState().getNPCDialog().getDialogState().getLine() == location.getDialogState().getNPCDialog().getDialogState().getMax() && location.getDialogState().getNPCDialog().getDialogState().getChoices().getNumberEntries() == 0) {
            location.getDialogState().leaveDialog();
        }
        else if (location.getDialogState().getNPCDialog().getDialogState().getLine() == location.getDialogState().getNPCDialog().getDialogState().getMax()) {
            location.getDialogState().setActiveChoice(true);
        }
        else location.getDialogState().getNPCDialog().getDialogState().nextLine();
    }
}
