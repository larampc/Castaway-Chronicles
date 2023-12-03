package castaway_chronicles.controller.game;

import castaway_chronicles.controller.game.Commands.Command;

import java.io.IOException;

public class CommandInvoker {
    public Command command = null;
    public CommandInvoker() {
    }
    public void execute() throws IOException {
        if (command!= null) {
            this.command.execute();
        }
    }
    public void setCommand(Command command) {this.command = command;}
}
