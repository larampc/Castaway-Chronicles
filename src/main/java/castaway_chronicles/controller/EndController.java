package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.gui.Action;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.menu.MainPage;
import castaway_chronicles.states.MainPageState;

import java.io.IOException;


public class EndController extends Controller<Ending> {
    private long lastFrame = 0;
    public EndController(Ending model) {
        super(model);
    }

    @Override
    public void step(Application application, Action action, long startTime) throws IOException {
        if (startTime-lastFrame > 200 && getModel().getMax() > getModel().getCurrent()) {
            getModel().setNext();
            lastFrame = startTime;
        }
        if (action.getType().equalsIgnoreCase("select") && getModel().getMax() == getModel().getCurrent()) {
            MainPage mainPage  = new MainPage();
            mainPage.setCurrent(MainPage.PAGE.ENDINGS);
            application.setState(new MainPageState(mainPage));
        }

    }
}
