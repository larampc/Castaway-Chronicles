package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ControllerState {
    void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException;
    void key(int keyCode, Application application) throws IOException, URISyntaxException, InterruptedException;
}
