package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.model.game.scene.*;
import castaway_chronicles.view.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class GameViewerTest {
    private Game gameMock;

    @BeforeEach
    void setUp() {
        gameMock = mock(Game.class);

    }

    @Test
    void drawPause() throws IOException, URISyntaxException, InterruptedException {
        PauseMenuViewer pauseMenuViewer = Mockito.mock(PauseMenuViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setPauseMenuViewer(pauseMenuViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.PAUSE);
        PauseMenu pauseMenuMock = mock(PauseMenu.class);
        when(gameMock.getPauseMenu()).thenReturn(pauseMenuMock);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        when(gameMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        when(gui.isBigger()).thenReturn(false);
        gameViewer.drawScreen(gui);

        verify(pauseMenuViewer, times(1)).draw(pauseMenuMock, gui);
    }

    @Test
    void drawBackpack() throws IOException, URISyntaxException, InterruptedException {
        SceneViewer gameSceneViewer = Mockito.mock(SceneViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setGameSceneViewer(gameSceneViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.BACKPACK);
        Backpack backpackMock = mock(Backpack.class);
        when(gameMock.getBackpack()).thenReturn(backpackMock);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        when(gameMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        when(gui.isBigger()).thenReturn(false);
        gameViewer.setSelectionPanelViewer(selectionPanelViewerMock);

        gameViewer.drawScreen(gui);

        verify(selectionPanelViewerMock).setDefinitions(75, 10, new Position(50, 157));
        verify(gameSceneViewer, times(1)).draw(backpackMock, gui);
    }

    @Test
    void drawMap() throws IOException, URISyntaxException, InterruptedException {
        SceneViewer gameSceneViewer = Mockito.mock(SceneViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setGameSceneViewer(gameSceneViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.MAP);
        Map mapMock = mock(Map.class);
        when(gameMock.getMap()).thenReturn(mapMock);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        when(gameMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        when(gui.isBigger()).thenReturn(true);

        gameViewer.drawScreen(gui);

        verify(gui).resizeTerminal();
        verify(gameSceneViewer, times(1)).draw(mapMock, gui);
    }

    @Test
    void drawLocation() throws IOException, URISyntaxException, InterruptedException {
        SceneViewer gameSceneViewer = Mockito.mock(SceneViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setGameSceneViewer(gameSceneViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.LOCATION);
        Location locationMock = mock(Location.class);
        when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        when(gameMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        when(gui.isBigger()).thenReturn(false);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        gameViewer.setSelectionPanelViewer(selectionPanelViewerMock);
        InteractableWithText interactableMock = Mockito.mock(InteractableWithText.class);
        when(textDisplayMock.getInteractable()).thenReturn(interactableMock);
        when(textDisplayMock.isActiveChoice()).thenReturn(false);
        TextBoxViewer textBoxViewerMock = Mockito.mock(TextBoxViewer.class);
        gameViewer.setTextBoxViewer(textBoxViewerMock);

        gameViewer.drawScreen(gui);

        verify(textBoxViewerMock).drawTextBox(gui, interactableMock, false, selectionPanelViewerMock);
        verify(selectionPanelViewerMock).setDefinitions(0, 10, new Position(6,155));
        verify(gameSceneViewer, times(1)).draw(locationMock, gui);
    }

    @Test
    void draw() throws IOException, URISyntaxException, InterruptedException {
        SceneViewer gameSceneViewer = Mockito.mock(SceneViewer.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setGameSceneViewer(gameSceneViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.LOCATION);
        Location locationMock = mock(Location.class);
        when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        GUI guiMock = Mockito.mock(GUI.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        when(gameMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        when(guiMock.isBigger()).thenReturn(false);

        gameViewer.draw(guiMock);

        Mockito.verify(guiMock).clear();
        Mockito.verify(guiMock).refresh();
        verify(gameSceneViewer, times(1)).draw(locationMock, guiMock);

    }
}
