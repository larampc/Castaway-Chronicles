package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.gameElements.Icon;
import castaway_chronicles.model.game.gameElements.Item;
import castaway_chronicles.model.game.gameElements.MainChar;
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
    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        locationMock = Mockito.mock(Location.class);
        backpackMock = Mockito.mock(Backpack.class);
        gameSceneViewer = new SceneViewer();
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(new Item(0,0,0,0, "people"), new Icon(10,10, 10, 10, "forestRock")));
        Mockito.when(locationMock.getBackground()).thenReturn(new Background(10,10,10, 10, "Menu", false));
    }
    @Test
    void locationViewerMainChar() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(locationMock.getMainChar()).thenReturn(new MainChar(10,10, 10, 10, "MainChar"));

        gameSceneViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "Menu");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "people");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "forestRock");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "MainChar");
    }

    @Test
    void locationViewerNoMainChar() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(locationMock.getMainChar()).thenReturn(null);

        gameSceneViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
    @Test
    void sceneViewer() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(new Item(0,0,0,0, "people"), new Icon(10,10, 10, 10, "forestRock")));
        Mockito.when(backpackMock.getBackground()).thenReturn(new Background(10,10,10, 10, "Menu", false));

        gameSceneViewer.draw(backpackMock, guiMock);

        Mockito.verify(guiMock).drawImage(new Position(10,10), "Menu");
        Mockito.verify(guiMock).drawImage(new Position(0,0), "people");
        Mockito.verify(guiMock).drawImage(new Position(10,10), "forestRock");
        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
}
