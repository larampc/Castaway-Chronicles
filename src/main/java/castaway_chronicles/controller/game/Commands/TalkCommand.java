package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;

public class TalkCommand implements Command{
    private final Location location;
    public TalkCommand(Location location) {
        this.location = location;
    }
    @Override
    public void execute() {
        NPC npcDialog = (NPC)location.getTextDisplay().getElement();
        if (npcDialog.getLine() == npcDialog.getMax() && npcDialog.getChoices().getNumberEntries() == 0) {
            location.getTextDisplay().closeTextBox();
        }
        else if (npcDialog.getLine() == npcDialog.getMax()) {
            location.getTextDisplay().setActiveChoice(true);
        }
        else npcDialog.nextLine();
    }
}
