package castaway_chronicles.model;

import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.mainPage.EndingPage;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndingPageTest {
    private boolean exists;
    private File achieved_endings;
    private final List<String> achieved_endingsLines = new ArrayList<>();

    @BeforeAll
    void setUpAchieved_endings() throws IOException {
        exists = true;
        achieved_endings = null;
        try {
            achieved_endings = new File(Path.of("src", "main", "resources", "achieved_endings.txt").toString());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(achieved_endings, StandardCharsets.UTF_8));
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                achieved_endingsLines.add(line);
            }
        } catch (FileNotFoundException f) {
            exists = false;
        }
        try {
            Writer fr = Files.newBufferedWriter(achieved_endings.toPath(), UTF_8, CREATE, TRUNCATE_EXISTING);
            fr.write("end1\nend2\n");
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @AfterAll
    void tearDown() throws IOException {
        if (exists) {
            Files.write(achieved_endings.toPath(), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            Writer writer = Files.newBufferedWriter(Paths.get(achieved_endings.getAbsolutePath()));
            for (String line : achieved_endingsLines) {
                writer.write(line + '\n');
            }
            writer.close();
        } else {
            new File(Path.of("src", "main", "resources", "achieved_endings.txt").toString()).delete();
        }
    }
    private Background backgroundMock;
    private HashMap<String, Interactable> endings;
    private HashMap<String, Interactable> visibleEndings;
    private HashMap<String,Integer> questionItems;
    private List<Interactable> endingItemsMock;
    @BeforeEach
    void setUp(){
        backgroundMock = Mockito.mock(Background.class);

        endingItemsMock = new ArrayList<>();
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));
        endingItemsMock.add(Mockito.mock(Interactable.class));

        endings = new HashMap<>();
        endings.put("end1", endingItemsMock.get(0));
        endings.put("end2", endingItemsMock.get(1));
        endings.put("end3", endingItemsMock.get(2));
        endings.put("question1", endingItemsMock.get(3));
        endings.put("question2", endingItemsMock.get(4));
        endings.put("question3", endingItemsMock.get(5));
        endings.put("reset", endingItemsMock.get(6));

        visibleEndings = new HashMap<>();
        visibleEndings.put("question1", endingItemsMock.get(3));
        visibleEndings.put("question2", endingItemsMock.get(4));
        visibleEndings.put("question3", endingItemsMock.get(5));
        visibleEndings.put("reset", endingItemsMock.get(6));

        questionItems = new HashMap<>();
        questionItems.put("end1",1);
        questionItems.put("end2",2);
        questionItems.put("end3",3);
    }

    @Test
    void endingPage(){
        EndingPage endingPage = new EndingPage(backgroundMock, endings,visibleEndings, questionItems);

        assertEquals(4,endingPage.getVisibleInteractables().size());
        assertTrue(endingPage.getVisibleInteractables()
                .containsAll(List.of(endingItemsMock.get(0),endingItemsMock.get(1),endingItemsMock.get(5),endingItemsMock.get(6))));
    }

    @Test
    void reset(){
        EndingPage endingPage = new EndingPage(backgroundMock, endings,visibleEndings, questionItems);
        for(int i = 1; i <= 3; i++){
            Mockito.when(endingItemsMock.get(i-1).getName()).thenReturn("end"+i);
        }
        for(int i = 1; i <= 3; i++){
            Mockito.when(endingItemsMock.get(i+2).getName()).thenReturn("question"+i);
        }
        Mockito.when(endingItemsMock.get(6).getName()).thenReturn("reset");

        endingPage.reset();
        assertEquals(4,endingPage.getVisibleInteractables().size());
        assertTrue(endingPage.getVisibleInteractables()
                .containsAll(List.of(endingItemsMock.get(3),endingItemsMock.get(4),endingItemsMock.get(5),endingItemsMock.get(6))));
    }
}
