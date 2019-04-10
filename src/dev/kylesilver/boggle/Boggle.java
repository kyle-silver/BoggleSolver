package dev.kylesilver.boggle;

import java.util.List;
import java.util.Set;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.io.FileReader;
import dev.kylesilver.boggle.triesolver.TrieSolver;

public class Boggle {

    private static final String DICTIONARY_PATH = "./res/dictionary.txt";
    private static final String BOGGLE01_PATH	= "./res/boggle01.txt";

    public static void main(String[] args) {
        solveWithTrie();
    }

    static void solveWithTrie() {
    	List<String> dictionary = FileReader.getDictionary(DICTIONARY_PATH);
    	char[][] input01 = FileReader.getBoard(BOGGLE01_PATH);
    	
        TrieSolver ts = new TrieSolver(dictionary);
        Board board01 = new Board.Builder(input01).build();

        Set<String> result = ts.findAllWords(board01);

        for (String word : result) {
            System.out.println(word);
        }
    }
}
