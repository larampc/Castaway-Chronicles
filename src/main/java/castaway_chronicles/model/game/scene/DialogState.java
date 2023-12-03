package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.NPC;


public class DialogState {
    private boolean isDialog;
    private boolean isChoice;
    private NPC npcDialog;

    public DialogState() {
        isDialog = false;
        isChoice = false;
    }
    public boolean isDialog() {return isDialog;}
    public NPC getNPCDialog() {return npcDialog;}
    public void activateDialog(NPC npc) {npcDialog = npc; isDialog = true;}
    public void leaveDialog() {isDialog = false; isChoice = false;}
    public boolean isChoice() {
        return isChoice;
    }
    public void setChoice(boolean choice) {this.isChoice = choice;}
}
