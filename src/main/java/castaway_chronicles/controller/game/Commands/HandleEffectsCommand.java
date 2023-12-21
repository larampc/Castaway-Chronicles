package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.Application;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.states.EndState;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class HandleEffectsCommand implements Command{
    private final Game game;
    private final List<String> effects;
    private final Application application;
    public HandleEffectsCommand(Game game, List<String> effects, Application application) {
        this.game = game;
        this.effects = effects;
        this.application = application;
    }

    @Override
    public void execute() throws IOException, InterruptedException, URISyntaxException {
        if (effects.isEmpty()) return;
        for (String effect: effects) {
            String[] s = effect.split(" ", -1);
            if (s[0].equalsIgnoreCase("go")) {
                game.setCurrentLocation(s[1]);
                continue;
            }
            if (s[0].equalsIgnoreCase("end")) {
                executeEnd(s);
                continue;
            }
            if (s[0].equalsIgnoreCase("NPC")) {
                executeNPCEffects(s);
                continue;
            }
            if (s[0].equalsIgnoreCase("map")) {
                executeMapEffects(s);
                continue;
            }
            if (s[0].equalsIgnoreCase("backpack")) {
                executeBackpackEffects(s);
                continue;
            }
            executeLocationEffects(s);
        }
    }
    private void executeLocationEffects(String[] s){
        Location location = game.getLocation(s[0]);
        if (s[2].equalsIgnoreCase("V")) {
            location.setVisible(s[1]);
        }
        if (s[2].equalsIgnoreCase("I")) {
            location.setInvisible(s[1]);
        }
    }
    private void executeEnd(String[] s) throws IOException, URISyntaxException {
        Path path = Paths.get("");
        File toDelete = new File(path.toAbsolutePath()+"/src/main/resources/Scenes_saved");
        File[] allContents = toDelete.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                file.delete();
            }
        }
        File endings = new File(path.toAbsolutePath()+"/src/main/resources/achieved_endings.txt");
        Writer fr = Files.newBufferedWriter(endings.toPath(), UTF_8, CREATE, APPEND);
        fr.write(s[1]+"\n");
        fr.close();
        application.setState(new EndState(new Ending(s[1])));
    }
    private void executeNPCEffects(String[] s) throws IOException {
        ((NPC)game.getCurrentLocation().getInteractable(s[1])).goToState(Integer.parseInt(s[2]));
        if (s.length != 4) game.setTextBox((InteractableWithText) game.getCurrentLocation().getInteractable(s[1]));
        else game.getTextBox().closeTextBox();
    }
    private void executeMapEffects(String[] s) {
        if (s[2].equalsIgnoreCase("V")) {
            game.getMap().setVisible(s[1]);
        }
        if (s[2].equalsIgnoreCase("I")) {
            game.getMap().setInvisible(s[1]);
        }
    }
    private void executeBackpackEffects(String[] s) throws IOException {
        if (s[2].equalsIgnoreCase("V")) {
            game.getBackpack().setVisible(s[1]+"_backpack");
        }
        else if (s[2].equalsIgnoreCase("I")) {
            game.getBackpack().setInvisible(s[1]+"_backpack");
        }
        else {
            ((BackpackItem)game.getBackpack().getInteractable(s[1]+"_backpack")).setNameBackpack(s[2]+"_backpack");
        }
    }
}
