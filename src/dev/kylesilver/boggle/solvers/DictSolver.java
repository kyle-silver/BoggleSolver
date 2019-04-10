package dev.kylesilver.boggle.solvers;

import java.util.HashSet;
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
        Set<String> foundWords = new HashSet<>();
        for (String word : dictionary) {
            searchForWord(word, board, foundWords);
        }
        return foundWords;
    }

    private static void searchForWord(String word, Board board, Set<String> foundWords) {
        for (Tile tile : board.tiles()) {
            searchFrom(tile, board, foundWords, word, 0);
        }
    }

    private static void searchFrom(Tile tile, Board board, Set<String> foundWords, String word, int index) {
        if (index >= word.length()) {
            return;
        }
        if (tile.value() != word.charAt(index)) {
            return;
        }
        if (index == word.length() - 1) {
            foundWords.add(word);
            return;
        }
        board.addToVisited(tile);
        for (Tile neighbor : board.unvisitedNeighbors(tile)) {
            searchFrom(neighbor, board, foundWords, word, index+1);
        }
        board.removeFromVisited(tile);
    }
}
