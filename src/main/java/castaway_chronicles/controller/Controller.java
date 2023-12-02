package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.gui.Action;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Application application, Action action) throws IOException;
}
