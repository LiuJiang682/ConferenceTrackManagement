package au.com.thoughtworks.time.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.thoughtworks.constants.Constants;

public class AfternoonSessionTest {

	private AfternoonSession testInstance;

	@Before
	public void setUp() {
		this.testInstance = new AfternoonSession();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
	}

	@Test
	public void whenTheConstructorCalledThenAllAttributesInitialized() {
		// Given the user can access the morning session object
		// When the MorningSession constructor called
		// Then all attributes should be initialized.
		assertInitialValues();
	}

	@Test
	public void whenTheAddNextTalkCalledWithCorrectTimeThenAllAttributesShouldUpdated() {
		// Given the MorningSession object and next talk data
		Integer time = 60;
		String title = "How to install Kali in full encryted partition 60min";
		// When addNextTalk method called
		this.testInstance.addNextTalk(time, title);
		// Then all attributes should updated
		assertTime120Minutes();
		assertTrue(this.testInstance.isNextTalkShort());
		assertEquals("01:00PM How to install Kali in full encryted partition 60min" + SessionBase.DELIM,
				this.testInstance.getProgramDetails());
	}

	@Test
	public void whenTheAddNextTalkCalledWith3CorrectTimeThenAllAttributesShouldUpdated() {
		// Given the MorningSession object and next talk data
		Integer time = 60;
		String title = "How to install Kali in full encryted partition 60min";
		// When addNextTalk method called
		this.testInstance.addNextTalk(time, title);
		assertTime120Minutes();
		assertTrue(this.testInstance.isNextTalkShort());
		assertEquals("01:00PM How to install Kali in full encryted partition 60min" + SessionBase.DELIM,
				this.testInstance.getProgramDetails());

		// Given the time to 45min
		time = 45;
		title = "AngularJS custom directive 45min";
		// When AddNextTalk method called
		this.testInstance.addNextTalk(time, title);
		// Then all attributes should updated
		assertTrue(135 == this.testInstance.getAvailableTime());
		assertTrue(75 == this.testInstance.getMinimumTime());
		assertTrue(this.testInstance.isNextTalkShort());
		assertEquals(
				"01:00PM How to install Kali in full encryted partition 60min" + SessionBase.DELIM
						+ "02:00PM AngularJS custom directive 45min" + SessionBase.DELIM,
				this.testInstance.getProgramDetails());

		// Given the time to 30min
		time = 30;
		title = "Docker ABc 30min";
		// When AddNextTalk method called
		this.testInstance.addNextTalk(time, title);
		// Then all attributes should updated
		assertTrue(105 == this.testInstance.getAvailableTime());
		assertTrue(45 == this.testInstance.getMinimumTime());
		assertFalse(this.testInstance.isNextTalkShort());
		assertEquals(
				"01:00PM How to install Kali in full encryted partition 60min" + SessionBase.DELIM
						+ "02:00PM AngularJS custom directive 45min" + SessionBase.DELIM
						+ "02:45PM Docker ABc 30min" + SessionBase.DELIM,
				this.testInstance.getProgramDetails());
	}

	@Test
	public void whenTheAddNextTalkCalledWithInvalidTimeThenAllAttributesShouldNotBeUpdated() {
		// Given the MorningSession object and next invalid talk data
		assertInitialValues();
		Integer time = 260;
		String title = "How to install Kali in full encryted partition 60min";
		// When addNextTalk method called
		try {
			this.testInstance.addNextTalk(time, title);
			fail("Program reached unexpected point!");
		} catch (IllegalArgumentException e) {
			String errorMessage = e.getMessage();
			assertNotNull(errorMessage);
			assertEquals("Invalid presentation time: 260, max availble time: 240", errorMessage);
		}
		// Then all attributes should NOT be updated
		this.assertInitialValues();
	}

	private void assertInitialValues() {
		assertNotNull(this.testInstance);
		assertTrue(240 == this.testInstance.getAvailableTime());
		assertTrue(180 == this.testInstance.getMinimumTime());
		assertFalse(this.testInstance.isNextTalkShort());
		assertEquals(Constants.Strings.EMPTY, this.testInstance.getProgramDetails());
	}
	
	private void assertTime120Minutes() {
		assertTrue(180 == this.testInstance.getAvailableTime());
		assertTrue(120 == this.testInstance.getMinimumTime());
	}
}
