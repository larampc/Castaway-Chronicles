package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.menu.MenuController;
import castaway_chronicles.model.menu.Menu;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.menu.MenuViewer;

public class MenuState extends State<Menu>{
    public MenuState(Menu model) {
        super(model);
    }
    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
