package au.com.thoughtworks.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * In order to track all conferences As conference manager I like to read all
 * conference presentations into memory.
 *
 */
public class FileContentImplIT {

	private FileContentImpl testInstance;

	@Before
	public void setUp() {
		testInstance = new FileContentImpl();
	}

	@After
	public void tearDown() {
		testInstance = null;
	}

	// Given a correct file name
	// When the getContents method called
	// Then a list of contents return
	@Test
	public void whenFileNameProvidedThenContentShouldReturn() {
		// Given the FileContentImpl object
		// When getContents method called with a correct file name
		Optional<List<String>> contents = testInstance.getContents("src/test/resources/test.txt");
		// Then a populated list should return
		assertNotNull(contents);
		assertTrue(contents.isPresent());
		assertTrue(19 == contents.get().size());
	}

	// Given an invalid file name
	// When the getContents method called
	// Then nothing should return
	@Test
	public void whenInvalidFileNameProvidedTheNothingShouldReturn() {
		// Given the FileContentImpl object
		// When getContents method called with an invalid file name
		Optional<List<String>> contents = testInstance.getContents("abc");
		// Then a populated list should return
		assertNotNull(contents);
		assertFalse(contents.isPresent());
	}
}
