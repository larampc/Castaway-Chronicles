package castaway_chronicles.controller.Commands;

import java.io.IOException;
import java.net.URISyntaxException;

public class CommandInvoker {
    public Command command = null;
    public CommandInvoker() {
    }
    public void execute() throws IOException, InterruptedException, URISyntaxException {
        if (command!= null) {
            this.command.execute();
        }
    }
    public void setCommand(Command command) {this.command = command;}
}
