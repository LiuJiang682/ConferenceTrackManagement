package au.com.thoughtworks.builder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.BiConsumer;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;

//In order to organize the conference
//As the conference manager, I like to
//sort out all presentations according
//to their time length
public class PresentationBuilderImplTest {

	private static final Logger LOGGER = Logger.getLogger(PresentationBuilderImplTest.class);

	private PresentationBuilderImpl testInstance;

	@Before
	public void setUp() {
		this.testInstance = new PresentationBuilderImpl();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
	}

	@Test
	public void whenAListProvidedThenASortedMapShouldReturn() {
		// Given a presentation title list
		List<String> presentations = PresentationFixture.getTitleList();
		// When the buildPresentationCategories method called
		SortedMap<String, List<String>> timeCategories = testInstance.doPresentationCategories(presentations);
		// Then all presentation sorted according to time
		assertNotNull(timeCategories);
		assertFalse(timeCategories.isEmpty());

		timeCategories.forEach(new BiConsumer<String, List<String>>() {

			@Override
			public void accept(String t, List<String> u) {
				LOGGER.debug("key: " + t + ", List<String>: " + u);
			}
		});
	}

	@Test
	public void whenLightningProvidedThenReplaceWith5Min() {
		// Given the time categories
		Map<String, List<String>> timeCategories = PresentationFixture.getLightningTimeCategories();
		// When the replaceLightning method called
		Map<String, List<String>> allTimeCategories = ((PresentationBuilderImpl) testInstance)
				.replaceLightning(timeCategories);
		// Then the lightning key has been replaced
		assertNotNull(allTimeCategories);
		assertFalse(allTimeCategories.isEmpty());
		assertFalse(allTimeCategories.containsKey("lightning"));
	}

	@Test
	public void whenMixTitleProvidedThenBuilderShouldFilterOUt() {
		// Given a presentation title list
		List<String> presentations = PresentationFixture.getMixTitleList();
		// When the buildPresentationCategories method called
		SortedMap<String, List<String>> timeCategories = testInstance.doPresentationCategories(presentations);
		// Then the all invalid key have been filter out
		assertNotNull(timeCategories);
		assertFalse(timeCategories.isEmpty());
		assertFalse(timeCategories.containsKey("lightning"));
		assertFalse(timeCategories.containsKey("abc"));
		assertTrue(4 == timeCategories.size());
	}

	@Test
	public void whenCorrectMinutesFormatKeyProvidedThenTrueShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.getMinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertTrue(flag);
	}

	@Test
	public void when0MinutesFormatKeyProvidedThenTrueShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.get0MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertTrue(flag);
	}

	@Test
	public void when999MinutesFormatKeyProvidedThenTrueShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.get999MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertTrue(flag);
	}

	@Test
	public void whenMinutesFormatKeyProvidedThenFalseShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.getNoDigitMinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertFalse(flag);
	}

	@Test
	public void when1000MinutesFormatKeyProvidedThenFalseShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.get1000MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertFalse(flag);
	}

	@Test
	public void whenLeadSpace10MinutesFormatKeyProvidedThenFalseShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.getLeadSpace10MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertFalse(flag);
	}

	@Test
	public void whenTailSpace10MinutesFormatKeyProvidedThenFalseShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.getTailSpace10MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertFalse(flag);
	}

	@Test
	public void whenLeadTailSpace10MinutesFormatKeyProvidedThenFalseShouldReturn() {
		// Given the minute format key
		Map.Entry<String, List<String>> e = PresentationFixture.getLeadTailSpace10MinutesFormateKey();
		// When the isMinuteFormat predicate called
		boolean flag = PresentationBuilderImpl.isMinutesFormat().test(e);
		// Then true should be return
		assertFalse(flag);
	}

	@Test
	public void whenAListProvidedThenASortedMapWithTimeAsKeyShouldReturn() {
		// Given a presentation title list
		List<String> presentations = PresentationFixture.getTitleList();
		// When the buildPresentationCategories method called
		SortedMap<Integer, List<String>> timeCategories = testInstance.buildPresentationCategories(presentations);
		// Then all presentation sorted according to time
		assertNotNull(timeCategories);
		assertFalse(timeCategories.isEmpty());

		timeCategories.forEach(new BiConsumer<Integer, List<String>>() {

			@Override
			public void accept(Integer t, List<String> u) {
				LOGGER.debug("key: " + t + ", List<String>: " + u);
			}
		});
	}
	
	@Test
	public void whenATimeMapProvidedThenASortedMapWithTimeAsKeyShouldReturn() {
		// Given a presentation title list
		SortedMap<String, List<String>> presentations = PresentationFixture.getStringTimeTitleList();
		// When the buildPresentationCategories method called
		SortedMap<Integer, List<String>> timeCategories = testInstance.doCategoriesKeyConvertion(presentations);
		// Then all presentation sorted according to time
		assertNotNull(timeCategories);
		assertFalse(timeCategories.isEmpty());

		timeCategories.forEach(new BiConsumer<Integer, List<String>>() {

			@Override
			public void accept(Integer t, List<String> u) {
				LOGGER.debug("key: " + t + ", List<String>: " + u);
			}
		});
	}
	
	@Test
	public void whenAMinTimeStringProvidedThenAnIntegerShouldReturn() {
		//Given a min string
		String time = "10min";
		//When the toInteger function called
		Integer integer = PresentationBuilderImpl.toInteger().apply(time);
		//Then the integer return
		assertNotNull(integer);
		assertTrue(10 == integer);
	}
}
