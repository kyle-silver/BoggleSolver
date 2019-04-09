package dev.kylesilver.boggle.board;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private char[][] board;
    private Set<Tile> visited;

    public Board(char[][] board) {
        this.board = board;
        visited = new HashSet<>();
    }

    public boolean visited(Tile tile) {
        return visited.contains(tile);
    }

    public void addToVisited(Tile tile) {
        visited.add(tile);
    }

    public void removeFromVisited(Tile tile) {
        visited.remove(tile);
    }

    public void clearVisited() {
        visited.clear();
    }

    public char charAt(Tile tile) {
        return board[tile.row()][tile.col()];
    }

    public int numRows() {
        return board.length;
    }

    public int numCols() {
        return board[0].length;
    }

    public Set<Tile> unvisitedNeighbors(Tile tile) {
        int row = tile.row();
        int col = tile.col();
        Set<Tile> neighbors = new HashSet<>();
        addIfUnvisited(neighbors, row-1, col-1);
        addIfUnvisited(neighbors, row-1, col);
        addIfUnvisited(neighbors, row-1, col+1);
        addIfUnvisited(neighbors, row, col-1);
        addIfUnvisited(neighbors, row, col+1);
        addIfUnvisited(neighbors, row+1, col-1);
        addIfUnvisited(neighbors, row+1, col);
        addIfUnvisited(neighbors, row+1, col+1);
        return neighbors;
    }

    private void addIfUnvisited(Set<Tile> neighbors, int row, int col) {
        if (row < 0 || row >= numRows()) {
            return;
        }
        if (col < 0 || col >= numCols()) {
            return;
        }
        Tile tile = new Tile(row, col);
        if (visited.contains(tile)) {
            return;
        }
        neighbors.add(tile);
    }

}
