package au.com.thoughtworks.time.manage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;

/**
 * In order to to organized the conference As the conference manager I like to
 * arrange the presentation in a long Presentation then follow by a 30miin block
 * presentation.
 */
public class TimeManagerTest {

	private static final Logger LOGGER = Logger.getLogger(TimeManagerTest.class);

	private TimeManager testInstance;

	@Before
	public void setUp() {
		this.testInstance = new TimeManager();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
	}

	@Test
	public void whenPresentTimeCategoriesProvidedThenConferenceShouldArranged() {
		// Given the sorted time categories
		SortedMap<Integer, List<String>> timeCategories = PresentationFixture.getTimeCategories();
		// When the arrange method called
		List<String> conferencePrograms = testInstance.arrange(timeCategories, 16);
		// Then the conference should be arranged
		assertNotNull(conferencePrograms);
		assertFalse(conferencePrograms.isEmpty());
		for (String string : conferencePrograms) {
			LOGGER.debug(string);
		}
	}

	@Test
	public void whenPresentTimeCategoriesProvidedThenConferenceShouldBeOrganized() {
		// Given the sorted time categories
		SortedMap<Integer, List<String>> timeCategories = PresentationFixture.getTimeCategories();
		// When the arrange method called
		List<String> conferencePrograms = testInstance.organized(timeCategories, 18);
		// Then the conference should be arranged
		assertNotNull(conferencePrograms);
		assertFalse(conferencePrograms.isEmpty());
		for (String string : conferencePrograms) {
			LOGGER.debug(string);
		}
	}

	@Test
	public void whenPresentationTimeCategoriesProvidedThenTheSizeShouldReturn() {
		// Given the sorted time categories
		SortedMap<Integer, List<String>> timeCategories = PresentationFixture.getTimeCategories();
		// When the getCategoriesSize method called
		Map<Integer, Integer> categoriesSize = testInstance.getCategoriesSize(timeCategories);
		// Then the size return
		assertNotNull(categoriesSize);
		assertFalse(categoriesSize.isEmpty());
		categoriesSize.entrySet().stream()
			.forEach(e -> {
				Integer key = e.getKey();
				if (60 == key) {
					assertTrue(4 == e.getValue());
				}
				else if (45 == key) {
					assertTrue(6 == e.getValue());
				}
				else if (30 == key) {
					assertTrue(7 == e.getValue());
				}
				else if (5 == key) {
					assertTrue(2 == e.getValue());
				}
				else {
					fail("Program reached unexpected point");
				}
			});
	}
	
	@Test
	public void whenPresentationTimeCategoriesAndCounterProvidedThenTheStatusShouldReturn() {
		// Given the sorted time categories
		SortedMap<Integer, List<String>> timeCategories = PresentationFixture.getTimeCategories();
		Map<Integer, Integer> timeCounters = testInstance.getTimeCounter(timeCategories);
		Map<Integer, Integer> categoriesSize = testInstance.getCategoriesSize(timeCategories);
		// When the getCategoriesSize method called
		Map<Integer, Integer> categoriesStatus = testInstance.getStatus(timeCounters, categoriesSize);
		// Then the size return
		assertNotNull(categoriesStatus);
		assertFalse(categoriesStatus.isEmpty());
		categoriesStatus.entrySet().stream()
			.forEach(e -> {
				Integer key = e.getKey();
				if (60 == key) {
					assertTrue(4 == e.getValue());
				}
				else if (45 == key) {
					assertTrue(6 == e.getValue());
				}
				else if (30 == key) {
					assertTrue(7 == e.getValue());
				}
				else if (5 == key) {
					assertTrue(2 == e.getValue());
				}
				else {
					fail("Program reached unexpected point");
				}
			});
	}
}
