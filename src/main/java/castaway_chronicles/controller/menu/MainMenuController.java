package castaway_chronicles.controller.menu;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.ControllerStates.ControllerState;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.model.menu.MainPage;
import castaway_chronicles.states.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController implements ControllerState {
    private final MainPageController mainPageController;
    private final MainMenu mainMenu;
    public MainMenuController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        mainMenu = mainPageController.getModel().getMainMenu();
    }

    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {

    }

    @Override
    public void key(int keyCode, Application application) throws IOException, URISyntaxException, InterruptedException {
        switch (keyCode){
            case KeyEvent.VK_UP:
                keyUp();
                break;
            case KeyEvent.VK_DOWN:
                keyDown();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                keyLeft();
                break;
            case KeyEvent.VK_ENTER:
                select(application);
                break;
            default:
        }
    }

    public void keyUp() {
        mainMenu.getSelectionPanel().previousEntry();
        if (mainMenu.isSelectedContinue() && !mainMenu.canContinue()) mainMenu.getSelectionPanel().previousEntry();
    }

    public void keyDown() {
        mainMenu.getSelectionPanel().nextEntry();
        if (mainMenu.isSelectedContinue() && !mainMenu.canContinue()) mainMenu.getSelectionPanel().nextEntry();
    }
    public void keyLeft() {
        if (!mainMenu.getSelectionPanel().getEntry(
                mainMenu.getSelectionPanel().getCurrentEntry() + 2
        ).isEmpty()) {
            mainMenu.getSelectionPanel().nextEntry();
            mainMenu.getSelectionPanel().nextEntry();
        } else if (!mainMenu.getSelectionPanel().getEntry(
                mainMenu.getSelectionPanel().getCurrentEntry() - 2
        ).isEmpty()) {
            mainMenu.getSelectionPanel().previousEntry();
            mainMenu.getSelectionPanel().previousEntry();
        }
        if (mainMenu.isSelectedContinue() && !mainMenu.canContinue()) {
            mainMenu.getSelectionPanel().previousEntry();
            mainMenu.getSelectionPanel().previousEntry();
        }
    }

    public void select(Application application) throws IOException {
        if (mainMenu.isSelectedExit()) application.setState(null);
        if (mainMenu.isSelectedStart())
            application.setState(new GameState(new GameBuilder().createGame(false)));
        if (mainMenu.isSelectedContinue() && mainMenu.canContinue())
            application.setState(new GameState(new GameBuilder().createGame(true)));
        if (mainMenu.isSelectedEndings()) {
            mainPageController.getModel().setCurrent(MainPage.PAGE.ENDINGS);
            mainPageController.setCurrent(mainPageController.getEndingPageController());
        }
    }

    @Override
    public void none(long time) throws IOException, InterruptedException, URISyntaxException {

    }
}
