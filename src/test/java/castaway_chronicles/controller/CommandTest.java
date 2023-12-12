package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.game.Commands.ChangeSceneCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.*;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    private Game game;
    GameController gameController;
    private Application applicationMock;


    @BeforeEach
    void setUp() {
        game = new Game();
        applicationMock = Mockito.mock(Application.class);
    }

    @Test
    void changeLocation() {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        Location newLocation = Mockito.mock(Location.class);
        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        locations.put("City",newLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");
        gameController = new GameController(game);

        assertEquals(startLocation, game.getCurrentLocation());

        ChangeLocationCommand changeLocationCommand = new ChangeLocationCommand(game,"City");

        changeLocationCommand.execute();

        assertEquals(newLocation, game.getCurrentLocation());
    }

    @Test
    void changeScene() {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");
        Backpack backpack = Mockito.mock(Backpack.class);
        game.setBackpack(backpack);
        gameController = new GameController(game);

        assertEquals(Game.SCENE.LOCATION, game.getScene());

        ChangeSceneCommand changeSceneCommand = new ChangeSceneCommand(game,"BACKPACK");
        changeSceneCommand.execute();

        assertEquals(Game.SCENE.BACKPACK, game.getScene());
    }

//    @Test
//    void executeEndEffect() throws IOException, InterruptedException, URISyntaxException {
//        game.setCurrentScene("LOCATION");
//
//        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(game, List.of("END TestEnd"));
//
//        assertEquals(Game.SCENE.LOCATION, game.getScene());
//
//        handleEffectsCommand.execute();
//
//        assertEquals(Game.SCENE.END, game.getScene());
//        assertEquals("TestEnd", game.getEnd().getName());
//    }

    @Test
    void executeNPCEffect() throws IOException, InterruptedException, URISyntaxException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(startLocation.getInteractable("TestNPC")).thenReturn(npcMock);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(game, List.of("NPC TestNPC 1"), applicationMock);

        handleEffectsCommand.execute();

        Mockito.verify(npcMock,Mockito.times(1)).goToState(1);
        Mockito.verify(startLocation,Mockito.times(1)).setTextDisplay("TestNPC");
    }

    @Test
    void executeMapEffect() throws IOException, InterruptedException, URISyntaxException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        Map mapMock = Mockito.mock(Map.class);

        game.setMap(mapMock);
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(game, List.of("MAP TestIcon I","MAP TestIcon2 V"), applicationMock);

        handleEffectsCommand.execute();

        Mockito.verify(mapMock,Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(mapMock,Mockito.times(1)).setVisible("TestIcon2");
    }

    @Test
    void executeBackPackEffect() throws IOException, InterruptedException, URISyntaxException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        Backpack backpack = Mockito.mock(Backpack.class);

        game.setBackpack(backpack);
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(game, List.of("BACKPACK TestIcon I","backpack TestIcon2 V"), applicationMock);

        handleEffectsCommand.execute();

        Mockito.verify(backpack,Mockito.times(1)).setInvisible("TestIcon_backpack");
        Mockito.verify(backpack,Mockito.times(1)).setVisible("TestIcon2_backpack");
    }

    @Test
    void executeLocationEffect() throws IOException, InterruptedException, URISyntaxException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);
        Location city = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        locations.put("City",city);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        Backpack backpack = Mockito.mock(Backpack.class);

        game.setBackpack(backpack);
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(game, List.of("City TestIcon I","Beach TestItem V","Beach TestNPC V"), applicationMock);

        handleEffectsCommand.execute();

        Mockito.verify(city,Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(startLocation,Mockito.times(1)).setVisible("TestItem");
        Mockito.verify(startLocation,Mockito.times(1)).setVisible("TestNPC");

    }

    @Test
    void moveRightCommand() throws IOException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);

        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
        Mockito.when(interactableMock.getPosition()).thenReturn(new Position(100,100));

        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");

        MoveCommand moveCommand = new MoveCommand(startLocation, -10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_right");
        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(new Position(90,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_right");
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_right");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));


    }
    @Test
    void moveLeftCommand() throws IOException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);

        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
        Mockito.when(interactableMock.getPosition()).thenReturn(new Position(100,100));

        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");

        MoveCommand moveCommand = new MoveCommand(startLocation, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_left");
        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-90,0));
        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(new Position(110,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_left");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-90,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(110,100));
    }

    @Test
    void moveCommand_MainIconsDontMove() throws IOException {
        game.setCurrentScene("LOCATION");
        Location startLocation = Mockito.mock(Location.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("Beach",startLocation);
        game.setLocations(locations);
        game.setCurrentLocation("Beach");

        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Icon backpackMock = Mockito.mock(Icon.class);
        Icon mapMock = Mockito.mock(Icon.class);
        Icon iconMock = Mockito.mock(Icon.class);

        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backpackMock.getName()).thenReturn("Backpack_icon");
        Mockito.when(mapMock.getName()).thenReturn("Map_icon");
        Mockito.when(iconMock.getName()).thenReturn("Icon");
        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
        Mockito.when(backpackMock.getPosition()).thenReturn(new Position(100,100));
        Mockito.when(mapMock.getPosition()).thenReturn(new Position(120,100));
        Mockito.when(iconMock.getPosition()).thenReturn(new Position(140,100));

        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(backpackMock,mapMock,iconMock));
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");

        MoveCommand moveCommand = new MoveCommand(startLocation, 10);
        moveCommand.execute();

        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-90,0));
        Mockito.verify(backpackMock,Mockito.never()).setPosition(new Position(110,100));
        Mockito.verify(mapMock,Mockito.never()).setPosition(new Position(130,100));
        Mockito.verify(iconMock,Mockito.times(1)).setPosition(new Position(150,100));
    }

}
