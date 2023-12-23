package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameViewer extends Viewer<Game> {
    private final MenuViewer pauseMenuViewer;
    private final SceneViewer gameSceneViewer;
    private final TextBoxViewer textBoxViewer;
    public GameViewer(Game model) {
        super(model);
        pauseMenuViewer = new MenuViewer();
        getPauseMenuViewer().getSelectionPanelViewer().setDefinitions(48,20, new Position(97,101));
        gameSceneViewer = new SceneViewer();
        textBoxViewer = new TextBoxViewer();
    }
    @Override
    public void drawScreen(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        switch (getModel().getScene()) {
            case PAUSE:
                drawScene(gui, getModel().getPauseMenu(), getPauseMenuViewer());
                break;
            case BACKPACK:
                drawScene(gui, getModel().getBackpack(), getGameSceneViewer());
                getTextBoxViewer().getSelectionPanelViewer().setDefinitions( 75, 10, new Position(50, 157));
                break;
            case MAP:
                drawScene(gui, getModel().getMap(), getGameSceneViewer());
                break;
            case LOCATION:
                drawScene(gui, getModel().getCurrentLocation(), getGameSceneViewer());
                getTextBoxViewer().getSelectionPanelViewer().setDefinitions(0, 10, new Position(6,155));
                break;
        }
        if (getModel().getTextBox().isActiveTextBox()) {
            getTextBoxViewer().drawTextBox(gui, getModel().getTextBox().getInteractable() , getModel().getTextBox().isActiveChoice());
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }
    public <T> void drawScene(GUI gui, T scene, ScreenViewer<T> viewer) throws IOException, URISyntaxException, InterruptedException {
        viewer.draw(scene, gui);
    }
    public MenuViewer getPauseMenuViewer() {return pauseMenuViewer;}
    public TextBoxViewer getTextBoxViewer() {return textBoxViewer;}
    public SceneViewer getGameSceneViewer() {return gameSceneViewer;}
}
