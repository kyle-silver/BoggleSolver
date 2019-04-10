package dev.kylesilver.boggle.board;

import java.util.HashSet;
import java.util.Set;

public class Tile {
    private int row;
    private int col;
    private char value;
    private Set<Tile> neighbors;

    public Tile(char value, int row, int col) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public char value() {
        return value;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public void addNeighbor(Tile tile) {
        neighbors.add(tile);
    }

    public Set<Tile> neighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", row(), col());
    }

}
