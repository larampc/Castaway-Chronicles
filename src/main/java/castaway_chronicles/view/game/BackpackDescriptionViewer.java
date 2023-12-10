package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.ItemBackpack;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackDescriptionViewer {
    private ItemBackpack item;
    public BackpackDescriptionViewer(ItemBackpack item) {
        this.item  =item;
    }
    public void drawBackpackDescription(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        gui.drawText(new Position(6,155),190,item.getDescription(),0,false);
    }
}
