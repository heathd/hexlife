package org.davidheath.hexlife;

import org.junit.Test;

import java.util.Random;
import java.util.StringJoiner;

import static org.davidheath.hexlife.Direction.EAST;
import static org.davidheath.hexlife.Coordinate.origin;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardRendererTest {

    public Direction randomDirection() {
        Direction[] values = Direction.values();
        return values[new Random().nextInt(values.length)];
    }

    public Coordinate randomCoordinate() {
        Coordinate c = origin();
        for (int i=0; i<10; i++) {
            c = c.move(randomDirection());
        }
        return c;
    }

    @Test
    public void testCoordinateConversion() {
        for (int i=0; i<10; i++) {
            Coordinate c = randomCoordinate();

            int row = BoardRenderer.row(c);
            int column = BoardRenderer.column(c);

            Coordinate converted = BoardRenderer.fromRowCol(row, column);

            assertThat(c, is(converted));
        }
    }

    @Test
    public void testRenderingSimple() {
        String rendered = BoardRenderer.toString(new Board(origin()));
        StringJoiner j = new StringJoiner("\n", "", "\n");
        j.add("  -1: . . .");
        j.add("   0:  . # .");
        j.add("   1: . . .");
        String expected = j.toString();

        assertThat(rendered, is(expected));
    }

    @Test
    public void testRenderingTwoPoints() {
        String rendered = BoardRenderer.toString(new Board(origin(), origin().move(EAST)));
        StringJoiner j = new StringJoiner("\n", "", "\n");
        j.add("  -1: . . . .");
        j.add("   0:  . # # .");
        j.add("   1: . . . .");
        String expected = j.toString();

        assertThat(rendered, is(expected));
    }

}
