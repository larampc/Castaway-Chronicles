package castaway_chronicles.model;

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
}
