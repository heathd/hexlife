package org.davidheath.hexlife;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Coordinate {
    /*

     Cubic coordinate system (ref https://www.redblobgames.com/grids/hexagons/#coordinates-cube):

     each coordinate is represented by three numbers (x,y,z)
     constraint that x+y+z = 0

                    VECTOR
                     x    y     z
        NORTHEAST:  +1         -1
        EAST:       +1   -1
        SOUTHEAST:       -1    +1
        SOUTHWEST:  -1         +1
        WEST:       -1   +1
        NORTHWEST:       -1    +1

     */

    public final int x;
    public final int y;
    public final int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    public static Coordinate origin() {
        return new Coordinate(0,0,0);
    }

    @Override
    public String toString() {
        StringJoiner j = new StringJoiner(",", "(", ")");
        j.add(String.valueOf(x));
        j.add(String.valueOf(y));
        j.add(String.valueOf(z));

        return j.toString();
    }

    public Coordinate move(Direction direction) {
        return new Coordinate(
                this.x + direction.xOffs,
                this.y + direction.yOffs,
                this.z + direction.zOffs
        );
    }

    public Set<Coordinate> neighbours() {
        HashSet<Coordinate> neighbours = new HashSet<>();
        for(Direction d : Direction.values()) {
            neighbours.add(this.move(d));
        }
        return neighbours;
    }
}
