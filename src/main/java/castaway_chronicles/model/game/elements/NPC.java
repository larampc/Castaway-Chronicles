package castaway_chronicles.model.game.elements;

import java.io.IOException;

public class NPC extends Interactable {
    private DialogState state = new DialogState(0,0, getName());
    public NPC(int x, int y, int w, int h, String name) throws IOException {
        super(x, y, w, h, name);
    }
    public DialogState getState() {
        return state;
    }
}
