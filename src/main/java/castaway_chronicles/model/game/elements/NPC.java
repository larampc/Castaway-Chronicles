package castaway_chronicles.model.game.elements;

import java.io.IOException;

public class NPC extends Interactable {
    private NPCDialog dialogState;
    public NPC(int x, int y, int w, int h, String name, int initialState) throws IOException {
        super(x, y, w, h, name);
        dialogState = new NPCDialog(initialState, getName());
    }
    public NPCDialog getDialogState() {
        return dialogState;
    }
}
