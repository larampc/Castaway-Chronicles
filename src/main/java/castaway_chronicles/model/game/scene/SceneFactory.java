package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;

import java.util.List;

public abstract class SceneFactory {
   public static Scene getScene(String type, Background background, List<Interactable> interactables, List<Interactable> visibleInteractables, MainChar mainChar){
       if ("LOCATION".equalsIgnoreCase(type)) return new Location(background,interactables, visibleInteractables, mainChar);
       else if("MAP".equalsIgnoreCase(type)) return new Map(background,interactables, visibleInteractables);
       else if("BACKPACK".equalsIgnoreCase(type)) return new Backpack(background, interactables, visibleInteractables);
       return null;
   }
}
