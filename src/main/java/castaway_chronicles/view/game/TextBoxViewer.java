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
    private final SelectionPanelViewer selectionPanelViewer;
    public TextBoxViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
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
            if (location.getTextDisplay().isActiveChoice()) {
                selectionPanelViewer.draw(((NPC)element).getChoices(), gui);
                return;
            }
            dialog = ((NPC)element).getCurrentLine();
        }
        gui.drawText(new Position(6,155),190, dialog,false);
    }
    public void drawTextBox(GUI gui, Backpack backpack) throws IOException, InterruptedException, URISyntaxException {
        init(gui);
        ItemBackpack element = (ItemBackpack) backpack.getTextDisplay().getElement();
        if (backpack.getTextDisplay().isActiveChoice()) {
            selectionPanelViewer.draw(element.getItemOptions(), gui);
            return;
        }
        String dialog = element.getDescription();
        gui.drawText(new Position(6,155),190, dialog,false);
    }
}
