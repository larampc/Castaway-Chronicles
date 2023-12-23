package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.EndController;
import castaway_chronicles.model.Ending;
import castaway_chronicles.view.EndViewer;
import castaway_chronicles.view.Viewer;

public class EndState extends State<Ending>{
    public EndState(Ending model) {super(model);}
    @Override
    protected Viewer<Ending> getViewer() {return new EndViewer(getModel());}
    @Override
    protected Controller<Ending> getController() {return new EndController(getModel());}
}
