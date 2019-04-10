package dev.kylesilver.boggle.solvers;

import java.util.Set;

import dev.kylesilver.boggle.board.Board;

public interface BoggleSolver {

    public Set<String> solve(Board board);
    
}
