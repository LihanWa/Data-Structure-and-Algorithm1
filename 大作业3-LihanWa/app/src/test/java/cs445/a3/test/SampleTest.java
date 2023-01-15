package cs445.a3.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import cs445.a3.SudokuSolver;

/**
 * A class that provides sample test of the Calculator
 * @author William C. Garrison III
 */

public class SampleTest {

    public final int LIMIT = 90;

    @Test
    @DisplayName("Trivial test")
    void testTrivial() {
        int[][] start = new int[][] {
            {3, 0, 0, 7, 9, 4, 2, 5, 0},
            {2, 0, 0, 5, 6, 0, 1, 9, 7},
            {7, 9, 0, 8, 2, 0, 4, 0, 3},
            {0, 2, 0, 0, 7, 8, 0, 4, 1},
            {5, 7, 0, 1, 0, 6, 0, 8, 2},
            {1, 4, 8, 3, 0, 2, 9, 7, 6},
            {9, 0, 7, 0, 8, 5, 6, 0, 4},
            {4, 5, 2, 6, 0, 7, 8, 3, 9},
            {0, 1, 6, 4, 3, 9, 7, 0, 5},
        };
        int[][] expected = new int[][] {
            {3, 6, 1, 7, 9, 4, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 1},
            {5, 7, 9, 1, 4, 6, 3, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 6},
            {9, 3, 7, 2, 8, 5, 6, 1, 4},
            {4, 5, 2, 6, 1, 7, 8, 3, 9},
            {8, 1, 6, 4, 3, 9, 7, 2, 5},
        };
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            SudokuSolver solver = new SudokuSolver(start);
            int[][] result = solver.solve();
            assertArrayEquals(expected, result, "Incorrect result");
        }, "Did not complete within " + LIMIT + " seconds");
    }

    @Test
    @DisplayName("Medium test")
    void testMedium() {
        int[][] start = new int[][] {
            {4, 0, 0, 0, 0, 2, 8, 0, 0},
            {0, 0, 0, 0, 9, 8, 0, 0, 1},
            {3, 0, 0, 7, 0, 0, 0, 2, 6},
            {0, 4, 9, 5, 0, 3, 0, 0, 8},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {6, 0, 0, 8, 0, 1, 9, 3, 0},
            {5, 7, 0, 0, 0, 9, 0, 0, 2},
            {9, 0, 0, 1, 6, 0, 0, 0, 0},
            {0, 0, 6, 2, 0, 0, 0, 0, 9},
        };
        int[][] expected = new int[][] {
            {4, 1, 7, 6, 3, 2, 8, 9, 5},
            {2, 6, 5, 4, 9, 8, 3, 7, 1},
            {3, 9, 8, 7, 1, 5, 4, 2, 6},
            {1, 4, 9, 5, 7, 3, 2, 6, 8},
            {7, 8, 3, 9, 2, 6, 1, 5, 4},
            {6, 5, 2, 8, 4, 1, 9, 3, 7},
            {5, 7, 1, 3, 8, 9, 6, 4, 2},
            {9, 2, 4, 1, 6, 7, 5, 8, 3},
            {8, 3, 6, 2, 5, 4, 7, 1, 9},
        };
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            SudokuSolver solver = new SudokuSolver(start);
            int[][] result = solver.solve();
            assertArrayEquals(expected, result, "Incorrect result");
        }, "Did not complete within " + LIMIT + " seconds");
    }

    @Test
    @DisplayName("Evil test")
    void testEvil() {
        int[][] start = new int[][] {
            {4, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 0, 2, 3, 8, 0},
            {0, 0, 2, 0, 0, 8, 0, 5, 0},
            {2, 0, 0, 0, 0, 0, 5, 4, 0},
            {0, 0, 5, 0, 7, 0, 6, 0, 0},
            {0, 3, 9, 0, 0, 0, 0, 0, 1},
            {0, 6, 0, 8, 0, 0, 2, 0, 0},
            {0, 7, 4, 9, 0, 6, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
        };
        int[][] expected = new int[][] {
            {4, 8, 6, 5, 1, 3, 9, 7, 2},
            {7, 5, 1, 6, 9, 2, 3, 8, 4},
            {3, 9, 2, 7, 4, 8, 1, 5, 6},
            {2, 1, 7, 3, 6, 9, 5, 4, 8},
            {8, 4, 5, 2, 7, 1, 6, 3, 9},
            {6, 3, 9, 4, 8, 5, 7, 2, 1},
            {1, 6, 3, 8, 5, 4, 2, 9, 7},
            {5, 7, 4, 9, 2, 6, 8, 1, 3},
            {9, 2, 8, 1, 3, 7, 4, 6, 5},
        };
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            SudokuSolver solver = new SudokuSolver(start);
            int[][] result = solver.solve();
            assertArrayEquals(expected, result, "Incorrect result");
        }, "Did not complete within " + LIMIT + " seconds");
    }

}

