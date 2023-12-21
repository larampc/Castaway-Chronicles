package castaway_chronicles.view.mainPage;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.view.MenuViewer;
import castaway_chronicles.view.SceneViewer;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainPageViewer extends Viewer<MainPage> {
    private final SceneViewer endingPageViewer;
    private final MenuViewer mainMenuViewer;
    public MainPageViewer(MainPage model) {
        super(model);
        endingPageViewer = new SceneViewer();
        mainMenuViewer = new MenuViewer();
        getMainMenuViewer().getSelectionPanelViewer().setDefinitions(42,20, new Position(98,101));
    }

    @Override
    public void drawScreen(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        switch (getModel().getCurrent()) {
            case MENU:
                getMainMenuViewer().draw(getModel().getMainMenu(), gui);
                break;
            case ENDINGS:
                getEndingPageViewer().draw(getModel().getEndingPage(), gui);
                break;
        }
    }
    public MenuViewer getMainMenuViewer() {
        return mainMenuViewer;
    }
    public SceneViewer getEndingPageViewer() {
        return endingPageViewer;
    }
}
