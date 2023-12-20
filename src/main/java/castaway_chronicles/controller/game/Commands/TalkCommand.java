package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;

public class TalkCommand implements Command{
    private final Game location;
    public TalkCommand(Game location) {
        this.location = location;
    }
    @Override
    public void execute() {
        NPC npcDialog = (NPC)location.getTextDisplay().getInteractable();
        if (npcDialog.dialogEnded() && npcDialog.getChoices().getNumberEntries() == 0) {
            location.getTextDisplay().closeTextBox();
        }
        else if (npcDialog.dialogEnded()) {
            location.getTextDisplay().setActiveChoice(true);
        }
        else npcDialog.nextLine();
    }
}
