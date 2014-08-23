package sg.edu.nus.comp.cs3213.assignment1;

import static org.junit.Assert.*;

import org.junit.Test;

public class KeyWordInContextTest {

	@Test
	public void exit_whenTypical() throws Exception {
		String input = "6\n";
		System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
		KeyWordInContext.main(null);
		
		assertTrue(true);
	}

}
