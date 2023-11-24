package model.game.elements;

import model.Position;

public abstract class Element {
    private Position position;
    private final int width;
    private final int height;
    private final String name;
    public Element(int x, int y, int width, int height, String name) {
        this.name = name;
        this.position = new Position(x,y);
        this.width = width;
        this.height = height;
    }
    public Position getPosition() {return position; }
    public int getWidth() {return width; }
    public int getHeight() {return height; }
    public String getName() {return name; }
    public void setPosition(Position position) {this.position = position; }
}
