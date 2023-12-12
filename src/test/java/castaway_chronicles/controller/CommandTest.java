package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.game.Commands.ChangeSceneCommand;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.*;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.TextDisplay;
import castaway_chronicles.states.EndState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CommandTest {
    private Game gameMock;
    GameController gameController;
    private Application applicationMock;


    @BeforeEach
    void setUp() {
        gameMock = Mockito.mock(Game.class);
        applicationMock = Mockito.mock(Application.class);
    }

    @Test
    void changeLocation() {
        ChangeLocationCommand changeLocationCommand = new ChangeLocationCommand(gameMock,"City");
        changeLocationCommand.execute();
        Mockito.verify(gameMock).setCurrentLocation("City");
    }

    @Test
    void changeScene() {
        ChangeSceneCommand changeSceneCommand = new ChangeSceneCommand(gameMock,"BACKPACK");
        changeSceneCommand.execute();

        Mockito.verify(gameMock).setCurrentScene("BACKPACK");
    }

    @Test
    void executeGoEffect() throws IOException, InterruptedException, URISyntaxException {
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("GO TestLocation"),applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(gameMock).setCurrentLocation("TestLocation");
    }
    @Test
    void executeEndEffect() throws IOException, InterruptedException, URISyntaxException {
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("END TestEnd"),applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(applicationMock).setState(Mockito.any(EndState.class));
    }

    @Test
    void executeNPCEffectNoWait() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        NPC NPCMock = Mockito.mock(NPC.class);
        NPC NPCMock2 = Mockito.mock(NPC.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(currentLocationMock.getInteractable("TestNPC")).thenReturn(NPCMock);
        Mockito.when(currentLocationMock.getInteractable("TestNPC2")).thenReturn(NPCMock2);
        Mockito.when(currentLocationMock.getTextDisplay()).thenReturn(textDisplayMock);
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("NPC TestNPC 1","NPC TestNPC2 2 W"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(NPCMock).goToState(1);
        Mockito.verify(currentLocationMock).setTextDisplay("TestNPC");
        Mockito.verify(NPCMock2).goToState(2);
        Mockito.verify(textDisplayMock).closeTextBox();
    }

    @Test
    void executeMapEffect() throws IOException, InterruptedException, URISyntaxException {
        Map mapMock = Mockito.mock(Map.class);
        Mockito.when(gameMock.getMap()).thenReturn(mapMock);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("MAP TestIcon I","MAP TestIcon2 V"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(mapMock,Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(mapMock,Mockito.times(1)).setVisible("TestIcon2");
    }

    @Test
    void executeBackPackEffect() throws IOException, InterruptedException, URISyntaxException {
        Backpack backpackMock = Mockito.mock(Backpack.class);
        ItemBackpack itemBackpackMock = Mockito.mock(ItemBackpack.class);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        Mockito.when(backpackMock.getInteractable("TestIcon3_backpack")).thenReturn(itemBackpackMock);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("BACKPACK TestIcon I","backpack TestIcon2 V","backpack TestIcon3 NewTestIcon3"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(backpackMock,Mockito.times(1)).setInvisible("TestIcon_backpack");
        Mockito.verify(backpackMock,Mockito.times(1)).setVisible("TestIcon2_backpack");
        Mockito.verify(itemBackpackMock).setNameBackpack("NewTestIcon3_backpack");
    }

    @Test
    void executeLocationEffect() throws IOException, InterruptedException, URISyntaxException {
        Location Beach = Mockito.mock(Location.class);
        Location City = Mockito.mock(Location.class);
        Mockito.when(gameMock.getLocation("City")).thenReturn(City);
        Mockito.when(gameMock.getLocation("Beach")).thenReturn(Beach);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("City TestIcon I","Beach TestItem V","Beach TestNPC V"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(City,Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(Beach,Mockito.times(1)).setVisible("TestItem");
        Mockito.verify(Beach,Mockito.times(1)).setVisible("TestNPC");
    }

//    @Test
//    void moveRightCommand() throws IOException {
//        game.setCurrentScene("LOCATION");
//        Location startLocation = Mockito.mock(Location.class);
//
//        HashMap<String, Location> locations = new HashMap<>();
//        locations.put("Beach",startLocation);
//        game.setLocations(locations);
//        game.setCurrentLocation("Beach");
//
//        MainChar mainCharMock = Mockito.mock(MainChar.class);
//        Background backgroundMock = Mockito.mock(Background.class);
//        Interactable interactableMock = Mockito.mock(Interactable.class);
//
//        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
//        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
//        Mockito.when(interactableMock.getPosition()).thenReturn(new Position(100,100));
//
//        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
//        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(interactableMock));
//        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
//
//        MoveCommand moveCommand = new MoveCommand(startLocation, -10);
//        moveCommand.execute();
//
//        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_right");
//        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-110,0));
//        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(new Position(90,100));
//
//        Mockito.when(mainCharMock.getName()).thenReturn("walk4_right");
//        moveCommand.execute();
//
//        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_right");
//        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
//        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));
//
//
//    }
//    @Test
//    void moveLeftCommand() throws IOException {
//        game.setCurrentScene("LOCATION");
//        Location startLocation = Mockito.mock(Location.class);
//
//        HashMap<String, Location> locations = new HashMap<>();
//        locations.put("Beach",startLocation);
//        game.setLocations(locations);
//        game.setCurrentLocation("Beach");
//
//        MainChar mainCharMock = Mockito.mock(MainChar.class);
//        Background backgroundMock = Mockito.mock(Background.class);
//        Interactable interactableMock = Mockito.mock(Interactable.class);
//
//        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
//        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
//        Mockito.when(interactableMock.getPosition()).thenReturn(new Position(100,100));
//
//        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
//        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(interactableMock));
//        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
//
//        MoveCommand moveCommand = new MoveCommand(startLocation, 10);
//        moveCommand.execute();
//
//        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_left");
//        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-90,0));
//        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(new Position(110,100));
//
//        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
//        moveCommand.execute();
//
//        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_left");
//        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-90,0));
//        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(110,100));
//    }
//
//    @Test
//    void moveCommand_MainIconsDontMove() throws IOException {
//        game.setCurrentScene("LOCATION");
//        Location startLocation = Mockito.mock(Location.class);
//
//        HashMap<String, Location> locations = new HashMap<>();
//        locations.put("Beach",startLocation);
//        game.setLocations(locations);
//        game.setCurrentLocation("Beach");
//
//        MainChar mainCharMock = Mockito.mock(MainChar.class);
//        Background backgroundMock = Mockito.mock(Background.class);
//        Icon backpackMock = Mockito.mock(Icon.class);
//        Icon mapMock = Mockito.mock(Icon.class);
//        Icon iconMock = Mockito.mock(Icon.class);
//
//        Mockito.when(startLocation.getBackground()).thenReturn(backgroundMock);
//        Mockito.when(backpackMock.getName()).thenReturn("Backpack_icon");
//        Mockito.when(mapMock.getName()).thenReturn("Map_icon");
//        Mockito.when(iconMock.getName()).thenReturn("Icon");
//        Mockito.when(backgroundMock.getPosition()).thenReturn(new Position(-100,0));
//        Mockito.when(backpackMock.getPosition()).thenReturn(new Position(100,100));
//        Mockito.when(mapMock.getPosition()).thenReturn(new Position(120,100));
//        Mockito.when(iconMock.getPosition()).thenReturn(new Position(140,100));
//
//        Mockito.when(startLocation.getMainChar()).thenReturn(mainCharMock);
//        Mockito.when(startLocation.getInteractables()).thenReturn(List.of(backpackMock,mapMock,iconMock));
//        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
//
//        MoveCommand moveCommand = new MoveCommand(startLocation, 10);
//        moveCommand.execute();
//
//        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-90,0));
//        Mockito.verify(backpackMock,Mockito.never()).setPosition(new Position(110,100));
//        Mockito.verify(mapMock,Mockito.never()).setPosition(new Position(130,100));
//        Mockito.verify(iconMock,Mockito.times(1)).setPosition(new Position(150,100));
//    }

}
