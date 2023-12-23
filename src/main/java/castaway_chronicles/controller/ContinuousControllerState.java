package castaway_chronicles.controller;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ContinuousControllerState extends ControllerState {
    void none(long time) throws IOException, InterruptedException, URISyntaxException;
}
