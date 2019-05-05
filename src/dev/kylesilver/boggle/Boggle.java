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

    public static void main(String[] args) {
        List<String> dictionary = FileReader.getDictionary(DICTIONARY_PATH);
        List<Board> boards = loadBoards(BOARDS_PATH);
        
        BoggleSolver trieSolver = new TrieSolver(dictionary);
        
        boards.forEach(board -> timedSolve(trieSolver, board));
        
    }
    
    private static Set<String> timedSolve(BoggleSolver solver, Board board) {
        long start = System.currentTimeMillis();
        Set<String> result = solver.solve(board);
        long end = System.currentTimeMillis();
        System.out.println("Found " + result.size() + " words in " + (end-start) + "ms");
        return result;
    }
    
    private static List<Board> loadBoards(String boardDirectoryPath) {
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
        }
        return boards;
    }
}
