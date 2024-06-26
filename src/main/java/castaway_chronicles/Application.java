package castaway_chronicles;

import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.MainPageState;
import castaway_chronicles.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    private final LanternaGUI gui;
    private State state;
    public Application() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(200, 150);
        this.state = new MainPageState(new MainPage());
    }
    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        new Application().start();
    }
    public void setState(State state) {this.state = state;}
    private void start() throws IOException, URISyntaxException, InterruptedException {
        int FPS = 10;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        gui.close();
    }
}
