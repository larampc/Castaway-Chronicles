package castaway_chronicles;

import castaway_chronicles.gui.LanternaGUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.SceneLoader;
import castaway_chronicles.model.menu.Menu;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.game.LocationViewer;
import castaway_chronicles.view.game.SceneViewer;
import castaway_chronicles.view.menu.MenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    private final LanternaGUI gui;

    public Application() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(200, 150);
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        new Application().start();
    }

    private void start() throws IOException, URISyntaxException, InterruptedException {
        SceneLoader loadscene = new SceneLoader("Beach", "Location");
        List<String> interactables = new ArrayList<>();
        interactables.add("toot");
        List<String> backgrounds = new ArrayList<>();
        backgrounds.add("Beach");
        SceneViewer<Location> scene = new LocationViewer((Location) loadscene.createScene());
        scene.draw(gui);
        gui.drawText(new Position(10, 100), 170, "I'm toot H,ell!o wh?????                           ? ? .sfiriugreofe Brunooooooooo   ooooooooo oo ooo ooooooooooooo oo ooooooooogg cnxhd fhhfhfb bfysgf fssbhh", 1,true);
        Menu m = new Menu();
        Viewer<Menu> mv = new MenuViewer(m);
        mv.draw(gui);
        TimeUnit.SECONDS.sleep(15);
        gui.close();
    }
}
