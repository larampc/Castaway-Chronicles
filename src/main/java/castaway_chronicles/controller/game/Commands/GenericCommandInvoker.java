package castaway_chronicles.controller.game.Commands;

import java.io.IOException;

public abstract class GenericCommandInvoker {
    public Command command = null;
    public abstract void execute() throws IOException, InterruptedException;
    public void setCommand(Command command){
        this.command = command;
    }
}
