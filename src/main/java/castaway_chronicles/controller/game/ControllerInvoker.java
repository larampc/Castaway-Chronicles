package castaway_chronicles.controller.game;

public class ControllerInvoker {
    public Command command;
    public ControllerInvoker(Command c) {
        this.command = c;
    }
    public void execute() {
        this.command.execute();
    }
}
