package castaway_chronicles.controller.game.ControllerStates;

import castaway_chronicles.Application;
import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ControllerState {
    void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException;
    void keyUp();
    void keyDown();
    void keyRight();
    void keyLeft();
    void select(Application application) throws IOException, InterruptedException, URISyntaxException;
    void escape();
    void none(long time) throws IOException, InterruptedException, URISyntaxException;
}
