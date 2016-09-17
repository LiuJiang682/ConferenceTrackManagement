package au.com.thoughtworks.time.session;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * In order to manage to conference
 * As the conference manager I like
 * to create the session in morning
 * following afternoon fashion.
 */
public class SessionFactoryTest {

	@Test
	public void whenTheConstructSessionCalledThenReturnMorningThenAfternoonSession() {
		//Given user can access to SessionFactory
		//When the nextSession method called
		Session session = SessionFactory.getNextSession();
		//Then morning session return
		assertNotNull(session);
		assertTrue(session instanceof MorningSession);
		//Call the nextSession method again.
		session = SessionFactory.getNextSession();
		//Then the afternoon session should be return
		assertNotNull(session);
		assertTrue(session instanceof AfternoonSession);
		//Third time call the next session
		session = SessionFactory.getNextSession();
		//Then morning session return
		assertNotNull(session);
		assertTrue(session instanceof MorningSession);
	}
}
