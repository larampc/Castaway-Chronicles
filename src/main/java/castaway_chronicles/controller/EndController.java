package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.MainPageState;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class EndController extends Controller<Ending> {
    private long lastFrame = 0;
    public EndController(Ending model) {
        super(model);
    }

    @Override
    public void step(Application application, InputEvent action, long startTime) throws IOException {
        if (startTime-lastFrame > 200 && getModel().getMax() > getModel().getCurrent()) {
            getModel().setNext();
            lastFrame = startTime;
        }
        if (action instanceof KeyEvent && ((KeyEvent)action).getKeyCode() == KeyEvent.VK_ENTER && getModel().getMax() == getModel().getCurrent()) {
            MainPage mainPage  = new MainPage();
            mainPage.setCurrent(MainPage.PAGE.ENDINGS);
            application.setState(new MainPageState(mainPage));
        }
    }
}
