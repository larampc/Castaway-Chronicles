package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.mainPage.EndingPageController;
import castaway_chronicles.controller.mainPage.MainPageController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.EndingItem;
import castaway_chronicles.model.mainPage.EndingPage;
import castaway_chronicles.model.mainPage.MainPage;

import castaway_chronicles.states.EndState;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndingPageControllerTest {
    private Application applicationMock;
    private MainPage mainPageMock;
    private MainPageController mainPageController;
    private EndingPageController endingPageController;
    private boolean exists;
    private File endings;
    private final List<String> lines = new ArrayList<>();

    @BeforeAll
    void init() throws IOException {
        exists = true;
        endings = null;
        try {
            endings = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/achieved_endings.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(endings, StandardCharsets.UTF_8));
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                lines.add(line);
            }
        }
        catch (FileNotFoundException f) {
            exists = false;
        }
    }

    @AfterAll
    void tearDown() throws IOException {
        if(exists) {
            Writer writer = Files.newBufferedWriter(Paths.get(endings.getAbsolutePath()));
            for (String line : lines) {
                writer.write(line + '\n');
            }
            writer.close();
        }
        else {
            new File(Path.of("src","main","resources","achieved_endings.txt").toString()).delete();
        }
    }

    @BeforeEach
    void setUp(){
        applicationMock = Mockito.mock(Application.class);
        mainPageMock = Mockito.mock(MainPage.class);
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.ENDINGS);
        mainPageController = new MainPageController(mainPageMock);
        endingPageController = (EndingPageController) mainPageController.getEndingPageController();
    }

    @Test
    void escape() throws IOException, URISyntaxException, InterruptedException {
        endingPageController.key(KeyEvent.VK_ESCAPE,applicationMock);

        Mockito.verify(mainPageMock).setCurrent(MainPage.PAGE.MENU);
        assertEquals(mainPageController.getCurrent(), mainPageController.getMainMenuController());
    }

    @Test
    void clickReset() throws IOException, URISyntaxException, InterruptedException {
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        EndingItem resetMock = Mockito.mock(EndingItem.class);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(mainPageMock.getEndingPage()).thenReturn(endingPageMock);
        Mockito.when(endingPageMock.getVisibleInteractables()).thenReturn(List.of(resetMock));
        Mockito.when(resetMock.getName()).thenReturn("reset");
        Mockito.when(resetMock.contains(positionMock)).thenReturn(true);

        endingPageController.click(positionMock, applicationMock);

        Mockito.verify(endingPageMock).reset();
    }
    @Test
    void clickEnding() throws IOException, URISyntaxException, InterruptedException {
        EndingPage endingPageMock = Mockito.mock(EndingPage.class);
        EndingItem endingMock = Mockito.mock(EndingItem.class);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(mainPageMock.getEndingPage()).thenReturn(endingPageMock);
        Mockito.when(endingPageMock.getVisibleInteractables()).thenReturn(List.of(endingMock));
        Mockito.when(endingMock.getName()).thenReturn("TestEnd");
        Mockito.when(endingMock.contains(positionMock)).thenReturn(true);

        endingPageController.click(positionMock, applicationMock);

        Mockito.verify(applicationMock).setState(Mockito.any(EndState.class));
    }
}
