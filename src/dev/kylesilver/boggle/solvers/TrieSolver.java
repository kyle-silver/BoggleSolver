package dev.kylesilver.boggle.solvers;

import java.util.List;
import java.util.Set;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.board.Tile;

/**
 * Constructs a Trie from the provided dictionary, then performs a Depth-First
 * Search on the board to find all words.
 * 
 * @author Kyle Silver
 */
public class TrieSolver implements BoggleSolver {

    private TrieNode root;

    public TrieSolver(List<String> dictionary) {
        root = TrieNode.buildTrie(dictionary);
    }

    public Set<String> solve(Board board) {
        SearchState state = new SearchState();
        for (Tile tile : board.tiles()) {
            searchFrom(tile, root, state);
        }
        return state.getFoundWords();
    }

    private void searchFrom(Tile tile, TrieNode node, SearchState state) {
        if (!node.containsKey(tile.value())) {
            return;
        }
        
        TrieNode next = node.getChild(tile.value());
        if (next.isTerminator()) {
            state.addWord(next.getWord());
        }
        
        state.addToVisited(tile);
        for (Tile neighbor : state.unvisitedNeighbors(tile)) {
            searchFrom(neighbor, next, state);
        }
        state.removeFromVisited(tile);
    }
}
