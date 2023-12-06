package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.ItemBackpack;

import java.io.IOException;
import java.net.URISyntaxException;

public class BackpackAnswerViewer {
    private ItemBackpack item;
    public BackpackAnswerViewer(ItemBackpack item) {
        this.item  =item;
    }
    public void drawBackpackAnswer(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        gui.drawText(new Position(6,155),190,item.getAnswer(),0,false);
    }
}
