package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.view.SelectionPanelViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class TextBoxViewer {
    private SelectionPanelViewer selectionPanelViewer;
    public TextBoxViewer() {
        selectionPanelViewer = new SelectionPanelViewer();
    }
    public void drawTextBox(GUI gui, InteractableWithText interactable, boolean choice) throws IOException, InterruptedException, URISyntaxException {
        if (!gui.isBigger()) gui.resizeTerminal();
        gui.drawImage(new Position(2,151), "dialog");
        if (choice) selectionPanelViewer.draw(interactable.getChoices(), gui);
        else gui.drawText(new Position(6,155),190, interactable.getText(),false);
    }

    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
    public SelectionPanelViewer getSelectionPanelViewer() {
        return selectionPanelViewer;
    }
}
