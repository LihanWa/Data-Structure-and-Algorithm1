package cs445.rec01.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs445.rec01.Status;

public class TestStatus {

	@Test
	public void testStatusReturn() {
		assertEquals("DONE", Status.current(), "Does not return DONE");
	}

}

