package cs445.rec04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

/**
 * A class that implements simple JUnit tests for the Analysis class.
 * @author William C. Garrison III
 * @author Brian Nixon
 * @author Kaiwen Zhao
 */

public class AnalysisTest {

    private Analysis worksheet;

    private final int LIMIT = 10;
    private final String timeoutMessage = "Test did not complete within " + LIMIT + " seconds";

    @BeforeEach
    public void setup() {
        worksheet = new Analysis();
    }

    @AfterEach
    public void teardown() {
        worksheet = null;
    }

    /**
     * Test case for Question 1
     */
    @Test
    public void testQ1() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            assertEquals(worksheet.question1[1], worksheet.answer1[0], "Order incorrect");
            assertEquals(worksheet.question1[4], worksheet.answer1[1], "Order incorrect");
            assertEquals(worksheet.question1[0], worksheet.answer1[2], "Order incorrect");
            assertEquals(worksheet.question1[3], worksheet.answer1[3], "Order incorrect");
            assertEquals(worksheet.question1[2], worksheet.answer1[4], "Order incorrect");
        }, timeoutMessage);
    }

    /**
     * Test case for Question 3a
     */
    @Test
    public void testQ3a() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            assertEquals("3^n", worksheet.answer3a, "3a incorrect");
        }, timeoutMessage);
    }

    /**
     * Test case for Question 4a
     */
    @Test
    public void testQ4a() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            assertEquals("O(n)", worksheet.answer4a, "4a incorrect");
            assertNotNull(worksheet.explanation4a, "4a no explanation");
        }, timeoutMessage);
    }

}

