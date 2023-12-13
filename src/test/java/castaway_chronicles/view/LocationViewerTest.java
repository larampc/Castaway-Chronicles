package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.TextDisplay;
import castaway_chronicles.view.game.LocationViewer;
import castaway_chronicles.view.game.TextBoxViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class LocationViewerTest {
    private GUI guiMock;
    private Location locationMock;
    private LocationViewer locationViewer;
    private TextBoxViewer textBoxViewerMock;
    @BeforeEach
    void setUp() {
        guiMock = Mockito.mock(GUI.class);
        locationMock = Mockito.mock(Location.class);
        textBoxViewerMock = Mockito.mock(TextBoxViewer.class);
        locationViewer = new LocationViewer();
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(new Item(0,0,0,0, "people"), new Icon(10,10, 10, 10, "forestRock")));
        Mockito.when(locationMock.getBackground()).thenReturn(new Background(10,10,10, 10, "Menu", false));
    }
    @Test
    void locationViewer() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);
        Mockito.when(locationMock.getMainChar()).thenReturn(new MainChar(10,10, 10, 10, "MainChar"));

        locationViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "Menu");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(0,0), "people");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "forestRock");
        Mockito.verify(guiMock, Mockito.times(1)).drawImage(new Position(10,10), "MainChar");
    }

    @Test
    void locationViewerNoMainChar() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);
        Mockito.when(locationMock.getMainChar()).thenReturn(null);

        locationViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(3)).drawImage(Mockito.any(Position.class), Mockito.anyString());
    }

    @Test
    void activeBox() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);

        locationViewer.draw(locationMock, guiMock);

        Mockito.verify(textBoxViewerMock, Mockito.times(1)).drawTextBox(guiMock, locationMock);
    }

    @Test
    void activeChoice() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(true);

        locationViewer.draw(locationMock, guiMock);

        Mockito.verify(textBoxViewerMock, Mockito.times(1)).drawChoices(guiMock, locationMock);
    }

    @Test
    void resizeTerminal() throws IOException, URISyntaxException, InterruptedException {
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(textDisplayMock.isActiveChoice()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(true);

        locationViewer.draw(locationMock, guiMock);

        Mockito.verify(guiMock, Mockito.times(1)).resizeTerminal();
    }
}
