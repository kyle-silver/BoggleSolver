package dev.kylesilver.boggle.board;

import java.util.Objects;

public class Tile {
    private int row, col;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) o;
        return row == tile.row && col == tile.col;
    }

    @Override public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override public String toString() {
        return "(" + row + ", " + col + ")";
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }
}
