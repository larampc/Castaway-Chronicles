package model.game.elements;

import model.Position;

public abstract class Interactable extends Element {
    public Interactable(int x, int y, int w, int h, String name) {super (x, y, w, h, name);}
    public boolean contains(Position position) {
        return (position.getX() >= this.getPosition().getX() && position.getX() <= this.getPosition().getX() + this.getWidth()
            && position.getY() >= this.getPosition().getY() && position.getY() <= this.getPosition().getY() + this.getHeight());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interactable interactable = (Interactable) o;
        return interactable.getName().equals(this.getName());
    }
}
