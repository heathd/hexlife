package org.davidheath.hexlife;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.davidheath.hexlife.Coordinate.origin;
import static org.davidheath.hexlife.Direction.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class CoordinateTest {
    @Test
    public void itShouldHaveAnOrigin() {
        assertThat(origin(), is(new Coordinate(0,0,0)));
    }

    @Test
    public void twoOriginCoordinatesShouldBeEqual() {
        assertThat(origin(), is(origin()));
    }

    @Test
    public void inverseAdjacentCoordinateIsTheSame() {
        Coordinate adjacent = origin().move(NORTHEAST).move(SOUTHWEST);
        assertThat(origin(), is(adjacent));
    }

    @Test
    public void adjacentCoordinateIsNotTheSameAsOrigin() {
        Coordinate adjacent = origin().move(NORTHEAST);
        assertThat(origin(), IsNot.not(adjacent));
    }

    @Test
    public void pathBackToSelfIsTheSame() {
        /*
               B
              ^ \
             /   v
            A<---C

          A->B: NORTHEAST
          B->C: SOUTHEAST
          C->A: WEST

         */

        Coordinate adjacent = origin().move(NORTHEAST).move(SOUTHEAST).move(WEST);
        assertThat(adjacent, is(origin()));
    }

    @Test
    public void moreComplexPathBackToSelfIsTheSame() {
        /*
               B
              ^ \
             /   v
        F-->A    C
         \      /
          E----D

          A->B: NORTHEAST
          B->C: SOUTHEAST
          C->D: SOUTHWEST
          D->E: WEST
          E->F: NORTHWEST
          F->A: EAST

         */


        Coordinate adjacent = origin()
                .move(NORTHEAST)
                .move(SOUTHEAST)
                .move(SOUTHWEST)
                .move(WEST)
                .move(NORTHWEST)
                .move(EAST);
        assertThat(adjacent, is(origin()));
    }

    @Test
    public void canListNeighbours() {
        Set<Coordinate> neighbours = origin().neighbours();
        assertThat(neighbours.size(), is(6));
        Set<Coordinate> expectedSet = new HashSet<>(Arrays.asList(
                new Coordinate(1,-1, 0),
                new Coordinate(1,0, -1),
                new Coordinate(0,1, -1),
                new Coordinate(0,-1, 1),
                new Coordinate(-1, 0, 1),
                new Coordinate(-1,1, 0)
        ));
        assertThat(neighbours, is(expectedSet));
    }
}
