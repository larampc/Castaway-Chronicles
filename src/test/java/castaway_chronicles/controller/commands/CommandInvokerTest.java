package castaway_chronicles.controller.commands;

import castaway_chronicles.controller.game.Commands.Command;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CommandInvokerTest {
    @Test
    void commandInvoker() throws IOException, URISyntaxException, InterruptedException {
        CommandInvoker commandInvoker = new CommandInvoker();
        Command commandMock = Mockito.mock(Command.class);
        assertDoesNotThrow(commandInvoker::execute);
        commandInvoker.setCommand(commandMock);
        commandInvoker.execute();
        Mockito.verify(commandMock).execute();
    }
}
