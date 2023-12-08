package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;
import java.util.List;

public class HandleEffectsCommand implements Command{
    private Game game;
    private List<String> effects;
    public HandleEffectsCommand(Game game, List<String> effects) {
        this.game = game;
        this.effects = effects;
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        if (effects.isEmpty()) return;
        for (String effect: effects) {
            String[] s = effect.split(" ", -1);
            if (s[0].equalsIgnoreCase("end")) {
                game.setEnd(s[1]);
                game.setCurrentScene("END");
                continue;
            }
            if (s[0].equalsIgnoreCase("NPC")) {
                ((NPC)game.getCurrentLocation().getInteractable(s[1])).getDialogState().goToState(Integer.parseInt(s[2]));
                if (s.length != 4) game.getCurrentLocation().setDialog(s[1]);
                else game.getCurrentLocation().getDialogState().leaveDialog();
                continue;
            }
            if (s[0].equalsIgnoreCase("map")) {
                if (s[2].equalsIgnoreCase("V")) {
                    game.getMap().setVisible(s[1]);
                }
                if (s[2].equalsIgnoreCase("I")) {
                    game.getMap().setInvisible(s[1]);
                }
                continue;
            }
            if (s[0].equalsIgnoreCase("backpack")) {
                if (s[2].equalsIgnoreCase("V")) {
                    game.getBackpack().setVisible(s[1]+"_backpack");
                }
                else if (s[2].equalsIgnoreCase("I")) {
                    game.getBackpack().setInvisible(s[1]+"_backpack");
                }
                else {
                    ((ItemBackpack)game.getBackpack().getInteractable(s[1]+"_backpack")).setNameBackpack(s[2]+"_backpack");
                }
                continue;
            }
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
