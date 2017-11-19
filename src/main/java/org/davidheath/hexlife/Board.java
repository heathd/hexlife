package org.davidheath.hexlife;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final Set<Coordinate> liveCells;

    public Board(Coordinate... liveCells) {
        this(new HashSet<Coordinate>(Arrays.asList(liveCells)));
    }

    public Board(Set<Coordinate> liveCells) {
        this.liveCells = liveCells;
    }

    public Board next() {
        return new Board(
            allCoordinates().stream().filter(this::willBeLiveNext).collect(Collectors.toSet())
        );
    }

    public Set<Coordinate> allCoordinates() {
        return liveCells.stream().flatMap(l -> l.neighbours().stream()).collect(Collectors.toSet());
    }

    int countLiveCells(){
        return liveCells.size();
    }

    boolean isLive(Coordinate coord) {
        return liveCells.contains(coord);
    }

    boolean willBeLiveNext(Coordinate c) {
        return nextStateFor(isLive(c), countNeighbouringLiveCells(c));
    }

    int countNeighbouringLiveCells(Coordinate c) {
        Set<Coordinate> neighbours = c.neighbours();
        neighbours.retainAll(liveCells); // mutates neighbours
        return neighbours.size();
    }

    public static boolean nextStateFor(boolean isAlive, int numberOfLiveNeighbours) {
        /*
        islive && <3 live => die
        islive && 3-4 live => live
        islive && >4 live => die
        isdead && ==2 live => live
         */
        if (isAlive)
            return (numberOfLiveNeighbours ==3 || numberOfLiveNeighbours==4);
        else
            return numberOfLiveNeighbours == 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return liveCells.equals(board.liveCells);
    }

    @Override
    public int hashCode() {
        return liveCells.hashCode();
    }

    public String toString() {
        return BoardRenderer.toString(this);
    }
}
