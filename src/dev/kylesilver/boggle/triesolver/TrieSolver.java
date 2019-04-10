package dev.kylesilver.boggle.triesolver;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.board.Tile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrieSolver {

    private TrieNode root;

    public TrieSolver(List<String> dictionary) {
        root = TrieNode.buildTrie(dictionary);
    }

    public Set<String> findAllWords(Board board) {
        Set<String> foundWords = new HashSet<>();
        for (Tile tile : board.tiles()) {
        	searchFrom(tile, board, root, foundWords);
        }
        return foundWords;
    }

    private void searchFrom(Tile tile, Board board, TrieNode node, Set<String> foundWords) {
        Set<Tile> neighbors = board.unvisitedNeighbors(tile);
        for (Tile neighbor : neighbors) {
            if (node.containsKey(tile.value())) {
                TrieNode next = node.getChild(tile.value());
                if (next.isTerminator()) {
                    foundWords.add(next.getWord());
                }
                board.addToVisited(neighbor);
                searchFrom(neighbor, board, next, foundWords);
                board.removeFromVisited(neighbor);
            }
        }
    }
}
