package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.game.gameElements.GameInteractableFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBuilderTest {
    private Game game;
    @BeforeEach
    public void init() throws IOException {
        game = new GameBuilder().createGame(false);
    }
    @Test
    public void game_map() throws IOException {
        List<Interactable> expectedInteractables = new ArrayList<>();
        List<Interactable> expectedVisibleInteractables = new ArrayList<>();
        Background expectedBackground = new Background(0,0, 200, 150, "Map", false);
        expectedInteractables.add(GameInteractableFactory.getInteractable("Icon", 97, 35, 39, 36, "Mountain_icon",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("Icon", 22, 85, 21, 29, "Beach_icon",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("Icon", 62, 75, 30, 23, "City_icon",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("Icon", 112, 74, 24, 30, "Forest_icon",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("Icon", 132, 26, 33, 25, "Volcano_icon",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("Item", 132, 26, 32, 25, "Volcano_item", 0));

        expectedVisibleInteractables.add(GameInteractableFactory.getInteractable("Icon", 22, 85, 21, 29, "Beach_icon",0));

        assertTrue(game.getMap().getInteractables().size() == expectedInteractables.size() && game.getMap().getInteractables().containsAll(expectedInteractables));
        assertTrue(game.getMap().getVisibleInteractables().size() == expectedVisibleInteractables.size() && game.getMap().getVisibleInteractables().containsAll(expectedVisibleInteractables));
        assertEquals(expectedBackground.getPosition(), game.getMap().getBackground().getPosition());
        assertEquals(expectedBackground.getName(), game.getMap().getBackground().getName());
        assertTrue(expectedBackground.getWidth() == game.getMap().getBackground().getWidth() && expectedBackground.getHeight() == game.getMap().getBackground().getHeight());


    }

    @Test
    public void game_Backpack() throws IOException {
        List<Interactable> expectedInteractables = new ArrayList<>();

        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 73, 112, 23, 20, "rock_backpack",0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 72, 72, 31, 21, "rope_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 104, 105, 20, 29, "beer1_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 104, 105, 20, 29, "beer2_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 104, 105, 20, 29,"beer3_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 44, 17, 14, 118,"broom_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 24, 18, 16, 117,"pipe_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 104, 40, 23, 14,"cloth_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 146, 16, 29, 127,"branch_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 72, 24, 18, 25,"flower_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 105, 80, 9, 11,"potion1_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 117, 80, 9, 11,"potion2_backpack", 0));
        expectedInteractables.add(GameInteractableFactory.getInteractable("BItem", 94, 30, 9, 18,"key_backpack", 0));

        assertTrue(game.getBackpack().getInteractables().size() == expectedInteractables.size() && game.getBackpack().getInteractables().containsAll(expectedInteractables));
        assertTrue(game.getBackpack().getVisibleInteractables().isEmpty());
    }

    @Test
    public void game_Locations() {
        List<String> locations = new ArrayList<>();
        locations.add("Beach");
        locations.add("City");
        locations.add("Tavern");
        locations.add("Mountain");
        locations.add("WitchOldHouse");
        locations.add("Newspaper");
        locations.add("Paper1");
        locations.add("Paper2");
        locations.add("Forest");
        locations.add("WitchNewHouse");
        locations.add("Store");
        locations.add("Volcano");
        locations.add("PiratesTip");

        assertTrue(game.getLocations().keySet().containsAll(locations));
        assertEquals(game.getCurrentLocation().getBackground().getName(),"Beach");
        assertEquals(game.getScene(), Game.SCENE.LOCATION);
    }

    @Test
    public void game_pauseMenu() {
        List<String> expectedEntries = new ArrayList<>();
        expectedEntries.add("Resume");
        expectedEntries.add("Exit");
        expectedEntries.add("Save");
        expectedEntries.add("Menu");
        assertEquals(4, game.getPauseMenu().getSelectionPanel().getNumberEntries());
        assertTrue(game.getPauseMenu().getSelectionPanel().getEntries().containsAll(expectedEntries));
    }

}
