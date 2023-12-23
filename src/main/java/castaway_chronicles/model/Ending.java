package castaway_chronicles.model;

import castaway_chronicles.ResourceManager;

public class Ending {
    private final String name;
    private int current = 1;
    private int max = 0;
    public Ending(String name) {
        this.name = name;
        max = getResourceManager().countFiles("Endings/"+name);
    }
    public int getMax() {return max;}
    public int getCurrent() {return current;}
    public void setNext() {++this.current;}
    public String getCurrentFrame() {return String.format(name + "_" + "%04d", current);}
    public String getName() {return name;}
    public ResourceManager getResourceManager(){
        return ResourceManager.getInstance();
    }
}
