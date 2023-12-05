package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.menu.MainMenuController;
import castaway_chronicles.model.menu.MainMenu;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.menu.MenuViewer;

public class MenuState extends State<MainMenu>{
    public MenuState(MainMenu model) {
        super(model);
    }
    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
