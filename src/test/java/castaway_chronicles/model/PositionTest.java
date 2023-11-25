package castaway_chronicles.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    public void equals_same(){
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1,2);
        assertEquals(pos1, pos2);
        assertEquals(pos1, pos1);
    }

    @Test
    public void equals_different(){
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1,3);
        assertNotEquals(pos1, pos2);
        assertNotEquals(pos1, new Object());
    }


    @Test
    public void hashcode_same(){
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1,2);
        assertEquals(pos1.hashCode(), pos2.hashCode());
        assertEquals(pos1.hashCode(), pos1.hashCode());
    }

    @Test
    public void hashcode_different(){
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1,3);
        assertNotEquals(pos1.hashCode(), pos2.hashCode());
        assertNotEquals(pos1.hashCode(), new Object().hashCode());
    }
}
