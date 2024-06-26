package castaway_chronicles.model;

import castaway_chronicles.ResourceManager;

import java.util.List;

public abstract class InteractableWithText extends Interactable {
    private SelectionPanel choices;
    public InteractableWithText(int x, int y, int w, int h, String name) {super(x, y, w, h, name);}
    public abstract String getText();
    public SelectionPanel getChoices() {return choices;}
    public abstract List<String> getEffects();
    public void setChoices(SelectionPanel choices) {this.choices = choices;}
    public ResourceManager getResourceManager(){
        return ResourceManager.getInstance();
    }
}
