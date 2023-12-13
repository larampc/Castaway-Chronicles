package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.*;
import castaway_chronicles.model.Position;
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

    @Test
    void moveRightCommandNotLoopableLocation() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(false);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(-10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
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
    void moveLeftCommandNotLoopableLocation() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(false);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundPositionMock.getRight(10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_left");
        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(new Position(90,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_left");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));
    }
    @Test
    void moveRightCommandLoopableLocation() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(true);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(-100);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(-10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
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
    void moveLeftCommandLoopableLocation() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(true);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(-120);
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_left");
        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(backgroundPositionMock.getRight(10));
        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(interactablePositionMock.getRight(10));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk1_left");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(backgroundPositionMock.getRight(10));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(interactablePositionMock.getRight(10));
    }

    @Test
    void moveCommandLoopableLocationAtBorder() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(true);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(0);
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(1)).setName("walk2_left");
        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-490,0));
        Mockito.verify(interactableMock,Mockito.times(1)).setPosition(interactablePositionMock.getRight(-490));

        Mockito.when(backgroundPositionMock.getX()).thenReturn(-500);

        moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(backgroundMock,Mockito.times(1)).setPosition(new Position(-10,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(interactablePositionMock.getRight(-510));
    }
    @Test
    void moveCommand_MainIconsDontMove() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position backgroundPositionMock = Mockito.mock(Position.class);
        Icon backpackMock = Mockito.mock(Icon.class);
        Icon mapMock = Mockito.mock(Icon.class);
        Icon iconMock = Mockito.mock(Icon.class);
        Position iconPositionMock = Mockito.mock(Position.class);

        Mockito.when(backpackMock.getName()).thenReturn("Backpack_icon");
        Mockito.when(mapMock.getName()).thenReturn("Map_icon");
        Mockito.when(iconMock.getName()).thenReturn("Icon");
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.isIsloopable()).thenReturn(false);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(backpackMock,mapMock,iconMock));
        Mockito.when(iconMock.getPosition()).thenReturn(iconPositionMock);

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(backpackMock,Mockito.never()).setPosition(Mockito.any(Position.class));
        Mockito.verify(mapMock,Mockito.never()).setPosition(Mockito.any(Position.class));
        Mockito.verify(iconMock,Mockito.times(1)).setPosition(iconPositionMock.getRight(-10));
    }

    @Test
    void pickUpCommand() {
        Location currentLocationMock = Mockito.mock(Location.class);
        Backpack backpackMock = Mockito.mock(Backpack.class);

        Mockito.when(gameMock.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);

        PickUpCommand pickUpCommand = new PickUpCommand(gameMock,"Test");
        pickUpCommand.execute();

        Mockito.verify(currentLocationMock).setInvisible("Test");
        Mockito.verify(backpackMock).setVisible("Test_backpack");
    }

    @Test
    void StartTalkCommand() {
        Location locationMock = Mockito.mock(Location.class);

        new StartTalkCommand(locationMock,"Test").execute();

        Mockito.verify(locationMock).setTextDisplay("Test");
    }

    @Test
    void AnswerCommand() throws IOException {
        Location locationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC NPCMock = Mockito.mock(NPC.class);
        Mockito.when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getElement()).thenReturn(NPCMock);
        new AnswerCommand(locationMock).execute();

        Mockito.verify(NPCMock).goToStateChoice();
        Mockito.verify(textDisplayMock).setActiveChoice(false);
    }

}
