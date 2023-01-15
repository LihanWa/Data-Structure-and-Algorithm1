package cs445.rec01.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs445.rec01.Message;

public class TestMessage {

    PrintStream originalOut = null;
    ByteArrayOutputStream bos = null;

    // Time limit per test
    public final int LIMIT = 10;

    @BeforeEach
    public void setOut() {
		originalOut = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
    }


	@Test
	public void testMessagePrint() {
        assertTimeoutPreemptively(Duration.ofSeconds(LIMIT), () -> {
            // action
            Message.main(null);

            // assertion
            String output = bos.toString();
            assertTrue(output.contains("CS445"), "Output string does not contain CS445");
        }, "Test did not complete within " + LIMIT + " seconds");
	}

    @AfterEach
    public void restore() {
		// undo the binding in System
		System.setOut(originalOut);
    }

}

