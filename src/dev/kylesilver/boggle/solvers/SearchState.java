package dev.kylesilver.boggle.solvers;

import java.util.HashSet;
import java.util.Set;

import dev.kylesilver.boggle.board.Tile;

class SearchState {
    private Set<Tile> visited;
    private Set<String> foundWords;
    
    public SearchState() {
        this.visited = new HashSet<>();
        this.foundWords = new HashSet<>();
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
    
    public Set<Tile> unvisitedNeighbors(Tile tile) {
        Set<Tile> unvisited = new HashSet<>();
        for (Tile neighbor : tile.neighbors()) {
            if (!visited.contains(neighbor)) {
                unvisited.add(neighbor);
            }
        }
        return unvisited;
    }
    
    public void addWord(String word) {
        foundWords.add(word);
    }
    
    public Set<String> getFoundWords() {
        return foundWords;
    }
}
