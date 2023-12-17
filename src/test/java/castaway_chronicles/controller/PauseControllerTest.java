package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.scenes.PauseController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;

import castaway_chronicles.model.game.scene.PauseMenu;
import castaway_chronicles.states.MainPageState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class PauseControllerTest {
    private PauseController pauseController;
    private Application applicationMock;
    private PauseMenu pauseMenuMock;
    private Game gameMock;
    private GameController gameController;
    private GameSaver gameSaverMock;
    private SelectionPanel selectionPanelMock;

    @BeforeEach
    void setUp() {
        gameMock = Mockito.mock(Game.class);
        pauseMenuMock = Mockito.mock(PauseMenu.class);
        applicationMock = Mockito.mock(Application.class);
        gameSaverMock = Mockito.mock(GameSaver.class);
        selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(gameMock.getPauseMenu()).thenReturn(pauseMenuMock);
        Mockito.when(pauseMenuMock.getSelectionPanel()).thenReturn(selectionPanelMock);
        gameController = new GameController(gameMock);
        pauseController = (PauseController) gameController.getPauseController();
        pauseController.setGameSaver(gameSaverMock);
        gameController.setControllerState(pauseController);
    }

    @Test
    void pressed_ArrowKey() throws IOException, URISyntaxException, InterruptedException {
        pauseController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock).previousEntry();
        pauseController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock).nextEntry();
    }

    @Test
    void select_resume() throws IOException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);

        pauseController.select(applicationMock);
        Mockito.verify(gameMock).setCurrentScene("LOCATION");
        assertEquals(gameController.getLocationController(),gameController.getCurrent());
    }

    @Test
    void select_exit() throws IOException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(true);

        pauseController.select(applicationMock);
        Mockito.verify(applicationMock).setState(null);
    }

    @Test
    void select_save() throws IOException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);

        pauseController.select(applicationMock);
        Mockito.verify(gameSaverMock).saveGame();
    }

    @Test
    void select_Menu() throws IOException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);

        pauseController.select(applicationMock);
        Mockito.verify(applicationMock).setState(Mockito.any(MainPageState.class));
    }
}
