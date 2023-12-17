package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Element;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.view.SelectionPanelViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class TextBoxViewer {
    public TextBoxViewer() {}
    public void init(GUI gui) throws IOException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
    }
    public void drawTextBox(GUI gui, Location location) throws IOException, InterruptedException, URISyntaxException {
        init(gui);
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
        init(gui);
        ItemBackpack element = (ItemBackpack) currentScene.getTextDisplay().getElement();
        String dialog = element.getDescription();
        gui.drawText(new Position(6,155),190, dialog,false);
    }
    public void drawChoices(GUI gui, Backpack backpack, SelectionPanelViewer selectionPanelViewer) throws IOException, InterruptedException, URISyntaxException {
        init(gui);
        ItemBackpack item = (ItemBackpack) backpack.getTextDisplay().getElement();
        selectionPanelViewer.draw(item.getItemOptions(), gui);
    }
    public void drawChoices(GUI gui, Location location, SelectionPanelViewer selectionPanelViewer) throws IOException, InterruptedException, URISyntaxException {
        init(gui);
        NPC npc = (NPC)location.getTextDisplay().getElement();
        selectionPanelViewer.draw(npc.getChoices(), gui);
    }
}
