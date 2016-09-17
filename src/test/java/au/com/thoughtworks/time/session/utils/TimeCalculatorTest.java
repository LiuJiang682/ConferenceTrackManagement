package au.com.thoughtworks.time.session.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.com.thoughtworks.time.session.utils.TimeCalculator;

public class TimeCalculatorTest {

	@Test
	public void when60MinutesProvidedThenNewTimeAdd1Hour() {
		//Given time is 60 min
		int time = 60;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:00AM", time);
		//Then update 1 hour
		assertNotNull(newTime);
		assertEquals("10:00AM", newTime);
	}
	
	@Test
	public void when123MinutesProvidedThenNewTimeAdd2Hour() {
		//Given time is 123 min
		int time = 123;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:00AM", time);
		//Then update 1 hour
		assertNotNull(newTime);
		assertEquals("11:03AM", newTime);
	}
	
	@Test
	public void when45MinutesProvidedWith30MinutesPassedThenNewTimeAdd1Hour() {
		//Given time is 45 min
		int time = 45;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:30AM", time);
		//Then update 1 hour
		assertNotNull(newTime);
		assertEquals("10:15AM", newTime);
	}
	
	@Test
	public void when45MinutesProvidedWith0MinutesPassedThenNewTimeShouldReturn() {
		//Given time is 45 min
		int time = 45;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:00AM", time);
		//Then update minutes
		assertNotNull(newTime);
		assertEquals("09:45AM", newTime);
	}
	
	@Test
	public void when45MinutesProvidedWith15MinutesPassedThenNewTimeAdd1Hour() {
		//Given time is 45 min
		int time = 45;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:15AM", time);
		//Then update 1 hour
		assertNotNull(newTime);
		assertEquals("10:00AM", newTime);
	}
	
	@Test
	public void when30MinutesProvidedWith0MinutesPassedThenNewTimeUpdated() {
		//Given time is 30 min
		int time = 30;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:00AM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("09:30AM", newTime);
	}
	
	@Test
	public void when30MinutesProvidedWith15MinutesPassedThenNewTimeUpdated() {
		//Given time is 30 min
		int time = 30;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("09:15AM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("09:45AM", newTime);
	}
	
	@Test
	public void when30MinutesProvidedWith30MinutesPassedThenNewTimeUpdated() {
		//Given time is 30 min
		int time = 30;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("01:30PM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("02:00PM", newTime);
	}
	
	@Test
	public void when30MinutesProvidedWith45MinutesPassedThenNewTimeAdd1Hour() {
		//Given time is 30 min
		int time = 30;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("01:45PM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("02:15PM", newTime);
	}
	
	@Test
	public void when5MinutesProvidedWith0MinutesPassedThenNewTimeUpdated() {
		//Given time is 5 min
		int time = 5;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("10:00AM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("10:05AM", newTime);
	}
	
	@Test
	public void when5MinutesProvidedWith55MinutesPassedThenNewTimeAdded1Hour() {
		//Given time is 5 min
		int time = 5;
		//When the newTime method called
		String newTime = TimeCalculator.newTime("10:55AM", time);
		//Then update minute
		assertNotNull(newTime);
		assertEquals("11:00AM", newTime);
	}
}
