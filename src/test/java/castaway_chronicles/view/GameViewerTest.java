package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.game.scene.*;
import castaway_chronicles.view.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameViewerTest {
    private Game gameMock;
    private SceneViewer sceneViewerMock;
    private GUI guiMock;
    private MenuViewer menuViewerMock;
    private TextBoxViewer textBoxViewerMock;
    private GameViewer gameViewer;
    private TextBox textBoxMock;
    private SelectionPanelViewer selectionPanelViewerMock;

    @BeforeEach
    void setUp() {
        gameMock = Mockito.mock(Game.class);
        sceneViewerMock = Mockito.mock(SceneViewer.class);
        guiMock = Mockito.mock(GUI.class);
        menuViewerMock = Mockito.mock(MenuViewer.class);
        sceneViewerMock = Mockito.mock(SceneViewer.class);
        textBoxViewerMock = Mockito.mock(TextBoxViewer.class);
        textBoxMock = Mockito.mock(TextBox.class);
        selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        Mockito.when(menuViewerMock.getSelectionPanelViewer()).thenReturn(selectionPanelViewerMock);
        Mockito.when(gameMock.getTextBox()).thenReturn(textBoxMock);
        gameViewer = new GameViewer(gameMock) {
            @Override
            public MenuViewer getPauseMenuViewer() {
                return menuViewerMock;
            }
            @Override
            public SceneViewer getGameSceneViewer() {
                return sceneViewerMock;
            }
            @Override
            public TextBoxViewer getTextBoxViewer() {
                return textBoxViewerMock;
            }
        };
    }

    @Test
    void drawPause() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(gameMock.getScene()).thenReturn(Game.SCENE.PAUSE);
        PauseMenu pauseMenuMock = Mockito.mock(PauseMenu.class);
        Mockito.when(gameMock.getPauseMenu()).thenReturn(pauseMenuMock);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(false);

        gameViewer.drawScreen(guiMock);

        Mockito.verify(menuViewerMock).draw(pauseMenuMock, guiMock);
    }

    @Test
    void drawBackpack() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(gameMock.getScene()).thenReturn(Game.SCENE.BACKPACK);
        Backpack backpackMock = Mockito.mock(Backpack.class);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        Mockito.when(gameMock.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        Mockito.when(textBoxViewerMock.getSelectionPanelViewer()).thenReturn(selectionPanelViewerMock);

        gameViewer.drawScreen(guiMock);

        Mockito.verify(selectionPanelViewerMock).setDefinitions(75, 10, new Position(50, 157));
        Mockito.verify(sceneViewerMock).draw(backpackMock, guiMock);
    }

    @Test
    void drawMap() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(gameMock.getScene()).thenReturn(Game.SCENE.MAP);
        Map mapMock = Mockito.mock(Map.class);
        Mockito.when(gameMock.getMap()).thenReturn(mapMock);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(true);

        gameViewer.drawScreen(guiMock);

        Mockito.verify(guiMock).resizeTerminal();
        Mockito.verify(sceneViewerMock).draw(mapMock, guiMock);
    }

    @Test
    void drawLocation() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(gameMock.getScene()).thenReturn(Game.SCENE.LOCATION);
        Location locationMock = Mockito.mock(Location.class);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(guiMock.isBigger()).thenReturn(false);
        SelectionPanelViewer selectionPanelViewerMock = Mockito.mock(SelectionPanelViewer.class);
        InteractableWithText interactableMock = Mockito.mock(InteractableWithText.class);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(interactableMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(textBoxViewerMock.getSelectionPanelViewer()).thenReturn(selectionPanelViewerMock);

        gameViewer.drawScreen(guiMock);

        Mockito.verify(textBoxViewerMock).drawTextBox(guiMock, interactableMock, false);
        Mockito.verify(selectionPanelViewerMock).setDefinitions(0, 10, new Position(6,155));
        Mockito.verify(sceneViewerMock).draw(locationMock, guiMock);
    }

    @Test
    void draw() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(gameMock.getScene()).thenReturn(Game.SCENE.MAP);
        Map mapMock = Mockito.mock(Map.class);
        Mockito.when(gameMock.getMap()).thenReturn(mapMock);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(guiMock.isBigger()).thenReturn(false);

        gameViewer.draw(guiMock);

        Mockito.verify(guiMock).clear();
        Mockito.verify(guiMock).refresh();
        Mockito.verify(sceneViewerMock).draw(mapMock, guiMock);
    }

    @Test
    void getters() {
        GameViewer gameViewer1 = new GameViewer(gameMock);
        assertEquals(MenuViewer.class, gameViewer1.getPauseMenuViewer().getClass());
        assertEquals(SceneViewer.class, gameViewer1.getGameSceneViewer().getClass());
        assertEquals(TextBoxViewer.class, gameViewer1.getTextBoxViewer().getClass());
    }

    @Test
    void constructor() {
        Mockito.verify(selectionPanelViewerMock).setDefinitions(48,20, new Position(97,101));
    }
}
