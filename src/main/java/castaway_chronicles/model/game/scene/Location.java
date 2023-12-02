package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.elements.NPC;

import java.util.HashMap;

public class Location extends Scene {
    private MainChar mainChar;
    private boolean isDialog = false;
    private NPC dialog;
    public Location(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, MainChar mainChar) {
        super(background,interactables, visibleInteractables);
        this.mainChar = mainChar;
    }
    public MainChar getMainChar() {return mainChar;}
    public void setMainChar(MainChar mainChar) {this.mainChar = mainChar;}
    public boolean isDialog() {return isDialog;}
    public NPC getNPCDialog() {return dialog;}
    public void activateDialog(String npc) {dialog = (NPC) getInteractable(npc); isDialog = true;}
    public void leaveDialog() {isDialog = false;}
}
