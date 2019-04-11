package dev.kylesilver.boggle.solvers;

import java.util.List;
import java.util.Set;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.board.Tile;

/**
 * Following the advice of an obscure blog post I can't find anymore, iterates
 * through the dictionary and searches for matches on the board. According to
 * the post's logic, most words will be misses and fail instantly. Since these
 * dead-end searches can be exhausted quickly, we can get comparable results
 * without the memory overhead of a Trie.
 * 
 * Note: this assertion is patently untrue, and this implementation is an
 * order of magnitude slower than the Trie
 *  
 * @author Kyle Silver
 */
public class DictSolver implements BoggleSolver {

    private List<String> dictionary;
    
    public DictSolver(List<String> dictionary) {
        this.dictionary = dictionary;
    }
    
    public Set<String> solve(Board board) {
        SearchState state = new SearchState();
        for (String word : dictionary) {
            searchForWord(word, board, state);
        }
        return state.getFoundWords();
    }

    private static void searchForWord(String word, Board board, SearchState state) {
        for (Tile tile : board.tiles()) {
            searchFrom(tile, word, 0, state);
        }
    }

    private static void searchFrom(Tile tile, String word, int index, SearchState state) {
        if (searchShouldTerminate(tile, word, index)) {
            return;
        }
        if (wordHasBeenFound(word, index)) {
            state.addWord(word);
            return;
        }
        state.addToVisited(tile);
        for (Tile neighbor : state.unvisitedNeighbors(tile)) {
            searchFrom(neighbor, word, index+1, state);
        }
        state.removeFromVisited(tile);
    }
    
    private static boolean searchShouldTerminate(Tile tile, String word, int index) {
        if (index >= word.length()) {
            return true;
        }
        if (tile.value() != word.charAt(index)) {
            return true;
        }
        return false;
    }
    
    private static boolean wordHasBeenFound(String word, int index) {
        return index == word.length() - 1;
    }
}
