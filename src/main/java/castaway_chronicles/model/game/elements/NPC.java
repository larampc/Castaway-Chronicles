package castaway_chronicles.model.game.elements;

import java.io.IOException;

public class NPC extends Interactable {
    private NPCDialog state;
    public NPC(int x, int y, int w, int h, String name, int initialState) throws IOException {
        super(x, y, w, h, name);
        state = new NPCDialog(initialState,0, getName());
    }
    public NPCDialog getState() {
        return state;
    }
}
