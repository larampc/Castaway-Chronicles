package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.PauseMenu;
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

        gameViewer.drawScreen(gui);

        verify(pauseMenuViewer, times(1)).draw(pauseMenuMock, gui);
    }

    @Test
    void drawBackpack() throws IOException, URISyntaxException, InterruptedException {
        BackpackViewer backpackViewer = Mockito.mock(BackpackViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setBackpackViewer(backpackViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.BACKPACK);
        Backpack backpackMock = mock(Backpack.class);
        when(gameMock.getBackpack()).thenReturn(backpackMock);

        gameViewer.drawScreen(gui);

        verify(backpackViewer, times(1)).draw(backpackMock, gui);
    }

    @Test
    void drawMap() throws IOException, URISyntaxException, InterruptedException {
        MapViewer mapViewer = Mockito.mock(MapViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setMapViewer(mapViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.MAP);
        Map mapMock = mock(Map.class);
        when(gameMock.getMap()).thenReturn(mapMock);

        gameViewer.drawScreen(gui);

        verify(mapViewer, times(1)).draw(mapMock, gui);
    }

    @Test
    void drawLocation() throws IOException, URISyntaxException, InterruptedException {
        LocationViewer locationViewer = Mockito.mock(LocationViewer.class);
        GUI gui = mock(GUI.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setLocationViewer(locationViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.LOCATION);
        Location locationMock = mock(Location.class);
        when(gameMock.getCurrentLocation()).thenReturn(locationMock);

        gameViewer.drawScreen(gui);

        verify(locationViewer, times(1)).draw(locationMock, gui);
    }

    @Test
    void draw() throws IOException, URISyntaxException, InterruptedException {
        LocationViewer locationViewer = Mockito.mock(LocationViewer.class);
        GameViewer gameViewer = new GameViewer(gameMock);
        gameViewer.setLocationViewer(locationViewer);
        when(gameMock.getScene()).thenReturn(Game.SCENE.LOCATION);
        Location locationMock = mock(Location.class);
        when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        GUI guiMock = Mockito.mock(GUI.class);

        gameViewer.draw(guiMock);

        Mockito.verify(guiMock).clear();
        Mockito.verify(guiMock).refresh();
        verify(locationViewer, times(1)).draw(locationMock, guiMock);

    }
}
