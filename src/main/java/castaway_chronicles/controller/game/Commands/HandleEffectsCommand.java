package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.Application;
import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.states.EndState;

import java.util.List;

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
    public void execute()  {
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
    private void executeLocationEffects(String[] s) {
        Location location = game.getLocation(s[0]);
        if (s[2].equalsIgnoreCase("V")) {
            location.setVisible(s[1]);
        }
        if (s[2].equalsIgnoreCase("I")) {
            location.setInvisible(s[1]);
        }
    }
    private void executeEnd(String[] s) {
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath("Scenes_saved");
        resourceManager.deleteResourceFileContent();
        resourceManager.setPath("achieved_endings.txt");
        resourceManager.writeToFile(s[1]+"\n");
        application.setState(new EndState(new Ending(s[1])));
    }
    private void executeNPCEffects(String[] s) {
        ((NPC)game.getCurrentLocation().getInteractable(s[1])).goToState(Integer.parseInt(s[2]));
        if (s.length != 4) game.setTextDisplay((InteractableWithText) game.getCurrentLocation().getInteractable(s[1]));
        else game.getTextDisplay().closeTextBox();
    }
    private void executeMapEffects(String[] s) {
        if (s[2].equalsIgnoreCase("V")) {
            game.getMap().setVisible(s[1]);
        }
        if (s[2].equalsIgnoreCase("I")) {
            game.getMap().setInvisible(s[1]);
        }
    }
    private void executeBackpackEffects(String[] s) {
        if (s[2].equalsIgnoreCase("V")) {
            game.getBackpack().setVisible(s[1]+"_backpack");
        }
        else if (s[2].equalsIgnoreCase("I")) {
            game.getBackpack().setInvisible(s[1]+"_backpack");
        }
        else {
            ((ItemBackpack)game.getBackpack().getInteractable(s[1]+"_backpack")).setNameBackpack(s[2]+"_backpack");
        }
    }
}
