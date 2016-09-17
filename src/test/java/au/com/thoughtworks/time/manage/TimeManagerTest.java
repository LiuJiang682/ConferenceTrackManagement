package au.com.thoughtworks.time.manage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.SortedMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;

/**
 * In order to to organized the conference
 * As the conference manager I like to
 * arrange the presentation in a long 
 * Presentation then follow by a 30miin
 * block presentation.
 */
public class TimeManagerTest {

	private TimeManager testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new TimeManager();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	
	@Ignore
	@Test
	public void whenPresentTimeCategoriesProvidedThenConferenceShouldArranged() {
		//Given the sorted time categories
		SortedMap<Integer, List<String>> timeCategories = PresentationFixture.getTimeCategories();
		//When the arrange method called
		List<String> conferencePrograms = testInstance.arrange(timeCategories);
		//Then the conference should be arranged
		assertNotNull(conferencePrograms);
		assertFalse(conferencePrograms.isEmpty());
	}
}
