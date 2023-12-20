package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameViewer extends Viewer<Game> {
    private MenuViewer pauseMenuViewer;
    private SceneViewer gameSceneViewer;
    private TextBoxViewer textBoxViewer;
    private SelectionPanelViewer selectionPanelViewer;
    public GameViewer(Game model) {
        super(model);
        pauseMenuViewer = new MenuViewer();
        pauseMenuViewer.getSelectionPanelViewer().setDefinitions(48,20, new Position(97,101));
        gameSceneViewer = new SceneViewer();
        textBoxViewer = new TextBoxViewer();
        selectionPanelViewer = new SelectionPanelViewer();
    }

    @Override
    public void drawScreen(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        switch (getModel().getScene()) {
            case PAUSE:
                drawScene(gui, getModel().getPauseMenu(), pauseMenuViewer);
                break;
            case BACKPACK:
                drawScene(gui, getModel().getBackpack(), gameSceneViewer);
                selectionPanelViewer.setDefinitions( 75, 10, new Position(50, 157));
                break;
            case MAP:
                drawScene(gui, getModel().getMap(), gameSceneViewer);
                break;
            case LOCATION:
                drawScene(gui, getModel().getCurrentLocation(), gameSceneViewer);
                selectionPanelViewer.setDefinitions(0, 10, new Position(6,155));
                break;
        }
        if (getModel().getTextDisplay().isActiveTextBox()) {
            textBoxViewer.drawTextBox(gui, getModel().getTextDisplay().getInteractable() , getModel().getTextDisplay().isActiveChoice(), selectionPanelViewer);
        }
        else if (gui.isBigger()) gui.resizeTerminal();
    }

    public <T> void drawScene(GUI gui, T model, ScreenViewer<T> viewer) throws IOException, URISyntaxException, InterruptedException {
        viewer.draw(model, gui);
    }
    public void setPauseMenuViewer(MenuViewer pauseMenuViewer) {
        this.pauseMenuViewer = pauseMenuViewer;
    }
    public void setSelectionPanelViewer(SelectionPanelViewer selectionPanelViewer) {
        this.selectionPanelViewer = selectionPanelViewer;
    }
    public void setTextBoxViewer(TextBoxViewer textBoxViewer) {
        this.textBoxViewer = textBoxViewer;
    }

    public void setGameSceneViewer(SceneViewer gameSceneViewer) {
        this.gameSceneViewer = gameSceneViewer;
    }
}
