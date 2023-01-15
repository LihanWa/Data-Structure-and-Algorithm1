package cs445.rec05;

import org.junit.jupiter.api.Test;

import cs445.rec05.UndoableBag;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


/**
 * A class that implements simple JUnit tests for the UndoableBag class.
 * @author Brian Nixon
 * @author William C. Garrison III
 */
public class UndoableBagTest {
    // Initialize any global reference variables/final values
    UndoableBag<String> bag;
    final String[] duplicateWords = {"Stacks", "are", "Last", "In", "First", "Out", "data", "structures"};

    /**
     * This method is called before each test. Used to initialize
     * all global variables before tests
     */
    @BeforeEach
    public void setup() {
        bag = new UndoableBag<String>();
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
     * Tests the add method for undoable bag
     */
    @Test
    public void testAdd() {
        for (String s : duplicateWords) {
            assertTrue(bag.add(s));
        }

        assertEquals(duplicateWords.length, bag.getCurrentSize());
        for (String s : duplicateWords) {
            assertTrue(bag.contains(s));
        }
    }

    /**
     * Tests the undo method by undoing the steps of testRemove
     */
    @Test
    public void testUndo() {
        for (String s : duplicateWords) {
            assertTrue(bag.add(s));
        }

        assertEquals(duplicateWords.length, bag.getCurrentSize());
        assertTrue(bag.remove("First"));
        assertFalse(bag.contains("First"));
        bag.remove();
        assertEquals(duplicateWords.length - 2, bag.getCurrentSize());

        assertTrue(bag.undo());
        assertTrue(bag.undo());
        assertEquals(duplicateWords.length, bag.getCurrentSize());
        assertTrue(bag.contains("First"));
    }
}

