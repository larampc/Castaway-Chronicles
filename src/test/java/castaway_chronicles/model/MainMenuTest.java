package castaway_chronicles.model;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.mainPage.MainMenu;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainMenuTest {
    MainMenu mainMenu;
    private File scenes_savedStorage;
    private Path scenes_savedPath;
//    @BeforeAll
//    void setUpScenes_Saved() {
//        Path storagePath = Path.of("src", "main", "resources", "Scenes_savedStorage");
//        scenes_savedPath = Path.of("src", "main", "resources","Scenes_saved");
//        scenes_savedStorage = new File(storagePath.toString());
//        scenes_savedStorage.mkdir();
//
//        File scenes_saved = new File(scenes_savedPath.toString());
//        for(File file : Objects.requireNonNull(scenes_saved.listFiles())){
//            File copy = new File(Path.of(storagePath.toString(),file.getName()).toString());
//            try {
//                copy.createNewFile();
//                InputStream is = new FileInputStream(file);
//                OutputStream os = new FileOutputStream(copy);
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = is.read(buffer)) > 0) {
//                    os.write(buffer, 0, length);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            file.delete();
//        }
//    }
//
//    @AfterAll
//    void tearDown() throws IOException {
//        for(File file : Objects.requireNonNull(scenes_savedStorage.listFiles())){
//            File copy = new File(Path.of(scenes_savedPath.toString(), file.getName()).toString());
//            copy.createNewFile();
//            try (InputStream is = new FileInputStream(file); OutputStream os = new FileOutputStream(copy)) {
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = is.read(buffer)) > 0) {
//                    os.write(buffer, 0, length);
//                }
//            }
//            file.delete();
//        }
//        scenes_savedStorage.delete();
//    }
    @BeforeEach
    void init(){
        mainMenu = new MainMenu();
    }

    @Test
    public void getEntry(){
        assertEquals("Continue", mainMenu.getSelectionPanel().getEntry(2));
        assertEquals("Endings", mainMenu.getSelectionPanel().getEntry(3));
        assertEquals("Start", mainMenu.getSelectionPanel().getEntry(0));
        assertEquals("Exit", mainMenu.getSelectionPanel().getEntry(1));
    }

    @Test
    void getCurrentEntry(){
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

    @Test
    void getBackground(){
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getName(), mainMenu.getBackground().getName());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getPosition(), mainMenu.getBackground().getPosition());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getHeight(), mainMenu.getBackground().getHeight());
        assertEquals(new Background(0, 0, 200, 150, "Menu", false).getWidth(), mainMenu.getBackground().getWidth());
    }

    @Test
    void getEntries(){
        List<String> entries = Arrays.asList("Start", "Exit", "Continue", "Endings");
        assertEquals(entries, mainMenu.getSelectionPanel().getEntries());
    }

    @Test
    void isSelected(){
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
    }

    @Test
    void isSelectedExit(){
        assertFalse(mainMenu.isSelectedExit());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedExit());
    }

    @Test
    void isSelectedStart(){
        assertTrue(mainMenu.isSelectedStart());
        mainMenu.getSelectionPanel().nextEntry();
        assertFalse(mainMenu.isSelectedStart());
    }

    @Test
    void isSelectedContinue(){
        assertFalse(mainMenu.isSelectedContinue());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedContinue());
    }

    @Test
    void isSelectedEndings(){
        assertFalse(mainMenu.isSelectedEndings());
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.isSelectedEndings());
    }

    @Test
    void getNumberEntries(){
        assertEquals(4, mainMenu.getSelectionPanel().getNumberEntries());
    }
    @Test
    void nextEntry(){
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(1));
        assertFalse(mainMenu.getSelectionPanel().isSelected(0));
        assertEquals(1, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
        assertEquals(2, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(3));
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertEquals(3, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().nextEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

    @Test
    void previousEntry(){
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(3));
        assertFalse(mainMenu.getSelectionPanel().isSelected(0));
        assertEquals(3, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(2));
        assertFalse(mainMenu.getSelectionPanel().isSelected(3));
        assertEquals(2, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(1));
        assertFalse(mainMenu.getSelectionPanel().isSelected(2));
        assertEquals(1, mainMenu.getSelectionPanel().getCurrentEntry());
        mainMenu.getSelectionPanel().previousEntry();
        assertTrue(mainMenu.getSelectionPanel().isSelected(0));
        assertFalse(mainMenu.getSelectionPanel().isSelected(1));
        assertEquals(0, mainMenu.getSelectionPanel().getCurrentEntry());
    }

//    @Test
//    void cantContinueNotExistsScenesSaved() {
//        new File(scenes_savedPath.toString()).delete();
//        assertFalse(mainMenu.canContinue());
//        new File(scenes_savedPath.toString()).mkdir();
//    }
//    @Test
//    void cantContinueButExistsScenesSaved(){
//        assertFalse(mainMenu.canContinue());
//    }
//    @Test
//    void canContinue(){
//        File backpack = new File(Path.of(scenes_savedPath.toString(), "Backpack").toString());
//        try {
//            Writer fr = Files.newBufferedWriter(backpack.toPath(), UTF_8, CREATE, TRUNCATE_EXISTING);
//            fr.write("Backpack info");
//            fr.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        assertTrue(mainMenu.canContinue());
//        boolean deleted = backpack.delete();
//        assertTrue(deleted);
//    }
}
