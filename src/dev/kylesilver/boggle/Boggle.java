package dev.kylesilver.boggle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.io.FileReader;
import dev.kylesilver.boggle.solvers.BoggleSolver;
import dev.kylesilver.boggle.solvers.TrieSolver;

public class Boggle {

    private static final String DICTIONARY_PATH = "./res/dictionary.txt";
    private static final String BOARDS_PATH     = "./res/boards/";

    public static void main(String[] args) throws IOException {
        String dictPath = setDictionaryPath(args);
        String boardsPath = setBoardsPath(args);

        List<String> dictionary = FileReader.getDictionary(dictPath);
        List<Board> boards = loadBoards(boardsPath);
        
        BoggleSolver trieSolver = new TrieSolver(dictionary);
        
        boards.forEach(board -> timedSolve(trieSolver, board));
    }

    private static String setDictionaryPath(String[] args) {
        if (args.length == 2) {
            return args[1];
        }
        return DICTIONARY_PATH;
    }

    private static String setBoardsPath(String[] args) {
        if (args.length == 2) {
            return args[0];
        }
        return BOARDS_PATH;
    }
    
    private static Set<String> timedSolve(BoggleSolver solver, Board board) {
        long start = System.currentTimeMillis();
        Set<String> result = solver.solve(board);
        long end = System.currentTimeMillis();
        System.out.println("Found " + result.size() + " words in " + (end-start) + "ms");
        return result;
    }
    
    private static List<Board> loadBoards(String boardDirectoryPath) throws IOException {
        List<Board> boards = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(boardDirectoryPath))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(path -> {
                    Board board = new Board.Builder(path).build();
                    boards.add(board);
                });
        } catch (IOException ioe) {
            System.out.println("Path to boards directory was invalid");
            throw ioe;
        }
        return boards;
    }
}
