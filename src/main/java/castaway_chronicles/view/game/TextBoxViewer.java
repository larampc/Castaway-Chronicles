package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.elements.Element;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.net.URISyntaxException;


public class TextBoxViewer {
    public TextBoxViewer() {
    }
    public void drawTextBox(GUI gui, Location location) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        String dialog;
        Element element = location.getTextDisplay().getElement();
        if (element instanceof ItemBackpack) {
            dialog = ((ItemBackpack)element).getAnswer();
        }
        else {
            dialog = ((NPC)element).getCurrentLine();
        }
        gui.drawText(new Position(6,155),190, dialog,false);
    }
    public void drawTextBox(GUI gui, Backpack currentScene) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        ItemBackpack element = (ItemBackpack) currentScene.getBackpackItemInfo().getElement();
        String dialog = element.getDescription();
        gui.drawText(new Position(6,155),190, dialog,false);
    }
    public void drawChoices(GUI gui, Backpack backpack) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offsety = 157;
        int offsetx = 50;
        ItemBackpack item = (ItemBackpack) backpack.getBackpackItemInfo().getElement();
        SelectionPanel selectionPanel = item.getItemOptions();
        for (int i = 0; i < selectionPanel.getNumberEntries(); i++) {
            gui.drawText(new Position(offsetx,offsety),190, selectionPanel.getEntry(i), i == selectionPanel.getCurrentEntry());
            offsety += 10;
            if (i == 1) {
                offsety = 157;
                offsetx = 125;
            }
        }
    }
    public void drawChoices(GUI gui, Location location) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        int offsety = 155;
        int offsetx = 6;
        NPC npc = (NPC)location.getTextDisplay().getElement();
        SelectionPanel selectionPanel = npc.getChoices();
        for (int i = 0; i < selectionPanel.getNumberEntries(); i++) {
            gui.drawText(new Position(offsetx,offsety),190, selectionPanel.getEntry(i), i == selectionPanel.getCurrentEntry());
            offsety += 10;
        }
    }
}
