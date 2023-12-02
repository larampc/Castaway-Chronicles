package castaway_chronicles.controller.game;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.model.game.scene.Scene;

public abstract class SceneController<T extends Scene> extends Controller<Scene> {
    public SceneController(T model) {
        super(model);
    }
}
