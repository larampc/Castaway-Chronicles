package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.GetSideOptionCommand;
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


public class PauseControllerTest {
    private PauseController pauseController;
    private Application applicationMock;
    private PauseMenu pauseMenuMock;
    private Game gameMock;
    private GameController gameControllerMock;
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
        gameControllerMock = Mockito.mock(GameController.class);
        Mockito.when(gameControllerMock.getCurrent()).thenReturn(pauseController);
        Mockito.when(gameControllerMock.getModel()).thenReturn(gameMock);
        Mockito.when(gameControllerMock.getGameSaver()).thenReturn(gameSaverMock);
        pauseController = new PauseController(gameControllerMock);
    }

    @Test
    void pressed_ArrowKey() throws IOException, URISyntaxException, InterruptedException {
        pauseController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock).previousEntry();
        pauseController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock).nextEntry();
    }

    @Test
    void keySide() throws IOException, URISyntaxException, InterruptedException {
        CommandInvoker commandInvoker = Mockito.mock(CommandInvoker.class);
        gameControllerMock.setCommandInvoker(commandInvoker);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvoker);

        pauseController.key(KeyEvent.VK_LEFT, applicationMock);
        Mockito.verify(commandInvoker).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvoker).execute();

        pauseController.key(KeyEvent.VK_RIGHT, applicationMock);
        Mockito.verify(commandInvoker, Mockito.times(2)).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvoker, Mockito.times(2)).execute();
    }

    @Test
    void select_resume() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);
        Mockito.when(gameControllerMock.getLocationController()).thenReturn(controllerStateMock);

        pauseController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
    }

    @Test
    void select_exit() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(true);

        pauseController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock).setState(null);
    }

    @Test
    void select_save() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);

        pauseController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(gameSaverMock).saveGame();
    }

    @Test
    void select_Menu() throws IOException, URISyntaxException, InterruptedException {
        Mockito.when(pauseMenuMock.isSelectedMenu()).thenReturn(true);
        Mockito.when(pauseMenuMock.isSelectedResume()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedSave()).thenReturn(false);
        Mockito.when(pauseMenuMock.isSelectedExit()).thenReturn(false);

        pauseController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock).setState(Mockito.any(MainPageState.class));
    }
}
