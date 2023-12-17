package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.menu.MainPageController;
import castaway_chronicles.model.menu.MainPage;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.menu.MainPageViewer;

public class MainPageState extends State<MainPage> {
    public MainPageState(MainPage model) {
        super(model);
    }

    @Override
    protected Viewer<MainPage> getViewer() {
        return new MainPageViewer(getModel());
    }

    @Override
    protected Controller<MainPage> getController() {
        return new MainPageController(getModel());
    }
}
