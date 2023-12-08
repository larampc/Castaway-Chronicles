package castaway_chronicles.controller;

import castaway_chronicles.controller.game.ControllerStates.BackpackController;
import castaway_chronicles.controller.game.ControllerStates.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HandControllerTest {

    private HandController handController;
    private GameController gameController;
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        gameController = new GameController(game);
        handController = (HandController) gameController.getHandController();
        gameController.setControllerState(handController);
    }

    @Test
    void testClickWithEmptyToGive() throws IOException, InterruptedException {
        ItemBackpack itemBackpack = Mockito.mock(ItemBackpack.class);

        BackpackSelection backpackSelectionMock = mock(BackpackSelection.class);
        when(backpackSelectionMock.getItem()).thenReturn(itemBackpack);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getBackpackSelection()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);


        Location locationMock = mock(Location.class);
        BackpackAnswer backpackAnswer = mock(BackpackAnswer.class);
        when(locationMock.getBackpackAnswer()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        handController.click(new Position(0, 0));

        Mockito.verify(backpackAnswer,times(1)).activate(itemBackpack);
        assertEquals(Game.SCENE.LOCATION, game.getScene());
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }

    @Test
    void testNPCClickWithToGive() throws IOException, InterruptedException {
        ItemBackpack itemBackpackMock = Mockito.mock(ItemBackpack.class);
        when(itemBackpackMock.getEffects()).thenReturn(new ArrayList<>());

        BackpackSelection backpackSelectionMock = mock(BackpackSelection.class);
        when(backpackSelectionMock.getItem()).thenReturn(itemBackpackMock);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getBackpackSelection()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);

        Location locationMock = mock(Location.class);
        BackpackAnswer backpackAnswer = mock(BackpackAnswer.class);
        when(locationMock.getBackpackAnswer()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        NPC npcMock = mock(NPC.class);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        when(npcMock.contains(new Position(0, 0))).thenReturn(true);

        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        handController.setToGive("NPC_NAME");
        handController.click(new Position(0, 0));

        assertEquals(Game.SCENE.LOCATION, game.getScene());
        Mockito.verify(locationMock,times(1)).setDialog("NPC_NAME");
        assertEquals(gameController.getDialogController(), gameController.getCurrent());
    }


    @Test
    void testEmptyClickWithToGive() throws IOException, InterruptedException {
        ItemBackpack itemBackpackMock = Mockito.mock(ItemBackpack.class);

        BackpackSelection backpackSelectionMock = mock(BackpackSelection.class);
        when(backpackSelectionMock.getItem()).thenReturn(itemBackpackMock);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getBackpackSelection()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);

        Location locationMock = mock(Location.class);
        BackpackAnswer backpackAnswer = mock(BackpackAnswer.class);
        when(locationMock.getBackpackAnswer()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        NPC npcMock = mock(NPC.class);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        when(npcMock.contains(new Position(0, 0))).thenReturn(true);

        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        handController.setToGive("NPC_NAME");
        handController.click(new Position(5, 5));

        Mockito.verify(backpackAnswer,times(1)).activate(itemBackpackMock);
        assertEquals(Game.SCENE.LOCATION, game.getScene());
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }


    @Test
    public void escape() {
        handController.escape();
        assertTrue(gameController.getCurrent() instanceof BackpackController);
    }
}
