package castaway_chronicles.controller;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

//    public abstract void step(Application application, GUI.ACTION action) throws IOException;
}
