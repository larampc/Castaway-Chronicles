package castaway_chronicles.model;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Position getLeft(int offset) {return new Position(x - offset, y);}
    public Position getRight(int offset) {return new Position(x + offset, y);}
    public Position getUp(int offset) {return new Position(x, y - offset);}
    public Position getDown(int offset) {return new Position(x, y + offset);}
    public int getX() {return x;}
    public int getY() {return y;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return position.x == x && position.y == y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
