package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.model.Position;

public class BackpackController {
    private Game model;
    public BackpackController(Game model) {
        this.model = model;
    }

    @Override
    public void click(Position position) {

    }

    @Override
    public void keyUp() {
        //does nothing
    }

    @Override
    public void keyDown() {
        //does nothing
    }

    @Override
    public void select(Application application) {
        //does nothing
    }

    @Override
    public void escape() {
        gameController.setControllerState(gameController.getLocationController());
    }
}
