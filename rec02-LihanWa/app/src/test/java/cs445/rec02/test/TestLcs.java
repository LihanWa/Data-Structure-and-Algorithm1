package cs445.rec02.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import cs445.rec02.LongestCommonSubsequence;

public class TestLcs {

    // Time limit per test
    public final int LIMIT = 10;

	@Test
	public void testSimple() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            assertEquals("A", LongestCommonSubsequence.solve("BANANA", "CAT"), "LCS incorrect for BANANA and CAT");
        }, "Test did not complete within " + LIMIT + " seconds");
	}

	@Test
	public void testMid() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            assertEquals("COPE", LongestCommonSubsequence.solve("COMPUTER", "SCONEPIE"), "LCS incorrect for COMPUTER and SCONEPIE");
        }, "Test did not complete within " + LIMIT + " seconds");
	}

}

