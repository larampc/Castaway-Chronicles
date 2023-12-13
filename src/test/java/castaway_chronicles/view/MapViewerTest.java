package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.view.game.MapViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MapViewerTest {
    private GUI guiMock;
    private Map mapMock;
    private MapViewer mapViewer;

    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        mapMock = Mockito.mock(Map.class);
        mapViewer = new MapViewer(mapMock);
        Mockito.when(mapMock.getVisibleInteractables()).thenReturn(List.of(new Item(0,0,0,0, "people"), new Icon(10,10, 10, 10, "forestRock")));
        Mockito.when(mapMock.getBackground()).thenReturn(new Background(10,10,10, 10, "Menu", false));
    }

    @Test
    void mapViewerTest() {
        mapViewer.draw(guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10, 10), "Menu");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "people");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "forestRock");
        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }
}
