package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.elements.NPC;

import java.util.HashMap;

public class Location extends Scene {
    private MainChar mainChar;
    private int offset = 0;
    private DialogState dialog = new DialogState();
    public Location(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, MainChar mainChar) {
        super(background,interactables, visibleInteractables);
        this.mainChar = mainChar;
    }
    public MainChar getMainChar() {return mainChar;}
    public void setMainChar(MainChar mainChar) {this.mainChar = mainChar;}
    public DialogState getDialogState() {return dialog;}
    public void setDialog(String npc) {
        dialog.activateDialog((NPC) getInteractable(npc));
    }
    public int getOffset(){
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
