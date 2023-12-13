package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewer implements ScreenViewer<SelectionPanel> {
    private final int offsety;
    private final int offsetx;
    private final Position initialPosition;
    private final String name;
    public MenuViewer(Position initialPosition, int offsetx, int offsety, String name) {
        this.initialPosition = initialPosition;
        this.offsetx = offsetx;
        this.offsety = offsety;
        this.name = name;
    }
    @Override
    public void draw(SelectionPanel model, GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.drawImage(new Position(0,0), name);
        int y = initialPosition.getY();
        int x = initialPosition.getX();
        for (int j = 0; j < model.getNumberEntries(); j++) {
            gui.drawText(new Position(x, y), 160, model.getEntry(j), model.isSelected(j));
            y+=offsety;
            if (j == 1) {x+=offsetx; y = initialPosition.getY();}
        }
    }
}
