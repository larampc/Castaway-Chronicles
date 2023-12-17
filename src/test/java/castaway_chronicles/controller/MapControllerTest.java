package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.scenes.LocationController;
import castaway_chronicles.controller.game.scenes.MapController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapControllerTest {
    private Game game;
    private Application application;
    private GameController gameController;
    @BeforeEach
    public void init() {
        game = new Game();

        HashMap<String, Interactable> interactables = new HashMap<>();
        HashMap<String, Interactable> visibleInteractables = new HashMap<>();
        Icon icon = new Icon(5,5,10,10,"City");
        interactables.put("Icon",icon);
        visibleInteractables.put("Icon",icon);
        interactables.put("Icon",new Icon(20,20,10,10,"Beach"));

        game.setMap(new Map(new Background(0,0,200,150,"map", false),interactables,visibleInteractables));
        game.setCurrentScene("MAP");

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",new Location(new Background(0,0,700,150,"Beach", false),
                new HashMap<>(), new HashMap<>(), new MainChar(100,100,10,10,"standing_right")));
        locations.put("City",new Location(new Background(0,0,700,150,"Beach", false),
                new HashMap<>(), new HashMap<>(), null));
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        gameController = new GameController(game);
        gameController.setControllerState(gameController.getMapController());
        application = Mockito.mock(Application.class);
    }

    @Test
    public void clickIcon() throws IOException, InterruptedException, URISyntaxException {
        MouseEvent mouseEventMock = Mockito.mock(MouseEvent.class);
        Mockito.when(mouseEventMock.getX()).thenReturn(6*4);
        Mockito.when(mouseEventMock.getY()).thenReturn(6*4);
        gameController.step(application, mouseEventMock,10);
        assertEquals(gameController.getCurrent(),gameController.getLocationController());
        assertEquals(gameController.getModel().getScene(), Game.SCENE.LOCATION);
        assertEquals(game.getLocation("City"),game.getCurrentLocation());
    }

    @Test
    public void clickInvisibleIcon() throws IOException, InterruptedException, URISyntaxException {
        MouseEvent mouseEventMock = Mockito.mock(MouseEvent.class);
        Mockito.when(mouseEventMock.getX()).thenReturn(25*4);
        Mockito.when(mouseEventMock.getY()).thenReturn(25*4);
        gameController.step(application, mouseEventMock,10);
        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
        assertTrue(gameController.getCurrent() instanceof MapController);
    }

    @Test
    public void escape() throws IOException, InterruptedException, URISyntaxException {
        KeyEvent keyEventMock = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEventMock.getKeyCode()).thenReturn(KeyEvent.VK_ESCAPE);
        gameController.step(application, keyEventMock,0);
        assertEquals(Game.SCENE.LOCATION,gameController.getModel().getScene());
        assertTrue(gameController.getCurrent() instanceof LocationController);
    }

//    @Test
//    public void EmptyKeys() throws IOException, InterruptedException {
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//
//        gameController.step(application, new KeyAction("RIGHT"),0);
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//
//        gameController.step(application, new KeyAction("LEFT"),0);
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//
//        gameController.step(application, new KeyAction("UP"),0);
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//
//        gameController.step(application, new KeyAction("DOWN"),0);
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//
//        gameController.step(application, new ClickAction("CLICK", new Position(4,4)),0);
//        assertEquals(gameController.getModel().getScene(), Game.SCENE.MAP);
//        assertTrue(gameController.getCurrent() instanceof MapController);
//    }
}