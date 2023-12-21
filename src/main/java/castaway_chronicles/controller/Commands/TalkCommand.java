package castaway_chronicles.controller.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;

public class TalkCommand implements Command{
    private final Game game;
    public TalkCommand(Game game) {
        this.game = game;
    }
    @Override
    public void execute() {
        NPC npcDialog = (NPC) game.getTextBox().getInteractable();
        if (npcDialog.dialogEnded() && npcDialog.getChoices().getNumberEntries() == 0) {
            game.getTextBox().closeTextBox();
        }
        else if (npcDialog.dialogEnded()) {
            game.getTextBox().setActiveChoice(true);
        }
        else npcDialog.nextLine();
    }
}
