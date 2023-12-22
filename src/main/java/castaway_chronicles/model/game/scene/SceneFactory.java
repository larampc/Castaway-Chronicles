package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.MainChar;

import java.util.HashMap;

public abstract class SceneFactory {
   public static Scene getScene(Game.SCENE type, Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, MainChar mainChar){
       switch (type) {
           case MAP:
               return new Map(background,interactables, visibleInteractables);
           case LOCATION:
               return new Location(background,interactables, visibleInteractables, mainChar);
           case BACKPACK:
               return new Backpack(background, interactables, visibleInteractables);
           default:
               return null;
       }
   }
}
