package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.NPC;


public class DialogState {
    private boolean activeDialog;
    private boolean activeChoice;
    private NPC npcDialog;
    public DialogState() {
        activeDialog = false;
        activeChoice = false;
    }
    public boolean isActiveDialog() {return activeDialog;}
    public NPC getNPCDialog() {return npcDialog;}
    public void activateDialog(NPC npc) {npcDialog = npc; activeDialog = true;}
    public void leaveDialog() {
        activeDialog = false; activeChoice = false;}
    public boolean isActiveChoice() {
        return activeChoice;
    }
    public void setActiveChoice(boolean activeChoice) {this.activeChoice = activeChoice;}
}
