package castaway_chronicles.model.game.elements;

import java.io.IOException;

public abstract class InteractableFactory {
    public static Interactable getInteractable(String type, int x, int y, int width, int height, String name, int state) throws IOException {
        if ("Item".equalsIgnoreCase(type)) return new Item(x,y,width,height,name);
        else if("NPC".equalsIgnoreCase(type)) return new NPC(x,y,width,height,name,state);
        else if("Icon".equalsIgnoreCase(type)) return new Icon(x,y,width,height,name);
        else if("BItem".equalsIgnoreCase(type)) return new ItemBackpack(x,y,width,height,name);
        return null;
    }
}