package castaway_chronicles.model;

import castaway_chronicles.model.mainPage.EndingItem;
import castaway_chronicles.model.mainPage.EndingPage;
import castaway_chronicles.model.mainPage.EndingPageBuilder;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndingPageBuilderTest {
    private File endings;
    private final List<String> endingsLines = new ArrayList<>();

    @BeforeAll
    void setUpAchieved_endings() throws IOException {
        endings = new File(Path.of("src", "main", "resources", "endings.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(endings, StandardCharsets.UTF_8));
        for (String line; (line = bufferedReader.readLine()) != null; ) {
            endingsLines.add(line);
        }
        try {
            Writer fr = Files.newBufferedWriter(endings.toPath(), UTF_8, CREATE, TRUNCATE_EXISTING);
            fr.write("end1 100 100 10 10\n" + "end2 100 100 10 10\n" +
                    "question0 90 90 10 10 V");
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @AfterAll
    void tearDown() throws IOException {
        Files.write(endings.toPath(), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        Writer writer = Files.newBufferedWriter(Paths.get(endings.getAbsolutePath()));
        for (String line : endingsLines) {
            writer.write(line + '\n');
        }
        writer.close();
    }
    @Test
    void createEndingPage() {
        EndingPage endingPage = EndingPageBuilder.createEndingPage();
        assertEquals("EndingsMenu", endingPage.getBackground().getName());
        assertEquals(3, endingPage.getInteractables().size());
        assertTrue(endingPage.getInteractables().containsAll(
                List.of(new EndingItem(0, 0, 0, 0, "end1"), new EndingItem(0, 0, 0, 0, "question0"),
                        new EndingItem(0,0,0,0,"end2"))));
        assertEquals(1, endingPage.getVisibleInteractables().size());
        assertTrue(endingPage.getVisibleInteractables().contains(
                new EndingItem(0, 0, 0, 0, "question0")));
        assertEquals(2,endingPage.getQuestionItem().size());
        assertTrue(endingPage.getQuestionItem().keySet().containsAll(List.of("end1","end2")));
        assertTrue(endingPage.getQuestionItem().values().containsAll(List.of(0,1)));
    }
}
