package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.ItemBackpack;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackSelectionViewer {
    private ItemBackpack item;
    public BackpackSelectionViewer(ItemBackpack item) {
        this.item = item;
    }
    public void drawBackpackSelectionViewer(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offsety  = 157;
        int offsetx = 50;
        for (int i = 0; i < item.getItemOptions().getNumberEntries(); i++) {
            gui.drawText(new Position(offsetx,offsety),190,item.getItemOptions().getEntry(i),0, i == item.getItemOptions().getCurrentEntry());
            offsety += 10;
            if (i == 1) {
                offsety = 157;
                offsetx = 125;
            }
        }
    }
}
