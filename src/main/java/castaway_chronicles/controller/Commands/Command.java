package castaway_chronicles.controller.Commands;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Command {
    void execute() throws IOException, InterruptedException, URISyntaxException;
}
