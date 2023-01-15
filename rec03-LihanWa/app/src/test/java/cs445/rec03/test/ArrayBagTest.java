package cs445.rec03.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import cs445.rec03.ArrayBag;
import cs445.rec03.BagInterface;

/**
 * A class that implements simple JUnit tests for the ArrayBag class.
 * @author William C. Garrison III
 * @author Brian Nixon
 */

public class ArrayBagTest {
    // Initialize any global reference variables/final values
    BagInterface<String> bag;
    final String[] duplicateWords = {"data", "structures", "are", "fun", "programs", "need", "data", "foobar"};

    // Time limit per test
    public final int LIMIT = 10;

    /**
     * This method is called before each test. Used to initialize
     * all global variables before tests
     */
    @BeforeEach
    public void setup() {
        bag = new ArrayBag<String>();
    }

    /**
     * This method is called after each test. Used to reset all
     * global variables after tests
     */
    @AfterEach
    public void teardown() {
        bag = null;
    }

    /**
     * Test case for equals method.
     *
     * Preconditions: Add duplicateWords to bag and bagTwo. Add "Garrison"
     *      to bagThree.
     * Execution Steps: Call bag.equals(bagTwo), call bag.equals(bagThree),
     *      call bagTwo.equals(bagThree), bagTwo.equals(bag)
     * Postconditions: The return of each method call should be:
     *      true, false, false, true
     */
    @Test
    public void testEquals() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            BagInterface<String> bagTwo = new ArrayBag<String>();
            BagInterface<String> bagThree = new ArrayBag<String>();

            for (String word : duplicateWords) {
                assertTrue(bag.add(word));
            }

            Util.shuffle(duplicateWords);   //shuffle the order of words
            for (String word : duplicateWords) {
                assertTrue(bagTwo.add(word));
            }

            assertTrue(bagThree.add("Garrison"));

            // Execution Steps
            assertTrue(bag.equals(bagTwo));
            assertFalse(bag.equals(null));
            assertFalse(bag.equals("A string"));
            assertFalse(bag.equals(bagThree));
            assertFalse(bagTwo.equals(bagThree));
            assertTrue(bagTwo.equals(bag));
        }, "Test did not complete within " + LIMIT + " seconds");
    }

    /**
     * Test case for removeDuplicatesOf method
     *
     * Preconditions: Add duplicateWords to bag.
     * Execution Steps: Call bag.getFrequencyOf("data"), call
     *      bag.removeDuplicatesOf("data"), call bag.getFrequencyOf("data")
     * Postconditions: The return of the calls to getFrequencyOf should be:
     *      2, 1
     */
    @Test
    public void testRemoveDuplicatesOf() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            for (String word : duplicateWords) {
                assertTrue(bag.add(word));
            }

            // Execution Steps
            assertEquals(2, bag.getFrequencyOf("data"));
            bag.removeDuplicatesOf("data");
            assertEquals(1, bag.getFrequencyOf("data"));
        }, "Test did not complete within " + LIMIT + " seconds");
    }

    /**
     * Test case for remove duplicates.
     *
     * Preconditions: Add duplicateWords to bag.
     * Execution Steps: Call bag.getCurrentSize(), call bag.removeAllDuplicates(),
     *      call bag.getCurrentSize(), call bag.getFrequencyOf("data")
     * Postconditions: The return of each method call should be:
     *      24, 7, 1
     */
    @Test
    public void testRemoveAllDuplicates() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            for (String word : duplicateWords) {
                assertTrue(bag.add("data"));
                assertTrue(bag.add(word));  //assert that each insertion is successful
                assertTrue(bag.add("data"));
            }

            // Execution Steps
            assertEquals(24, bag.getCurrentSize(), "Bag should have 24 elements before removing duplicates");
            bag.removeAllDuplicates();
            assertEquals(7, bag.getCurrentSize(), "Bag should have 7 elements after removing duplicates");
            assertEquals(1, bag.getFrequencyOf("data"), "Bag should only have 1 instance of \"data\"");
        }, "Test did not complete within " + LIMIT + " seconds");
    }

}

