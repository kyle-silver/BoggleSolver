package dev.kylesilver.boggle.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private FileReader() { }

    public static List<String> getDictionary(String path) {
        List<String> words;
        try {
            words = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            words = new ArrayList<>();
        }
        return words;
    }

    public static char[][] getBoard(String path) {
        List<String> rows = getRows(path);
        char[][] board = new char[rows.size()][rows.get(0).length()];
        for (int i = 0; i < rows.size(); i++) {
            board[i] = rows.get(i).toCharArray();
        }
        return board;
    }

    private static List<String> getRows(String path) {
        List<String> words;
        try {
            words = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            words = new ArrayList<>();
        }
        return words;
    }

}
