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
        for (int row = 0; row < board.numRows(); row++) {
            for (int col = 0; col < board.numCols(); col++) {
                searchFrom(new Tile(row, col), board, foundWords);
            }
        }
        return foundWords;
    }

    private void searchFrom(Tile tile, Board board, Set<String> foundWords) {
        board.clearVisited();
        if (root.containsKey(board.charAt(tile))) {
            board.addToVisited(tile);
            searchFrom(tile, board, root.getChild(board.charAt(tile)), foundWords);
        }
    }

    private void searchFrom(Tile tile, Board board, TrieNode node, Set<String> foundWords) {
        Set<Tile> neighbors = board.unvisitedNeighbors(tile);
        for (Tile neighbor : neighbors) {
            char letter = board.charAt(neighbor);
            if (node.containsKey(letter)) {
                TrieNode next = node.getChild(letter);
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
