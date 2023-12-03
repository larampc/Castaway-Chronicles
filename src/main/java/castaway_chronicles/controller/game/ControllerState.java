package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.model.Position;

import java.io.IOException;

public interface ControllerState {
    void click(Position position) throws IOException;
    void keyUp();
    void keyDown();
    void select(Application application) throws IOException;
    void escape();
}
