package castaway_chronicles.controller.Commands;

import castaway_chronicles.model.SelectionPanel;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetSideOptionCommand implements Command {
    private final SelectionPanel selectionPanel;
    public GetSideOptionCommand(SelectionPanel selectionPanel) {
        this.selectionPanel = selectionPanel;
    }
    @Override
    public void execute() throws IOException, InterruptedException, URISyntaxException {
        if (!selectionPanel.getEntry(selectionPanel.getCurrentEntry() + 2).isEmpty()) {
            selectionPanel.nextEntry();
            selectionPanel.nextEntry();
        } else if (!selectionPanel.getEntry(selectionPanel.getCurrentEntry() - 2).isEmpty()) {
            selectionPanel.previousEntry();
            selectionPanel.previousEntry();
        }
    }
}
