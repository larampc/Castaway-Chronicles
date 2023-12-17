package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;

import java.io.IOException;
import java.net.URISyntaxException;

public class SelectionPanelViewer {
    private final int offsety;
    private final int offsetx;
    private final Position initialPosition;
    public SelectionPanelViewer(Position initialPosition, int offsetx, int offsety) {
        this.initialPosition = initialPosition;
        this.offsetx = offsetx;
        this.offsety = offsety;
    }
    public void draw(SelectionPanel model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        int y = initialPosition.getY();
        int x = initialPosition.getX();
        for (int j = 0; j < model.getNumberEntries(); j++) {
            gui.drawText(new Position(x, y), 190, model.getEntry(j), model.isSelected(j));
            y+=offsety;
            if (j == 1) {x+=offsetx; y = initialPosition.getY();}
        }
    }
}
