package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.ControllerStates.LocationController;
import castaway_chronicles.controller.game.ControllerStates.PauseController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.gui.KeyAction;
import castaway_chronicles.model.game.Game;

import castaway_chronicles.model.game.scene.PauseMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class PauseControllerTest {
    private GameController gameController;
    private Application application;
    private PauseMenu pauseMenu;

    @BeforeEach
    void init() {
        Game game = new Game();
        pauseMenu = new PauseMenu();
        game.setPauseMenu(pauseMenu);
        gameController = new GameController(game);
        application = Mockito.mock(Application.class);
        game.setCurrentScene("PAUSE");
        gameController.setControllerState(gameController.getPauseController());
    }

//    @Test
//    void empty_keys() throws IOException, InterruptedException {
//        assertTrue(pauseMenu.isSelectedResume());
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
//        assertTrue(gameController.getCurrent() instanceof PauseController);
//
//        gameController.step(application, new KeyAction("RIGHT"),0);
//        assertTrue(pauseMenu.isSelectedResume());
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
//        assertTrue(gameController.getCurrent() instanceof PauseController);
//
//        gameController.step(application, new KeyAction("LEFT"),0);
//        assertTrue(pauseMenu.isSelectedResume());
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
//        assertTrue(gameController.getCurrent() instanceof PauseController);
//
//        gameController.step(application, new ClickAction("CLICK", new Position(25,50)),0);
//        assertTrue(pauseMenu.isSelectedResume());
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
//        assertTrue(gameController.getCurrent() instanceof PauseController);
//
//        gameController.step(application, new KeyAction("ESCAPE"),0);
//        assertTrue(pauseMenu.isSelectedResume());
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
//        assertTrue(gameController.getCurrent() instanceof PauseController);
//    }

    @Test
    void pressed_ArrowKey() throws IOException, InterruptedException, URISyntaxException {
        assertTrue(gameController.getCurrent() instanceof PauseController);
        assertTrue(pauseMenu.isSelectedResume());
        gameController.step(application, new KeyAction("DOWN"),0);
        assertTrue(pauseMenu.isSelectedSave());
        gameController.step(application, new KeyAction("DOWN"),0);
        assertTrue(pauseMenu.isSelectedExit());
        gameController.step(application, new KeyAction("DOWN"),0);
        assertTrue(pauseMenu.isSelectedResume());
        gameController.step(application, new KeyAction("UP"),0);
        assertTrue(pauseMenu.isSelectedExit());
        gameController.step(application, new KeyAction("UP"),0);
        assertTrue(pauseMenu.isSelectedSave());
        gameController.step(application, new KeyAction("UP"),0);
        assertTrue(pauseMenu.isSelectedResume());
        assertTrue(gameController.getCurrent() instanceof PauseController);
    }

    @Test
    void select_resume() throws IOException, InterruptedException, URISyntaxException {
        assertTrue(pauseMenu.isSelectedResume());
        assertEquals(gameController.getModel().getScene(), Game.SCENE.PAUSE);
        assertTrue(gameController.getCurrent() instanceof PauseController);
        gameController.step(application, new KeyAction("SELECT"),0);
        assertEquals(gameController.getModel().getScene(), Game.SCENE.LOCATION);
        assertTrue(gameController.getCurrent() instanceof LocationController);
    }

    @Test
    void select_exit() throws IOException, InterruptedException, URISyntaxException {
        assertTrue(pauseMenu.isSelectedResume());
        assertTrue(gameController.getCurrent() instanceof PauseController);
        gameController.step(application, new KeyAction("DOWN"),0);
        gameController.step(application, new KeyAction("DOWN"),0);
        gameController.step(application, new KeyAction("SELECT"),0);
        Mockito.verify(application).setState(null);
    }
}
