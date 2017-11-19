package org.davidheath.hexlife;

public enum Direction {
    NORTHEAST( 1, 0,-1),
    EAST     ( 1,-1, 0),
    SOUTHEAST( 0,-1, 1),
    SOUTHWEST(-1, 0, 1),
    WEST     (-1, 1, 0),
    NORTHWEST( 0, 1,-1);

    public final int xOffs;
    public final int yOffs;
    public final int zOffs;

    Direction(int xOffs, int yOffs, int zOffs) {
        this.xOffs = xOffs;
        this.yOffs = yOffs;
        this.zOffs = zOffs;
    }
}
