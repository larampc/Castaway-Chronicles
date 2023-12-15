package castaway_chronicles.controller.game.Commands;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class GenericCommandInvoker {
    public Command command = null;
    public abstract void execute() throws IOException, InterruptedException, URISyntaxException;
    public void setCommand(Command command){
        this.command = command;
    }
}
