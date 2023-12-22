package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.controller.game.locationControllers.StandingController;
import castaway_chronicles.controller.game.locationControllers.WalkingController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.gameElements.MainChar;
import castaway_chronicles.model.game.scene.Location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class WalkingControllerTest {
    private WalkingController walkingController;
    private GameController gameControllerMock;
    private Application applicationMock;
    private CommandInvoker commandInvokerMock;
    private Background backgroundMock;
    private MainChar mainCharMock;
    private Position positionWalkToMock;
    private Position positionBackgroundMock;
    private Position positionCharacterMock;

    @BeforeEach
    void init() {
        Game gameMock = Mockito.mock(Game.class);
        gameControllerMock = Mockito.mock(GameController.class);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        applicationMock = Mockito.mock(Application.class);
        Location locationMock = Mockito.mock(Location.class);
        backgroundMock = Mockito.mock(Background.class);
        walkingController = new WalkingController(gameControllerMock);
        mainCharMock = Mockito.mock(MainChar.class);
        positionWalkToMock = Mockito.mock(Position.class);
        positionBackgroundMock = Mockito.mock(Position.class);
        positionCharacterMock = Mockito.mock(Position.class);
        Mockito.when(gameControllerMock.getModel()).thenReturn(gameMock);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvokerMock);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionBackgroundMock);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundMock.getHeight()).thenReturn(150);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getPosition()).thenReturn(positionCharacterMock);
        Mockito.when(mainCharMock.getWidth()).thenReturn(10);
        Mockito.when(mainCharMock.getHeight()).thenReturn(10);
        Mockito.when(mainCharMock.getName()).thenReturn("standing_right");

    }

    @Test
    void WalkingControllerContent() {
        assertEquals (0,walkingController.getToWalk());
        assertFalse (walkingController.isFacingRight());
    }

    @Test
    void setToWalk() throws IOException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControllerMock.getPrevious()).thenReturn(controllerStateMock);
        StandingController standingControllerMock = Mockito.mock(StandingController.class);
        Mockito.when(gameControllerMock.getLocationController()).thenReturn(standingControllerMock);

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(100);
        Mockito.when(positionCharacterMock.getX()).thenReturn(100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        walkingController.click(positionWalkToMock, applicationMock);

        assertEquals(11, walkingController.getToWalk());
        assertFalse(walkingController.isFacingRight());
        Mockito.verify(standingControllerMock).setLastCommandNull();

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(100);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-25);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertTrue(walkingController.setToWalk(positionWalkToMock));

        assertEquals(-60, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(90);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertTrue(walkingController.setToWalk(positionWalkToMock));

        assertEquals(-8, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-200);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-310);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertTrue(walkingController.setToWalk(positionWalkToMock));

        assertEquals(-29, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-200);
        Mockito.when(positionCharacterMock.getX()).thenReturn(100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(true);

        assertTrue(walkingController.setToWalk(positionWalkToMock));

        assertEquals(10, walkingController.getToWalk());
        assertFalse(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(0);
        Mockito.when(positionCharacterMock.getX()).thenReturn(100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertFalse(walkingController.setToWalk(positionWalkToMock));

        assertEquals(0, walkingController.getToWalk());
        assertFalse(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-490);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertTrue(walkingController.setToWalk(positionWalkToMock));
        assertEquals(1, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());

        assertFalse(walkingController.setToWalk(positionWalkToMock));

        assertEquals(0, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(181);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-490);
        Mockito.when(positionCharacterMock.getX()).thenReturn(176);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertFalse(walkingController.setToWalk(positionWalkToMock));

        assertEquals(0, walkingController.getToWalk());
        assertFalse(walkingController.isFacingRight());

        Mockito.when(positionWalkToMock.getX()).thenReturn(200);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-490);
        Mockito.when(positionCharacterMock.getX()).thenReturn(176);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);

        assertFalse(walkingController.setToWalk(positionWalkToMock));

        assertEquals(0, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());
    }

    @Test
    void none() throws IOException, InterruptedException, URISyntaxException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControllerMock.getPrevious()).thenReturn(controllerStateMock);

        walkingController.none(0);
        Mockito.verify(mainCharMock).setName("standing_left");
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock, Mockito.never()).execute();


        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-490);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        assertTrue(walkingController.setToWalk(positionWalkToMock));
        assertEquals(1, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());


        walkingController.none(150);
        Mockito.verify(mainCharMock).setName(Mockito.anyString());
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock, Mockito.never()).execute();

        walkingController.none(151);
        Mockito.verify(mainCharMock).setName(Mockito.anyString());
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(MoveCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        assertEquals(0, walkingController.getToWalk());

        walkingController.none(0);
        Mockito.verify(mainCharMock).setName("standing_right");
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock).execute();



        Mockito.when(positionWalkToMock.getX()).thenReturn(181);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-490);
        Mockito.when(positionCharacterMock.getX()).thenReturn(176);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        walkingController.setToWalk(positionWalkToMock);
        Mockito.when(positionWalkToMock.getX()).thenReturn(0);
        Mockito.when(positionBackgroundMock.getX()).thenReturn(-460);
        Mockito.when(positionCharacterMock.getX()).thenReturn(-100);
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        walkingController.setToWalk(positionWalkToMock);
        assertEquals(-4, walkingController.getToWalk());
        assertTrue(walkingController.isFacingRight());


        walkingController.none(0);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock).execute();

        walkingController.none(302);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(commandInvokerMock, Mockito.times(2)).setCommand(Mockito.any(MoveCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).execute();
        assertEquals(-3, walkingController.getToWalk());
    }
}


