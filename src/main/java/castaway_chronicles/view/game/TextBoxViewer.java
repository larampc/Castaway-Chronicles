package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Element;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;
import java.net.URISyntaxException;


public class TextBoxViewer {
    private final Element element;
    public TextBoxViewer(Element element) {
        this.element = element;
    }
    public void drawTextBox(GUI gui, Game.SCENE currentScene) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        String dialog = "";
        if (element instanceof NPC) {
            dialog = ((NPC)element).getCurrentLine();
        }
        if (element instanceof ItemBackpack && currentScene == Game.SCENE.LOCATION) {
            dialog = ((ItemBackpack)element).getAnswer();
        }
        if (element instanceof ItemBackpack && currentScene == Game.SCENE.BACKPACK) {
            dialog = ((ItemBackpack)element).getDescription();
        }
        gui.drawText(new Position(6,155),190, dialog,false);
    }
    public void drawChoices(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offsety = 0;
        int offsetx = 6;
        SelectionPanel selectionPanel = null;
        if (element instanceof NPC) {
            selectionPanel = ((NPC)element).getChoices();
            offsety  = 155;
        }
        if (element instanceof ItemBackpack) {
            selectionPanel = ((ItemBackpack)element).getItemOptions();
            offsety  = 157;
            offsetx = 50;
        }
        for (int i = 0; i < selectionPanel.getNumberEntries(); i++) {
            gui.drawText(new Position(offsetx,offsety),190, selectionPanel.getEntry(i), i == selectionPanel.getCurrentEntry());
            offsety += 10;
            if (i == 1 && element instanceof ItemBackpack) {
                offsety = 157;
                offsetx = 125;
            }
        }
    }
}
