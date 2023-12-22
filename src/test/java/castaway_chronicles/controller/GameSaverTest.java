package castaway_chronicles.controller;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.controller.game.GameSaver;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

public class GameSaverTest {
    private Game gameMock;
    private GameSaver gameSaver;
    private ResourceManager resourceManagerMock;

    @BeforeEach
    void setUp(){
        gameMock = Mockito.mock(Game.class);
        resourceManagerMock = Mockito.mock(ResourceManager.class);
        gameSaver = new GameSaver(gameMock){
            @Override
            public ResourceManager getResourceManager(){
                return resourceManagerMock;
            }
        };
    }
    @Test
    void constructor(){
        Mockito.verify(resourceManagerMock).createResourceDir("Scenes_saved");
    }
    @Test
    void emptySave(){
        gameSaver.emptySave();
        Mockito.verify(resourceManagerMock).deleteResourceFileContent("Scenes_saved");
    }

    @Test
    void saveBackpack(){
        Backpack backpackMock = Mockito.mock(Backpack.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);
        Interactable visibleInteractableMock = Mockito.mock(Interactable.class);
        Interactable invisibleInteractableMock = Mockito.mock(Interactable.class);

        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        Mockito.when(backpackMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("BgName");
        Mockito.when(backgroundMock.getWidth()).thenReturn(100);
        Mockito.when(backgroundMock.getHeight()).thenReturn(200);
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(positionMock.getX()).thenReturn(5);
        Mockito.when(positionMock.getY()).thenReturn(10);
        Mockito.when(backpackMock.getInteractables()).thenReturn(List.of(visibleInteractableMock,invisibleInteractableMock));
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(visibleInteractableMock));
        Mockito.when(invisibleInteractableMock.getName()).thenReturn("InvisibleInteractable");
        Mockito.when(invisibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(invisibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(invisibleInteractableMock.getPosition()).thenReturn(positionMock);
        Mockito.when(visibleInteractableMock.getName()).thenReturn("VisibleInteractable");
        Mockito.when(visibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(visibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(visibleInteractableMock.getPosition()).thenReturn(positionMock);

        gameSaver.saveBackpack();
        String toWrite = "B BgName 5 10 100 200\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Backpack.txt",toWrite);
        toWrite = "I BItem VisibleInteractable 5 10 10 20 V\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Backpack.txt",toWrite);
        toWrite = "I BItem InvisibleInteractable 5 10 10 20\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Backpack.txt",toWrite);
        Mockito.verify(resourceManagerMock,Mockito.times(3)).writeToFile(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    void saveLocations(){
        Location startLocationMock = Mockito.mock(Location.class);
        Location otherLocationMock = Mockito.mock(Location.class);
        Background startBackgroundMock = Mockito.mock(Background.class);
        Background otherBackgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);
        NPC npcMock = Mockito.mock(NPC.class);
        Item itemMock = Mockito.mock(Item.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("start",startLocationMock);
        locations.put("other",otherLocationMock);
        locations.put("another",otherLocationMock);

        Mockito.when(gameMock.getCurrentLocation()).thenReturn(startLocationMock);
        Mockito.when(gameMock.getLocations()).thenReturn(locations);
        Mockito.when(gameMock.getLocation("start")).thenReturn(startLocationMock);
        Mockito.when(gameMock.getLocation("other")).thenReturn(otherLocationMock);
        Mockito.when(gameMock.getLocation("another")).thenReturn(otherLocationMock);
        Mockito.when(startLocationMock.getBackground()).thenReturn(startBackgroundMock);
        Mockito.when(otherLocationMock.getBackground()).thenReturn(otherBackgroundMock);
        Mockito.when(startBackgroundMock.getName()).thenReturn("BgName");
        Mockito.when(startBackgroundMock.getWidth()).thenReturn(100);
        Mockito.when(startBackgroundMock.getHeight()).thenReturn(200);
        Mockito.when(startBackgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(startBackgroundMock.isLoopable()).thenReturn(true);
        Mockito.when(otherBackgroundMock.getName()).thenReturn("BgName");
        Mockito.when(otherBackgroundMock.getWidth()).thenReturn(100);
        Mockito.when(otherBackgroundMock.getHeight()).thenReturn(200);
        Mockito.when(otherBackgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(otherBackgroundMock.isLoopable()).thenReturn(false);
        Mockito.when(positionMock.getX()).thenReturn(5);
        Mockito.when(positionMock.getY()).thenReturn(10);
        Mockito.when(startLocationMock.getInteractables()).thenReturn(List.of(npcMock,itemMock));
        Mockito.when(otherLocationMock.getInteractables()).thenReturn(List.of(npcMock,itemMock));
        Mockito.when(startLocationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));
        Mockito.when(otherLocationMock.getVisibleInteractables()).thenReturn(List.of(itemMock));
        Mockito.when(startLocationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(otherLocationMock.getMainChar()).thenReturn(null);
        Mockito.when(npcMock.getState()).thenReturn(1);
        Mockito.when(npcMock.getName()).thenReturn("npcName");
        Mockito.when(npcMock.getPosition()).thenReturn(positionMock);
        Mockito.when(npcMock.getWidth()).thenReturn(10);
        Mockito.when(npcMock.getHeight()).thenReturn(10);
        Mockito.when(itemMock.getName()).thenReturn("itemName");
        Mockito.when(itemMock.getPosition()).thenReturn(positionMock);
        Mockito.when(itemMock.getWidth()).thenReturn(10);
        Mockito.when(itemMock.getHeight()).thenReturn(10);
        Mockito.when(mainCharMock.getPosition()).thenReturn(positionMock);
        Mockito.when(mainCharMock.getWidth()).thenReturn(10);
        Mockito.when(mainCharMock.getHeight()).thenReturn(10);

        gameSaver.saveLocations();

        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Locations.txt","start");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Locations.txt","other");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Locations.txt","another");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Locations.txt"," S");
        Mockito.verify(resourceManagerMock,Mockito.times(3)).writeToFile("Scenes_saved/Locations.txt","\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/start.txt","B BgName 5 10 100 200 L\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/start.txt","I " + npcMock.getClass().getSimpleName() +
                " npcName 5 10 10 10 1 V\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/start.txt","I " + itemMock.getClass().getSimpleName() +
                " itemName 5 10 10 10\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/start.txt","M 5 10 10 10\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/other.txt","B BgName 5 10 100 200\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/another.txt","B BgName 5 10 100 200\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/other.txt","I " + npcMock.getClass().getSimpleName() +
                " npcName 5 10 10 10 1\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/another.txt","I " + npcMock.getClass().getSimpleName() +
                " npcName 5 10 10 10 1\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/other.txt","I " + itemMock.getClass().getSimpleName() +
                " itemName 5 10 10 10 V\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/another.txt","I " + itemMock.getClass().getSimpleName() +
                " itemName 5 10 10 10 V\n");
        Mockito.verify(resourceManagerMock,Mockito.times(17)).writeToFile(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    void saveMap() {
        Map mapMock = Mockito.mock(Map.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);
        Icon visibleInteractableMock = Mockito.mock(Icon.class);
        Icon invisibleInteractableMock = Mockito.mock(Icon.class);

        Mockito.when(gameMock.getMap()).thenReturn(mapMock);
        Mockito.when(mapMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("BgName");
        Mockito.when(backgroundMock.getWidth()).thenReturn(100);
        Mockito.when(backgroundMock.getHeight()).thenReturn(200);
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(positionMock.getX()).thenReturn(5);
        Mockito.when(positionMock.getY()).thenReturn(10);
        Mockito.when(mapMock.getInteractables()).thenReturn(List.of(visibleInteractableMock,invisibleInteractableMock));
        Mockito.when(mapMock.getVisibleInteractables()).thenReturn(List.of(visibleInteractableMock));
        Mockito.when(invisibleInteractableMock.getName()).thenReturn("InvisibleInteractable");
        Mockito.when(invisibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(invisibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(invisibleInteractableMock.getPosition()).thenReturn(positionMock);
        Mockito.when(visibleInteractableMock.getName()).thenReturn("VisibleInteractable");
        Mockito.when(visibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(visibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(visibleInteractableMock.getPosition()).thenReturn(positionMock);

        gameSaver.saveMap();
        String toWrite = "B BgName 5 10 100 200\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Map.txt",toWrite);
        toWrite = "I "+ visibleInteractableMock.getClass().getSimpleName() + " VisibleInteractable 5 10 10 20 V\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Map.txt",toWrite);
        toWrite = "I " + invisibleInteractableMock.getClass().getSimpleName() + " InvisibleInteractable 5 10 10 20\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Map.txt",toWrite);
        Mockito.verify(resourceManagerMock,Mockito.times(3)).writeToFile(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    void saveGame(){
        Backpack backpackMock = Mockito.mock(Backpack.class);
        Background backgroundMock = Mockito.mock(Background.class);
        Location startLocationMock = Mockito.mock(Location.class);
        Location otherLocationMock = Mockito.mock(Location.class);
        Background startBackgroundMock = Mockito.mock(Background.class);
        Background otherBackgroundMock = Mockito.mock(Background.class);
        Position positionMock = Mockito.mock(Position.class);
        NPC npcMock = Mockito.mock(NPC.class);
        Item itemMock = Mockito.mock(Item.class);
        MainChar mainCharMock = Mockito.mock(MainChar.class);
        Interactable visibleInteractableMock = Mockito.mock(Interactable.class);
        Interactable invisibleInteractableMock = Mockito.mock(Interactable.class);
        Map mapMock = Mockito.mock(Map.class);

        HashMap<String, Location> locations = new HashMap<>();
        locations.put("start",startLocationMock);
        locations.put("other",otherLocationMock);
        locations.put("another",otherLocationMock);

        Mockito.when(gameMock.getMap()).thenReturn(mapMock);
        Mockito.when(mapMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(mapMock.getInteractables()).thenReturn(List.of(visibleInteractableMock,invisibleInteractableMock));
        Mockito.when(mapMock.getVisibleInteractables()).thenReturn(List.of(visibleInteractableMock));
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(startLocationMock);
        Mockito.when(gameMock.getLocations()).thenReturn(locations);
        Mockito.when(gameMock.getLocation("start")).thenReturn(startLocationMock);
        Mockito.when(gameMock.getLocation("other")).thenReturn(otherLocationMock);
        Mockito.when(gameMock.getLocation("another")).thenReturn(otherLocationMock);
        Mockito.when(startLocationMock.getBackground()).thenReturn(startBackgroundMock);
        Mockito.when(otherLocationMock.getBackground()).thenReturn(otherBackgroundMock);
        Mockito.when(startBackgroundMock.getName()).thenReturn("BgName");
        Mockito.when(startBackgroundMock.getWidth()).thenReturn(100);
        Mockito.when(startBackgroundMock.getHeight()).thenReturn(200);
        Mockito.when(startBackgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(startBackgroundMock.isLoopable()).thenReturn(true);
        Mockito.when(otherBackgroundMock.getName()).thenReturn("BgName");
        Mockito.when(otherBackgroundMock.getWidth()).thenReturn(100);
        Mockito.when(otherBackgroundMock.getHeight()).thenReturn(200);
        Mockito.when(otherBackgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(otherBackgroundMock.isLoopable()).thenReturn(false);
        Mockito.when(positionMock.getX()).thenReturn(5);
        Mockito.when(positionMock.getY()).thenReturn(10);
        Mockito.when(startLocationMock.getInteractables()).thenReturn(List.of(npcMock,itemMock));
        Mockito.when(otherLocationMock.getInteractables()).thenReturn(List.of(npcMock,itemMock));
        Mockito.when(startLocationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));
        Mockito.when(otherLocationMock.getVisibleInteractables()).thenReturn(List.of(itemMock));
        Mockito.when(startLocationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(otherLocationMock.getMainChar()).thenReturn(null);
        Mockito.when(npcMock.getState()).thenReturn(1);
        Mockito.when(npcMock.getName()).thenReturn("npcName");
        Mockito.when(npcMock.getPosition()).thenReturn(positionMock);
        Mockito.when(npcMock.getWidth()).thenReturn(10);
        Mockito.when(npcMock.getHeight()).thenReturn(10);
        Mockito.when(itemMock.getName()).thenReturn("itemName");
        Mockito.when(itemMock.getPosition()).thenReturn(positionMock);
        Mockito.when(itemMock.getWidth()).thenReturn(10);
        Mockito.when(itemMock.getHeight()).thenReturn(10);
        Mockito.when(mainCharMock.getPosition()).thenReturn(positionMock);
        Mockito.when(mainCharMock.getWidth()).thenReturn(10);
        Mockito.when(mainCharMock.getHeight()).thenReturn(10);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        Mockito.when(backpackMock.getBackground()).thenReturn(backgroundMock);
        Mockito.when(backgroundMock.getName()).thenReturn("BgName");
        Mockito.when(backgroundMock.getWidth()).thenReturn(100);
        Mockito.when(backgroundMock.getHeight()).thenReturn(200);
        Mockito.when(backgroundMock.getPosition()).thenReturn(positionMock);
        Mockito.when(backpackMock.getInteractables()).thenReturn(List.of(visibleInteractableMock,invisibleInteractableMock));
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(visibleInteractableMock));
        Mockito.when(invisibleInteractableMock.getName()).thenReturn("InvisibleInteractable");
        Mockito.when(invisibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(invisibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(invisibleInteractableMock.getPosition()).thenReturn(positionMock);
        Mockito.when(visibleInteractableMock.getName()).thenReturn("VisibleInteractable");
        Mockito.when(visibleInteractableMock.getWidth()).thenReturn(10);
        Mockito.when(visibleInteractableMock.getHeight()).thenReturn(20);
        Mockito.when(visibleInteractableMock.getPosition()).thenReturn(positionMock);

        gameSaver.saveGame();

        Mockito.verify(resourceManagerMock).deleteResourceFileContent("Scenes_saved");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Backpack.txt","B BgName 5 10 100 200\n");
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Locations.txt","start");
        String toWrite = "I "+ visibleInteractableMock.getClass().getSimpleName() + " VisibleInteractable 5 10 10 20 V\n";
        Mockito.verify(resourceManagerMock).writeToFile("Scenes_saved/Map.txt",toWrite);
    }
}
