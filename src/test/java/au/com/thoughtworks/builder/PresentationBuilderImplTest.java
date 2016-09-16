package au.com.thoughtworks.builder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
			
	private PresentationBuilder testInstance;
	
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
		//Given a presentation title list
		List<String> presentations = PresentationFixture.getTitleList();
		//When the buildPresentationCategories method called
		SortedMap<String, List<String>> timeCategories = testInstance.buildPresentationCategories(presentations);
		//Then all presentation sorted according to time
		assertNotNull(timeCategories);
		assertFalse(timeCategories.isEmpty());
		
		timeCategories.forEach(new BiConsumer<String, List<String>>() {

			@Override
			public void accept(String t, List<String> u) {
				LOGGER.debug("key: " + t + ", List<String>: " + u );	
			}
		});
	}
	
	@Test
	public void whenLightningProvidedThenReplaceWith5Min() {
		//Given the time categories
		Map<String, List<String>> timeCategories = PresentationFixture.getLightningTimeCategories();
		//When the replaceLightning method called
		Map<String, List<String>> allTimeCategories = ((PresentationBuilderImpl)testInstance).replaceLightning(timeCategories);
		//Then the lightning key has been replaced
		assertNotNull(allTimeCategories);
		assertFalse(allTimeCategories.isEmpty());
		assertFalse(allTimeCategories.containsKey("lightning"));
	}
}
