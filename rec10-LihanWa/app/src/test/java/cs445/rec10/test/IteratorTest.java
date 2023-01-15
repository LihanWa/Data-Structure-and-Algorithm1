package cs445.rec10.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import cs445.list.ArrayList;
import cs445.list.ListInterface;
import cs445.rec10.IterableUtils;

/**
 * A class that provides sample tests of the Singly-LinkedList
 * @author William C. Garrison III
 * @author Brian T. Nixon
 * @author Norhan Abbas
 */
public class IteratorTest {

    ListInterface<String> stringList;
    ListInterface<Integer> numList;

    // each string's length is the same as its position, starting from 1
    final String[] testStrings = {"1", "22", "333", "4444", "55555", "666666", "7777777"};

    private final int LIMIT = 15;
    private final String timeoutMessage = "Test did not complete within " + LIMIT + " seconds";

    @BeforeEach
    public void setup() {
        stringList = new ArrayList<String>();
        numList = new ArrayList<Integer>();
    }

    @AfterEach
    public void teardown() {
        stringList = null;
        numList = null;
    }

    @Test
    @DisplayName("Test removeShortStrings")
    void testRemoveShortStrings() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            for (String str : testStrings) {
                stringList.add(str);
            }
            IterableUtils.removeShortStrings(stringList, 3);
            assertEquals(5, stringList.getSize(), "removeShortStrings method is not working properly");
        }, timeoutMessage);
    }

    @Test
    @DisplayName("Test removeShortStringsAgain")
    void testRemoveShortStringsAgain() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            for (String str : testStrings) {
                stringList.add(str);
            }
            IterableUtils.removeShortStrings(stringList, 7);
            assertEquals(1, stringList.getSize(), "removeShortStrings method is not working properly");
        }, timeoutMessage);
    }

}

