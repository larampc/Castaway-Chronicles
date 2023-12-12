package castaway_chronicles.controller.game.Commands;

import java.io.IOException;

public class CommandInvoker extends GenericCommandInvoker {
    public Command command = null;
    public CommandInvoker() {
    }
    public void execute() throws IOException, InterruptedException {
        if (command!= null) {
            this.command.execute();
        }
    }
    public void setCommand(Command command) {this.command = command;}
}
