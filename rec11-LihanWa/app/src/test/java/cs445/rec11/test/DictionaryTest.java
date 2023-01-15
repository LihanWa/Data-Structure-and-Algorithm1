package cs445.rec11.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import cs445.rec11.MapLandmarks;

/**
 * A class that provides sample tests for MapLandmarks
 * @author William C. Garrison III
 * @author Norhan Abbas
 * @author Brian T. Nixon
 */
public class DictionaryTest {

    private static MapLandmarks testDictionary;

    private static final int LIMIT = 10;
    private static final String timeoutMessage = "Test did not complete within " + LIMIT + " seconds";

    @BeforeEach
    public void setup() {
        testDictionary = new MapLandmarks();
    }

    @AfterEach
    public void teardown() {
        testDictionary = null;
    }

    @Test
    @DisplayName("addLandmark")
    void testAddLandmark() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            testDictionary.addLandmark("this", 1, 2);
            testDictionary.addLandmark("is", 100, 200);
            testDictionary.addLandmark("CS", 1.99998, 2.9997);
            testDictionary.addLandmark("449", 1000.99998, 2000.9997);
            testDictionary.addLandmark("445", 1000.99998, 2000.9997);

            assertEquals("this", testDictionary.getValue(1, 2), "Retrieved value incorrect");
            assertEquals("is", testDictionary.getValue(100, 200), "Retrieved value incorrect");
            assertEquals("CS", testDictionary.getValue(1.99998, 2.9997), "Retrieved value incorrect");
            assertEquals("445", testDictionary.getValue(1000.99998, 2000.9997), "Retrieved value incorrect");
        }, timeoutMessage);
    }

}

