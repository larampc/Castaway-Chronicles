package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;

import java.io.IOException;
import java.net.URISyntaxException;

public class SelectionPanelViewer {
    private int offsetY;
    private int offsetX;
    private Position initialPosition;
    public SelectionPanelViewer(Position initialPosition, int offsetX, int offsetY) {
        this.initialPosition = initialPosition;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    public SelectionPanelViewer() {
        initialPosition = new Position(6,155);
        offsetX = 0;
        offsetY = 10;
    }
    public void draw(SelectionPanel model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        int y = initialPosition.getY();
        int x = initialPosition.getX();
        for (int j = 0; j < model.getNumberEntries(); j++) {
            gui.drawText(new Position(x, y), 190, model.getEntry(j), model.isSelected(j));
            y+= offsetY;
            if (j == 1) {x+= offsetX; y = initialPosition.getY();}
        }
    }
    public void setDefinitions(int offsetX, int offsetY, Position initialPosition) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.initialPosition = initialPosition;
    }
}
