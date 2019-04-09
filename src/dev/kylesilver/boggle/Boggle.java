package dev.kylesilver.boggle;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.io.FileReader;
import dev.kylesilver.boggle.triesolver.TrieSolver;

import java.io.File;
import java.util.*;

public class Boggle {

    private static final String DICTIONARY_PATH = "./res/dictionary.txt";

    public static void main(String[] args) {
        solveWithTrie();
    }

    static void solveWithTrie() {
        TrieSolver ts = new TrieSolver(FileReader.getDictionary(DICTIONARY_PATH));
        Board board01 = new Board(FileReader.getBoard("./res/boggle01.txt"));

        Set<String> result = ts.findAllWords(board01);

        for (String word : result) {
            System.out.println(word);
        }
    }
}
