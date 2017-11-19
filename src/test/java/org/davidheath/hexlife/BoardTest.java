package org.davidheath.hexlife;

import org.junit.Test;

import static org.davidheath.hexlife.Direction.*;
import static org.davidheath.hexlife.Coordinate.origin;
import static org.junit.Assert.assertEquals;

public class BoardTest {
    @Test
    public void itShouldHaveNoLiveCellsByDefault() {
        Board board = new Board();
        assertEquals(board.countLiveCells(), 0);
    }

    @Test
    public void itShouldReturnCorrectLiveCells() {
        Board board = new Board(new Coordinate(0,0,0));
        assertEquals(1, board.countLiveCells());
    }

    @Test
    public void itCanCountNeighbours() {
        Board board = new Board(new Coordinate(0,0,0));
        assertEquals(1, board.countNeighbouringLiveCells(new Coordinate(0, 1, -1)));
    }

    @Test
    public void canCompareForEquality() {
        assertEquals(new Board(), new Board());
    }

    @Test
    public void itFindsNextBoardForFlipFlop() {

        /*
          x       -       x
         - -  => x x  => - -
          x       -       x
         */

        Board start = new Board(
                origin().move(NORTHEAST),
                origin().move(SOUTHEAST)
        );

        Board expectedNext = new Board(
                origin(),
                origin().move(EAST)
        );

        assertEquals(expectedNext, start.next());

        // Returns to original state
        assertEquals(start, start.next().next());
    }
}
