package dev.kylesilver.boggle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.kylesilver.boggle.board.Board;
import dev.kylesilver.boggle.io.FileReader;
import dev.kylesilver.boggle.solvers.BoggleSolver;
import dev.kylesilver.boggle.solvers.DictSolver;
import dev.kylesilver.boggle.solvers.TrieSolver;

public class Boggle {

    private static final String DICTIONARY_PATH = "./res/dictionary.txt";

    public static void main(String[] args) {
        List<String> dictionary = FileReader.getDictionary(DICTIONARY_PATH);
        List<Board> boards = loadBoards();
        
        BoggleSolver trieSolver = new TrieSolver(dictionary);
        BoggleSolver dictSolver = new DictSolver(dictionary);
        
        timedSolve(trieSolver, boards.get(0));
        timedSolve(dictSolver, boards.get(0));
   
    }
    
    private static Set<String> timedSolve(BoggleSolver solver, Board board) {
        long start = System.currentTimeMillis();
        Set<String> result = solver.solve(board);
        long end = System.currentTimeMillis(); 
        System.out.println("Found " + result.size() + " words in " + (end-start) + "ms");
        return result;
    }
    
    private static List<Board> loadBoards() {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board.Builder(FileReader.getBoard("./res/boggle01.txt")).build());
        boards.add(new Board.Builder(FileReader.getBoard("./res/boggle02.txt")).build());
        boards.add(new Board.Builder(FileReader.getBoard("./res/boggle03.txt")).build());
        return boards;
    }
}
