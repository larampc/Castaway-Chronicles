package model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @Property
    void getLeft(@ForAll int x, @ForAll int y, @ForAll int o) {
        assertEquals(x - o, new Position(x, y).getLeft(o).getX());
        assertEquals(y, new Position(x, y).getLeft(o).getY());
    }

    @Property
    void getRight(@ForAll int x, @ForAll int y, @ForAll int o) {
        assertEquals(x + o, new Position(x, y).getRight(o).getX());
        assertEquals(y, new Position(x, y).getRight(o).getY());
    }

    @Property
    void getUp(@ForAll int x, @ForAll int y, @ForAll int o) {
        assertEquals(x, new Position(x, y).getUp(o).getX());
        assertEquals(y - o, new Position(x, y).getUp(o).getY());
    }
    @Property
    void getDown(@ForAll int x, @ForAll int y, @ForAll int o) {
        assertEquals(x, new Position(x, y).getDown(o).getX());
        assertEquals(y + o, new Position(x, y).getDown(o).getY());
    }
}
