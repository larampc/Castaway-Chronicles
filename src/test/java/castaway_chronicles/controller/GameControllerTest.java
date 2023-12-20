package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.controller.game.locationControllers.*;
import castaway_chronicles.controller.game.scenes.BackpackController;
import castaway_chronicles.controller.game.scenes.MapController;
import castaway_chronicles.controller.game.scenes.PauseController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    private GameController gameController;
    private Application applicationMock;

    @BeforeEach
    void setUp() {
        applicationMock = Mockito.mock(Application.class);
        Game gameMock = Mockito.mock(Game.class);
        gameController = new GameController(gameMock);
    }

    @Test
    void GameControllerContent() {
        assertEquals(BackpackController.class, gameController.getBackpackController().getClass());
        assertEquals(DialogController.class, gameController.getDialogController().getClass());
        assertEquals(StandingController.class, gameController.getLocationController().getClass());
        assertEquals(MapController.class, gameController.getMapController().getClass());
        assertEquals(PauseController.class, gameController.getPauseController().getClass());
        assertEquals(HandController.class, gameController.getHandController().getClass());
        assertEquals(WalkingController.class, gameController.getWalkingController().getClass());
        assertEquals(NarratorController.class, gameController.getNarratorController().getClass());
        assertEquals(GameSaver.class, gameController.getGameSaver().getClass());
        assertEquals(CommandInvoker.class, gameController.getCommandInvoker().getClass());
    }

    @Test
    void step() throws IOException, URISyntaxException, InterruptedException {
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);
        KeyEvent keyEventMock = Mockito.mock(KeyEvent.class);
        MouseEvent mouseEventMock = Mockito.mock(MouseEvent.class);
        Mockito.when(keyEventMock.getKeyCode()).thenReturn(3);
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        gameController.setControllerState(controllerStateMock);
        assertEquals(controllerStateMock, gameController.getCurrent());

        gameController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(controllerStateMock, Mockito.times(0)).key(Mockito.anyInt(), Mockito.any());

        gameController.step(applicationMock, keyEventMock, 0);
        Mockito.verify(controllerStateMock).key(3, applicationMock);

        gameController.step(applicationMock, mouseEventMock, 0);
        Mockito.verify(controllerStateMock).click(new Position(mouseEventMock.getX()/4, mouseEventMock.getY()/4), applicationMock);

        ContinuousControllerState continuousControllerStateMock = Mockito.mock(ContinuousControllerState.class);
        gameController.setControllerState(continuousControllerStateMock);
        assertEquals(continuousControllerStateMock, gameController.getCurrent());

        gameController.step(applicationMock, inputEventMock, 10);
        Mockito.verify(continuousControllerStateMock).none(10);
    }
}
