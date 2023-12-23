package castaway_chronicles.controller;

import castaway_chronicles.Application;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Controller<T> {
    private final T model;
    public Controller(T model) {
        this.model = model;
    }
    public T getModel() {
        return model;
    }
    public abstract void step(Application application, InputEvent action, long startTime) throws IOException, InterruptedException, URISyntaxException;
}
