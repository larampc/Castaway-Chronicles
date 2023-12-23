package castaway_chronicles.states;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.GUI;
import castaway_chronicles.view.Viewer;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;
    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }
    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();
    public T getModel() {return model;}
    public void step(Application application, GUI gui, long startTime) throws IOException, URISyntaxException, InterruptedException {
        InputEvent action = gui.getNextAction();
        controller.step(application, action, startTime);
        viewer.draw(gui);
    }
}
