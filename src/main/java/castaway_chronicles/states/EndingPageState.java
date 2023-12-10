package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.menu.EndingPageController;
import castaway_chronicles.model.menu.EndingPage;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.menu.EndingPageViewer;

public class EndingPageState extends State<EndingPage> {
    public EndingPageState(EndingPage model) {
        super(model);
    }

    @Override
    protected Viewer<EndingPage> getViewer() {
        return new EndingPageViewer(getModel());
    }

    @Override
    protected Controller<EndingPage> getController() {
        return new EndingPageController(getModel());
    }
}
