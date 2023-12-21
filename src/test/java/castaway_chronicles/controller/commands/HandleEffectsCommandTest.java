package castaway_chronicles.controller.commands;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.TextBox;
import castaway_chronicles.states.EndState;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HandleEffectsCommandTest {
    private Game gameMock;
    private Application applicationMock;
    private boolean exists;
    private File endings;
    private final List<String> lines = new ArrayList<>();

    @BeforeAll
    void init() throws IOException {
        exists = true;
        endings = null;
        try {
            endings = new File(Path.of("src", "main", "resources", "achieved_endings.txt").toString());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(endings, StandardCharsets.UTF_8));
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                lines.add(line);
            }
        } catch (FileNotFoundException f) {
            exists = false;
        }
    }

    @AfterAll
    void tearDown() throws IOException {
        if (exists) {
            Writer writer = Files.newBufferedWriter(Paths.get(endings.getAbsolutePath()));
            for (String line : lines) {
                writer.write(line + '\n');
            }
            writer.close();
        } else {
            new File(Path.of("src", "main", "resources", "achieved_endings.txt").toString()).delete();
        }
    }

    @BeforeEach
    void setUp() {
        gameMock = Mockito.mock(Game.class);
        applicationMock = Mockito.mock(Application.class);
    }

    @Test
    void executeGoEffect() {
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("GO TestLocation"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(gameMock).setCurrentLocation("TestLocation");
    }

//    @Test
//    void executeEndEffect() {
//        //set up
//
//        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("END drink"), applicationMock);
//        handleEffectsCommand.execute();
////        assertNull();
//        Mockito.verify(applicationMock).setState(Mockito.any(EndState.class));
//
//        //tear down
//
//    }

    @Test
    void executeNPCEffectNoWait() {
        Location currentLocationMock = Mockito.mock(Location.class);
        Game gameMock = Mockito.mock(Game.class);
        NPC NPCMock = Mockito.mock(NPC.class);
        NPC NPCMock2 = Mockito.mock(NPC.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(currentLocationMock.getInteractable("TestNPC")).thenReturn(NPCMock);
        Mockito.when(currentLocationMock.getInteractable("TestNPC2")).thenReturn(NPCMock2);
        Mockito.when(gameMock.getTextBox()).thenReturn(textBoxMock);
        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("NPC TestNPC 1", "NPC TestNPC2 2 W"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(NPCMock).goToState(1);
        Mockito.verify(gameMock).setTextBox(NPCMock);
        Mockito.verify(NPCMock2).goToState(2);
        Mockito.verify(textBoxMock).closeTextBox();
    }

    @Test
    void executeMapEffect() {
        Map mapMock = Mockito.mock(Map.class);
        Mockito.when(gameMock.getMap()).thenReturn(mapMock);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("MAP TestIcon I", "MAP TestIcon2 V"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(mapMock, Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(mapMock, Mockito.times(1)).setVisible("TestIcon2");
    }

    @Test
    void executeBackPackEffect() {
        Backpack backpackMock = Mockito.mock(Backpack.class);
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        Mockito.when(backpackMock.getInteractable("TestIcon3_backpack")).thenReturn(backpackItemMock);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("BACKPACK TestIcon I", "backpack TestIcon2 V", "backpack TestIcon3 NewTestIcon3"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(backpackMock, Mockito.times(1)).setInvisible("TestIcon_backpack");
        Mockito.verify(backpackMock, Mockito.times(1)).setVisible("TestIcon2_backpack");
        Mockito.verify(backpackItemMock).setNameBackpack("NewTestIcon3_backpack");
    }

    @Test
    void executeLocationEffect() {
        Location Beach = Mockito.mock(Location.class);
        Location City = Mockito.mock(Location.class);
        Mockito.when(gameMock.getLocation("City")).thenReturn(City);
        Mockito.when(gameMock.getLocation("Beach")).thenReturn(Beach);

        HandleEffectsCommand handleEffectsCommand = new HandleEffectsCommand(gameMock, List.of("City TestIcon I", "Beach TestItem V", "Beach TestNPC V"), applicationMock);
        handleEffectsCommand.execute();

        Mockito.verify(City, Mockito.times(1)).setInvisible("TestIcon");
        Mockito.verify(Beach, Mockito.times(1)).setVisible("TestItem");
        Mockito.verify(Beach, Mockito.times(1)).setVisible("TestNPC");
    }
}