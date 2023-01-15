package cs445.a2.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import cs445.a2.Calculator;

/**
 * A class that provides sample test of the Calculator
 * @author William C. Garrison III
 */

public class SampleTest {

    Calculator calc;

    // Time limit per test
    public final int LIMIT = 10;

    /**
     * This method is called after each test. Used to reset all
     * global variables after tests
     */
    @AfterEach
    public void teardown() {
        calc = null;
    }

    @Test
    @DisplayName("Simple test")
    void testSimple() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            calc = new Calculator("8*5");
            assertEquals(40, calc.evaluate());
        }, "Test did not complete within " + LIMIT + " seconds");
    }

    @Test
    @DisplayName("Moderate test")
    void testModerate() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            calc = new Calculator("2 ^ [15 - 4*3]");
            assertEquals(8, calc.evaluate());
        }, "Test did not complete within " + LIMIT + " seconds");
    }

    @Test
    @DisplayName("Complex test")
    void testComplex() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            calc = new Calculator("[1+2.5/2.5]^(35-[6*5.15])+4^2");
            assertEquals(33.14837540058066, calc.evaluate());
        }, "Test did not complete within " + LIMIT + " seconds");
    }

}

