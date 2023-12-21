package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.MoveCommand;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.gameElements.Icon;
import castaway_chronicles.model.game.gameElements.MainChar;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class MoveCommandTest {
    private Location locationMock;
    private MainChar mainCharMock;
    private Background backgroundMock;
    private Position backgroundPositionMock;
    private Interactable interactableMock;
    private Position interactablePositionMock;

    @BeforeEach
    void setUp() {
        locationMock = Mockito.mock(Location.class);
        mainCharMock = Mockito.mock(MainChar.class);
        backgroundMock = Mockito.mock(Background.class);
        backgroundPositionMock = Mockito.mock(Position.class);
        interactableMock = Mockito.mock(Interactable.class);
        interactablePositionMock = Mockito.mock(Position.class);

        Mockito.when(locationMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(backgroundMock.getPosition()).thenReturn(backgroundPositionMock);
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
    }

    @Test
    void moveRightCommandNotLoopableLocation() throws IOException {
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(interactablePositionMock.getRight(-10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk2_right");
        Mockito.verify(backgroundMock).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock).setPosition(new Position(90,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_right");
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk1_right");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));
    }
    @Test
    void moveLeftCommandNotLoopableLocation() throws IOException {
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        Mockito.when(backgroundPositionMock.getRight(10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand1 = new MoveCommand(locationMock, 0);
        moveCommand1.execute();

        Mockito.verify(mainCharMock).setName("walk2_left");

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock,Mockito.times(2)).setName("walk2_left");
        Mockito.verify(backgroundMock).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock).setPosition(new Position(90,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk1_left");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));
    }
    @Test
    void moveRightCommandLoopableLocation() throws IOException {
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(true);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(-100);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(-10)).thenReturn(new Position(90,100));

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk2_right");
        Mockito.verify(backgroundMock).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock).setPosition(new Position(90,100));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_right");
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk1_right");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(-110,0));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(90,100));
    }
    @Test
    void moveLeftCommandLoopableLocation() throws IOException {
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(true);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(-120);
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(backgroundPositionMock.getRight(10)).thenReturn(new Position(1,1));
        Mockito.when(interactablePositionMock.getRight(10)).thenReturn(new Position(2,2));

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk2_left");
        Mockito.verify(backgroundMock).setPosition(new Position(1,1));
        Mockito.verify(interactableMock).setPosition(new Position(2,2));

        Mockito.when(mainCharMock.getName()).thenReturn("walk4_left");
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk1_left");
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(1,1));
        Mockito.verify(interactableMock,Mockito.times(2)).setPosition(new Position(2,2));
    }

    @Test
    void moveCommandLoopableLocationAtBorder() throws IOException {
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_left");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(true);
        Mockito.when(backgroundMock.getWidth()).thenReturn(700);
        Mockito.when(backgroundPositionMock.getX()).thenReturn(-9);
        Mockito.when(backgroundPositionMock.getRight(Mockito.anyInt())).thenReturn(new Position(2,2));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.getPosition()).thenReturn(interactablePositionMock);
        Mockito.when(interactablePositionMock.getRight(-490)).thenReturn(new Position(0,0));
        Mockito.when(interactablePositionMock.getRight(490)).thenReturn(new Position(1,1));

        MoveCommand moveCommand = new MoveCommand(locationMock, 10);
        moveCommand.execute();

        Mockito.verify(mainCharMock).setName("walk2_left");
        Mockito.verify(backgroundMock).setPosition(new Position(-490,0));
        Mockito.verify(interactableMock).setPosition(new Position(0,0));

        moveCommand = new MoveCommand(locationMock, 9);
        moveCommand.execute();
        Mockito.verify(backgroundMock).setPosition(new Position(2,2));
        Mockito.verify(interactableMock).setPosition(new Position(0,0));

        Mockito.when(backgroundPositionMock.getX()).thenReturn(-491);

        moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(backgroundMock).setPosition(new Position(-10,0));
        Mockito.verify(interactableMock).setPosition(new Position(1,1));

        moveCommand = new MoveCommand(locationMock, -9);
        moveCommand.execute();
        Mockito.verify(backgroundMock,Mockito.times(2)).setPosition(new Position(2,2));
    }
    @Test
    void moveCommand_MainIconsDontMove() throws IOException {
        Icon backpackMock = Mockito.mock(Icon.class);
        Icon mapMock = Mockito.mock(Icon.class);
        Icon iconMock = Mockito.mock(Icon.class);
        Position iconPositionMock = Mockito.mock(Position.class);

        Mockito.when(backpackMock.getName()).thenReturn("Backpack_icon");
        Mockito.when(mapMock.getName()).thenReturn("Map_icon");
        Mockito.when(iconMock.getName()).thenReturn("Icon");
        Mockito.when(mainCharMock.getName()).thenReturn("walk1_right");
        Mockito.when(backgroundMock.isLoopable()).thenReturn(false);
        Mockito.when(backgroundPositionMock.getRight(-10)).thenReturn(new Position(-110,0));
        Mockito.when(locationMock.getInteractables()).thenReturn(List.of(backpackMock,mapMock,iconMock));
        Mockito.when(iconMock.getPosition()).thenReturn(iconPositionMock);
        Mockito.when(iconPositionMock.getRight(-10)).thenReturn(new Position(1,1));

        MoveCommand moveCommand = new MoveCommand(locationMock, -10);
        moveCommand.execute();

        Mockito.verify(backpackMock,Mockito.never()).setPosition(Mockito.any(Position.class));
        Mockito.verify(mapMock,Mockito.never()).setPosition(Mockito.any(Position.class));
        Mockito.verify(iconMock).setPosition(new Position(1,1));
    }
}
