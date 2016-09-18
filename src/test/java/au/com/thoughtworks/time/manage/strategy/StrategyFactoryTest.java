package au.com.thoughtworks.time.manage.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;
import au.com.thoughtworks.time.session.Session;

public class StrategyFactoryTest {

	@Test
	public void whenMorningSessionAndStatusProvidedThenStrategyShouldReturn() {
		//Given the Session and status
		Session session = PresentationFixture.getMorningSession();
		Map<Integer, Integer> status = PresentationFixture.getStatus();
		//When the createStrategy called
		Strategy strategy = StrategyFactory.createStrategy(session, status);
		//Then a strategy should return
		assertNotNull(strategy);
	}
	
	@Test
	public void whenMorningSessionAnd1HourStatusProvidedThenStrategyShouldReturn() {
		//Given the Session and status
		Session session = PresentationFixture.getMorningSession();
		Map<Integer, Integer> status = PresentationFixture.getStatus();
		status.remove(60);
		status.put(60, 1);
		//When the createStrategy called
		Strategy strategy = StrategyFactory.createStrategy(session, status);
		//Then a strategy should return
		assertNotNull(strategy);
	}
	
	@Test
	public void when2HoursProvidedThenTrueShouldReturn() {
		//Given the status 
		Map<Integer, Integer> status = PresentationFixture.getStatus();
		//When the canDoTwoHours method called
		boolean flag = StrategyFactory.canDoTwoHours(status);
		//Then true should return
		assertTrue(flag);
	}
	
	@Test
	public void whenNO2HoursProvidedThenFalseShouldReturn() {
		//Given the status 
		Map<Integer, Integer> status = PresentationFixture.getStatus();
		status.remove(60);
		//When the canDoTwoHours method called
		boolean flag = StrategyFactory.canDoTwoHours(status);
		//Then true should return
		assertFalse(flag);
	}
	
	@Test
	public void when2ThirtyProvidedThenTrueShouldReturn() {
		//Given the status 
		TreeMap<Integer, Integer> shortPrenstation = givenShortPrenstations();
		//When the canDoTwoHours method called
		boolean flag = StrategyFactory.canDoTwoThirtyMinute(shortPrenstation);
		//Then true should return
		assertTrue(flag);
	}

	
	
	@Test
	public void whenNO2ThirtyProvidedThenFalseShouldReturn() {
		//Given the status 
		TreeMap<Integer, Integer> shortPrenstation = givenShortPrenstations();
		shortPrenstation.remove(30);
		//When the canDoTwoHours method called
		boolean flag = StrategyFactory.canDoTwoHours(shortPrenstation);
		//Then true should return
		assertFalse(flag);
	}
	
	@Test
	public void when38FiveMinutesProvidedThenFive36ShoudReturn() {
		//Give the time length and number of occurs
		Integer timeLength = 5;
		Integer numberOfOccurs = 38;
		Map<Integer, Integer> entries = new HashMap<>();
		//When the findFittedArrangement method called
		Map<Integer, Integer> fittedEntries = StrategyFactory.findFittedArrangement(entries, timeLength, numberOfOccurs, 180, 180);
		//The fitted entries should return
		assertNotNull(fittedEntries);
		assertFalse(fittedEntries.isEmpty());
		assertTrue(1 == fittedEntries.size());
		Map.Entry<Integer, Integer> entry = fittedEntries.entrySet().iterator().next();
		assertTrue(5 == entry.getKey());
		assertTrue(36 == entry.getValue());
	}
	
	private TreeMap<Integer, Integer> givenShortPrenstations() {
		Map<Integer, Integer> status = PresentationFixture.getStatus();
		TreeMap<Integer, Integer> sortedStatus = new TreeMap<>(status);
		TreeMap<Integer, Integer> shortPrenstation = new TreeMap<>(sortedStatus.headMap(31));
		return shortPrenstation;
	}
}
