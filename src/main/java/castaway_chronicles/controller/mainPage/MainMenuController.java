package castaway_chronicles.controller.mainPage;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.GenericCommandInvoker;
import castaway_chronicles.controller.game.Commands.GetSideOptionCommand;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController implements ControllerState {
    private final MainPageController mainPageController;
    private final MainMenu mainMenu;
    private GenericCommandInvoker commandInvoker;
    public MainMenuController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
        mainMenu = mainPageController.getModel().getMainMenu();
        commandInvoker = new CommandInvoker();
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
    public void keyLeft() throws IOException, URISyntaxException, InterruptedException {
        GetSideOptionCommand getSide = new GetSideOptionCommand(mainMenu.getSelectionPanel());
        commandInvoker.setCommand(getSide);
        commandInvoker.execute();
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

    public void setCommandInvoker(GenericCommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }
}
