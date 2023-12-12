package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.controller.game.ControllerStates.WalkingController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WalkingControllerTest {
    private Game game;
    private WalkingController walkingController;
    private GameController gameController;
    private Application application;
    private CommandInvoker commandInvoker;

    @BeforeEach
    void init() {
        game = Mockito.mock(Game.class);
        gameController = new GameController(game);
        commandInvoker = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvoker);

        application = Mockito.mock(Application.class);
        gameController.setControllerState(gameController.getWalkingController());
        walkingController = (WalkingController) gameController.getWalkingController();

        Location startLocation = Mockito.mock(Location.class);
        Mockito.when(game.getCurrentLocation()).thenReturn(startLocation);
        Background background = Mockito.mock(Background.class);
        Mockito.when(startLocation.getBackground()).thenReturn(background);
        Mockito.when(background.getPosition()).thenReturn(new Position(-100,0));
        Mockito.when(background.getWidth()).thenReturn(700);
        Mockito.when(background.getHeight()).thenReturn(150);
        MainChar mainChar = Mockito.mock(MainChar.class);
        Mockito.when(startLocation.getMainChar()).thenReturn(mainChar);
        Mockito.when(mainChar.getPosition()).thenReturn(new Position(100,100));
        Mockito.when(mainChar.getWidth()).thenReturn(10);
        Mockito.when(mainChar.getHeight()).thenReturn(10);
        Mockito.when(mainChar.getName()).thenReturn("standing_right");

    }

    @Test
    void setToWalkLegalRight() {
        assertEquals (0,walkingController.getToWalk());
        assertFalse (walkingController.isFacingRight());

        boolean walked = walkingController.setTowalk(new Position(200,123));
        assertTrue (walked);
        assertTrue (walkingController.getToWalk() < 0);
        assertTrue (walkingController.isFacingRight());
    }

    @Test
    void setToWalkLegalLeft() {
        assertEquals (0,walkingController.getToWalk());
        assertFalse (walkingController.isFacingRight());

        boolean walked = walkingController.setTowalk(new Position(50,123));
        assertTrue (walked);
        assertTrue (walkingController.getToWalk() > 0);
        assertFalse (walkingController.isFacingRight());
    }

    @Test
    void walk() throws IOException, InterruptedException {
        long time = 0;
        assertEquals (0,walkingController.getToWalk());
        assertFalse (walkingController.isFacingRight());

        walkingController.click(new Position(90,9));
        assertEquals (0,walkingController.getToWalk());
        assertFalse (walkingController.isFacingRight());

        walkingController.click(new Position(110,9));
        assertEquals (0,walkingController.getToWalk());
        assertTrue (walkingController.isFacingRight());

        walkingController.click(new Position(150,9));
        assertEquals (-3,walkingController.getToWalk());

        walkingController.click(new Position(200,9));
        assertEquals (-8,walkingController.getToWalk());

        walkingController.none(time+150);
        assertEquals (-8,walkingController.getToWalk());

        for(int i = 1; i <= 8; i++) {
            walkingController.none(time+i*200);
            assertEquals (-8+i,walkingController.getToWalk());
            //Mockito.verify(commandInvoker,Mockito.times(i)).setCommand(new MoveCommand(startLocation,-10));
            Mockito.verify(commandInvoker,Mockito.times(i)).setCommand(Mockito.any(MoveCommand.class));
            Mockito.verify(commandInvoker,Mockito.times(i)).execute();
        }
        walkingController.none(time+2000);
        assertEquals(gameController.getLocationController(), gameController.getCurrent());
    }

//    @Test
//    void walkMostPossibleOnOutOfBoundsClick() throws IOException, InterruptedException {
//        long time = 0;
//        assertEquals(-100,game.getCurrentLocation().getBackground().getPosition().getX());
//        assertEquals (0,walkingController.getToWalk());
//        assertFalse (walkingController.isFacingRight());

//        gameController.step(application, new ClickAction("CLICK", new Position(0,9)),time);
//        while(walkingController.getToWalk() != 0) {
//            time += 200;
//            gameController.step(application, new ClickAction("DUMMY", new Position(150,9)),time);
//        }
//    }

    @Property
    public void inBounds(@ForAll List<@From("moveActions") Position> positionList) throws IOException, InterruptedException {
        init();
        long time = 0;
        for(Position position : positionList){
            gameController.step(application, new ClickAction("CLICK", position),time);
            while(walkingController.getToWalk() != 0) {
                time += 200;
                walkingController.none(time);
            }
        }
        time += 200;
        walkingController.none(time);

        assertEquals(0, game.getCurrentLocation().getBackground().getPosition().getY());
        assertTrue(game.getCurrentLocation().getBackground().getPosition().getX() <= 0 &&
                game.getCurrentLocation().getBackground().getPosition().getX() >= 200 - game.getCurrentLocation().getBackground().getWidth());
        assertEquals("standing",game.getCurrentLocation().getMainChar().getName().split("_",-1)[0]);
    }
    @Provide@Size(max = 20)
    Arbitrary<Position> moveActions() {
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 150);
        return Arbitraries.of(new Position(x, y));
    }

}


