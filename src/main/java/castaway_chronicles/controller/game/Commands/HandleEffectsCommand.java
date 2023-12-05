package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.util.List;

public class HandleEffectsCommand implements Command{
    private Game game;
    public HandleEffectsCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        List<String> effects = game.getCurrentLocation().getDialogState().getNPCDialog().getState().getEffects();
        if (effects.isEmpty()) return;
        for (String effect: effects) {
            String[] s = effect.split(" ", -1);
            if (s[0].equalsIgnoreCase("map")) {
                if (s[2].equalsIgnoreCase("V")) {
                    game.getMap().setVisible(s[1]);
                }
                if (s[2].equalsIgnoreCase("I")) {
                    game.getMap().setInvisible(s[1]);
                }
            }
            else if (s[0].equalsIgnoreCase("backpack")) {
                if (s[2].equalsIgnoreCase("V")) {
                    game.getMap().setVisible(s[1]);
                }
                if (s[2].equalsIgnoreCase("I")) {
                    game.getMap().setInvisible(s[1]);
                }
            }
            else {
                Location location = game.getLocation(s[0]);
                if (s[2].equalsIgnoreCase("V")) {
                    location.setVisible(s[1]);
                }
                if (s[2].equalsIgnoreCase("I")) {
                    location.setInvisible(s[1]);
                }
            }
        }
    }
}
