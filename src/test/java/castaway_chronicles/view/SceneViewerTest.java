package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class SceneViewerTest {
    private GUI guiMock;
    private Location locationMock;
    private Backpack backpackMock;
    private SceneViewer gameSceneViewer;
    private Position positionMock;
    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        locationMock = Mockito.mock(Location.class);
        backpackMock = Mockito.mock(Backpack.class);
        positionMock = Mockito.mock(Position.class);
        Item itemMock = Mockito.mock(Item.class);
        Icon iconMock = Mockito.mock(Icon.class);
        Background backgroundMock = Mockito.mock(Background.class);

        Mockito.when(itemMock.getName()).thenReturn("people");
        Mockito.when(itemMock.getPosition()).thenReturn(positionMock);
        Mockito.when(iconMock.getPosition()).thenReturn(positionMock);
        Mockito.when(iconMock.getName()).thenReturn("forestRock");
        Mockito.when(backgroundMock.getName()).thenReturn("Menu");
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(itemMock, iconMock));
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(itemMock, iconMock));
        Mockito.when(backpackMock.getBackground()).thenReturn(backgroundMock);

        gameSceneViewer = new SceneViewer();
    }
    @Test
    void locationViewerMainChar() throws IOException, URISyntaxException, InterruptedException {
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Mockito.when(mainCharMock.getPosition()).thenReturn(positionMock);
        Mockito.when(mainCharMock.getName()).thenReturn("MainChar");
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);

        gameSceneViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "Menu");
        Mockito.verify(guiMock).drawImage(positionMock, "people");
        Mockito.verify(guiMock).drawImage(positionMock, "forestRock");
        Mockito.verify(guiMock).drawImage(positionMock, "MainChar");
    }

    @Test
    void locationViewerNoMainChar() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(locationMock.getMainChar()).thenReturn(null);

        gameSceneViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
    @Test
    void sceneViewer() throws IOException, URISyntaxException, InterruptedException {
        gameSceneViewer.draw(backpackMock, guiMock);

        Mockito.verify(guiMock).drawImage(positionMock, "Menu");
        Mockito.verify(guiMock).drawImage(positionMock, "people");
        Mockito.verify(guiMock).drawImage(positionMock, "forestRock");
        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
}
