package cs445.a3;

import java.util.List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SudokuFromFile {

    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("build/resources/main/" + filename), Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Error reading " + filename);
            e.printStackTrace();
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (NumberFormatException e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

    public static void main(String[] args) {
        if(args.length >= 1) {
            int[][] board = readBoard(args[0]);
            System.out.println("Initial board:");
            SudokuSolver.printBoard(board);
            System.out.println("Solution:");
            SudokuSolver solver = new SudokuSolver(board);
            SudokuSolver.printBoard(solver.solve());
        } else {
            System.err.println("No arguments. Check usage instructions.");
        }
    }
}

