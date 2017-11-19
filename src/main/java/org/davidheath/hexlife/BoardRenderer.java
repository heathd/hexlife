package org.davidheath.hexlife;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class BoardRenderer {
    private final Board board;

    public static String toString(Board board) {
        return new BoardRenderer(board).toString();
    }

    private BoardRenderer(Board board) {
        this.board = board;
    }

    public boolean isLive(int row, int column) {
        return board.isLive(fromRowCol(row, column));
    }

    public static Coordinate fromRowCol(int row, int column) {
        // https://www.redblobgames.com/grids/hexagons/#conversions-offset
        int x = column - (row - (row & 1)) / 2;
        int z = row;
        int y = -x - z;

        return new Coordinate(x, y, z);
    }

    public static int row(Coordinate c) {
        // https://www.redblobgames.com/grids/hexagons/#conversions-offset
        return c.z;
    }

    public static int column(Coordinate c) {
        // https://www.redblobgames.com/grids/hexagons/#conversions-offset
        return c.x + (c.z - (c.z & 1)) / 2;
    }

    public String toString() {
        StringJoiner lines = new StringJoiner("\n", "", "\n");

        for (int row : allRows()) {
            String spacePrefix = (row & 1) == 0 ? " " : "";
            String prefix = String.format("% 4d: %s", row, spacePrefix);
            StringJoiner j = new StringJoiner(" ", prefix, "");

            for (int column : allColumns()) {
                j.add(isLive(row, column) ? "#" : ".");
            }
            lines.add(j.toString());
        }
        return lines.toString();
    }

    private List<Integer> allColumns() {
        return allCoordinates().stream().map(BoardRenderer::column).distinct().sorted().collect(Collectors.toList());
    }

    private List<Integer> allRows() {
        return allCoordinates().stream().map(BoardRenderer::row).distinct().sorted().collect(Collectors.toList());
    }

    private Set<Coordinate> allCoordinates() {
        return board.allCoordinates();
    }

}
