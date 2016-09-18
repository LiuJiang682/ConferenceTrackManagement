package au.com.thoughtworks.time.manage.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;

public class StrategyImplTest {

	@Test
	public void whenMapProvidedThenAStrategyObjectShouldConstructed() {
		//Given a Map object
		Map<Integer, Integer> entries = new HashMap<>();
		//When the constructor called
		StrategyImpl strategy = new StrategyImpl(entries);
		//Then the strategy object return
		assertNotNull(strategy);
	}
	
	@Test
	public void whenNullMapProvidedThenAExceptionShouldRaise() {
		//Given a Null Map object
		Map<Integer, Integer> entries = null;
		//When the constructor called
		try {
			new StrategyImpl(entries);
			fail("program reached unexpected point!");
		}
		catch (IllegalArgumentException e) {
			//Then the exception raised
			String message = e.getMessage();
			assertNotNull(message);
			assertEquals("Entries map cannot be null!", message);
		}
	}
	
	@Test
	public  void whenAStrategyProvidedThenExecuteShouldExecuteAccordingly() {
		Map<Integer, Integer> entries = PresentationFixture.getStrategyEntries();
		StrategyImpl testInstance = new StrategyImpl(entries);
		//When the execute method called
		String result = testInstance.execute(PresentationFixture.getMorningSession(), 
				PresentationFixture.getTimeCounters(), 
				PresentationFixture.getFullTimeCategories());
		//Then the strategy should executed
		assertTrue(4 == testInstance.getOrganizedPresentations());
		assertNotNull(result);
		System.out.println(result);
	}
}
