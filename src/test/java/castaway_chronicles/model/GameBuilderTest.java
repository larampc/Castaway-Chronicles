package castaway_chronicles.model;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.GameBuilder;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.InteractableFactory;

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
        game = new GameBuilder().createGame();
    }
    @Test
    public void game_map() throws IOException {
        List<Interactable> expectedInteractables = new ArrayList<>();
        List<Interactable> expectedVisibleInteractables = new ArrayList<>();
        Background expectedBackground = new Background(0,0, 200, 150, "map");
        expectedInteractables.add(InteractableFactory.getInteractable("Icon", 97, 35, 39, 36, "Mountain_icon",0));
        expectedInteractables.add(InteractableFactory.getInteractable("Icon", 22, 85, 21, 29, "Beach_icon",0));
        expectedInteractables.add(InteractableFactory.getInteractable("Icon", 62, 75, 30, 23, "City_icon",0));
        expectedInteractables.add(InteractableFactory.getInteractable("Icon", 112, 74, 24, 30, "Forest_icon",0));
        expectedInteractables.add(InteractableFactory.getInteractable("Icon", 132, 26, 33, 25, "Volcano_icon",0));

        expectedVisibleInteractables.add(InteractableFactory.getInteractable("Icon", 22, 85, 21, 29, "Beach_icon",0));

        assertTrue(game.getMap().getInteractables().size() == expectedInteractables.size() && game.getMap().getInteractables().containsAll(expectedInteractables));
        assertTrue(game.getMap().getVisibleInteractables().size() == expectedVisibleInteractables.size() && game.getMap().getVisibleInteractables().containsAll(expectedVisibleInteractables));
        assertEquals(expectedBackground.getPosition(), game.getMap().getBackground().getPosition());
        assertEquals(expectedBackground.getName(), game.getMap().getBackground().getName());
        assertTrue(expectedBackground.getWidth() == game.getMap().getBackground().getWidth() && expectedBackground.getHeight() == game.getMap().getBackground().getHeight());


    }

    @Test
    public void game_Backpack() throws IOException {
        List<Interactable> expectedInteractables = new ArrayList<>();

        expectedInteractables.add(InteractableFactory.getInteractable("Item", 100, 110, 8, 7, "rock",0));

        assertTrue(game.getBackpack().getInteractables().size() == expectedInteractables.size() && game.getBackpack().getInteractables().containsAll(expectedInteractables));
        assertTrue(game.getBackpack().getVisibleInteractables().isEmpty());
    }

    @Test
    public void game_Locations() {
        List<String> locations = new ArrayList<>();
        locations.add("Beach");
        locations.add("City");
        locations.add("tavern");

        assertTrue(game.getLocations().keySet().containsAll(locations));
        assertEquals(game.getCurrentLocation().getBackground().getName(),"Beach");
        assertEquals(game.getScene(), Game.SCENE.LOCATION);
    }

    @Test
    public void game_pauseMenu() {
        List<String> expectedEntries = new ArrayList<>();
        expectedEntries.add("Resume");
        expectedEntries.add("Exit");
        assertEquals(game.getPauseMenu().getNumberEntries(),2);
        assertTrue(game.getPauseMenu().getEntries().containsAll(expectedEntries));
    }

}
