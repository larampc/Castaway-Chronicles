package castaway_chronicles.model;

import castaway_chronicles.model.mainPage.EndingPageBuilder;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.model.mainPage.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    MainPage mainPage;
    @BeforeEach
    public void init() throws IOException {
        mainPage = new MainPage();
    }

    @Test
    public void MainPageContent() throws IOException {
        assertEquals(MainMenu.class, mainPage.getMainMenu().getClass());
        assertEquals(new EndingPageBuilder().createEndingPage().getClass(), mainPage.getEndingPage().getClass());
        assertEquals("MENU", mainPage.getCurrent().name());
    }

    @Test
    public void current() {
        assertEquals("MENU", mainPage.getCurrent().name());
        mainPage.setCurrent(MainPage.PAGE.ENDINGS);
        assertEquals("ENDINGS", mainPage.getCurrent().name());
        mainPage.setCurrent(MainPage.PAGE.MENU);
        assertEquals("MENU", mainPage.getCurrent().name());
    }
}
