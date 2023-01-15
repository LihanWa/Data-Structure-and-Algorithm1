package cs445.rec07.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs445.rec07.Queens;

import java.time.Duration;

/**
 * A class that provides sample tests of the 8 Queens Program
 * @author William C. Garrison III
 * @author Brian T. Nixon
 * @author Norhan Abbas
 * @author Kaiwen Zhao
 */
public class QueensTest {

    private final int LIMIT = 15;
    private final String timeoutMessage = "Test did not complete within " + LIMIT + " seconds";

    @Test
    @DisplayName("Test isFullSolution()")
    void testIsFullSolution() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            int[][] fullSolutions = new int[][] {
                {2, 4, 6, 8, 3, 1, 7, 5},
                {1, 7, 4, 6, 8, 2, 5, 3},
            };

            int[][] notFullSolutions = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 4, 6, 8, 3, 5, 7, 0},
                {0, 2, 0, 0, 2, 0, 0, 0},
                {0, 2, 0, 0, 1, 0, 0, 2},
                {2, 4, 6, 8, 3, 1, 5, 0},
                {0, 5, 0, 0, 2, 0, 0, 0},
            };

            for (int[] test : fullSolutions) {
                assertTrue(Queens.isFullSolution(test), "Failed to detect a full solution");
            }

            for (int[] test : notFullSolutions) {
                assertFalse(Queens.isFullSolution(test), "Failed to detect a full solution");
            }
        }, timeoutMessage);
    }

    @Test
    @DisplayName("Test extend()")
    void testExtend() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            int[][] noExtend = new int[][] {
                {2, 4, 6, 8, 3, 1, 7, 5},
                {1, 7, 4, 6, 8, 2, 5, 3},
                {1, 7, 4, 6, 8, 2, 5, 2},
                {1, 3, 5, 7, 2, 4, 6, 8},
            };

            int[][] extend = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 4, 6, 8, 3, 5, 7, 0},
                {2, 4, 6, 8, 0, 0, 0, 0},
                {2, 4, 6, 8, 3, 1, 5, 0},
            };

            for (int[] test : noExtend) {
                assertNull(Queens.extend(test), "Failed to detect non-extendable partial solution");
            }

            int[][] results = new int[][] {
                Queens.extend(extend[0]),
                Queens.extend(extend[1]),
                Queens.extend(extend[2]),
                Queens.extend(extend[3])
            };

            for (int[] test : results) {
                assertNotNull(test, "Failed to extend partial solution");
            }

            // check Queen Placement
            assertEquals(1, results[0][0], "extend() placed queen in incorrect position");
            assertEquals(1, results[1][7], "extend() placed queen in incorrect position");
            assertEquals(1, results[2][4], "extend() placed queen in incorrect position");
            assertEquals(1, results[3][7], "extend() placed queen in incorrect position");
        }, timeoutMessage);
    }

}

