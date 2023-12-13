package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.ScreenViewer;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameViewer extends Viewer<Game> {
    private PauseMenuViewer pauseMenuViewer;
    private MapViewer mapViewer;
    private BackpackViewer backpackViewer;
    private LocationViewer locationViewer;
    public GameViewer(Game model) {
        super(model);
        pauseMenuViewer = new PauseMenuViewer();
        mapViewer = new MapViewer();
        backpackViewer = new BackpackViewer();
        locationViewer = new LocationViewer();
    }

    @Override
    public void drawScreen(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        switch (getModel().getScene()) {
            case PAUSE:
                drawScene(gui, getModel().getPauseMenu(), pauseMenuViewer);
                break;
            case BACKPACK:
                drawScene(gui, getModel().getBackpack(), backpackViewer);
                break;
            case MAP:
                drawScene(gui, getModel().getMap(), mapViewer);
                break;
            case LOCATION:
                drawScene(gui, getModel().getCurrentLocation(), locationViewer);
                break;
        }
    }

    public <T> void drawScene(GUI gui, T model, ScreenViewer<T> viewer) throws IOException, URISyntaxException, InterruptedException {
        viewer.draw(model, gui);
    }
    public void setPauseMenuViewer(PauseMenuViewer pauseMenuViewer) {
        this.pauseMenuViewer = pauseMenuViewer;
    }
}
