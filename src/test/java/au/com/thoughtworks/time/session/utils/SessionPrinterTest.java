package au.com.thoughtworks.time.session.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;
import au.com.thoughtworks.time.session.Session;
import au.com.thoughtworks.time.session.SessionBase;

public class SessionPrinterTest {

	@Test
	public void whenTheMorningSessionProvidedThenLunchMessageAppear() {
		//Given the session is morning session
		Session session = PresentationFixture.getMorningSession();
		//When the print method called
		String message = SessionPrinter.print(session);
		//Then lunch message appear
		assertNotNull(message);
		assertEquals("12:00PM Lunch", message);
	}
	
	@Test
	public void whenTheAfternoonSessionProvidedThenNetworkMessageAppear() {
		//Given the session is morning session
		Session session = PresentationFixture.getAfternoonSession();
		//When the print method called
		String message = SessionPrinter.print(session);
		//Then lunch message appear
		assertNotNull(message);
		assertEquals("05:00PM Networking Event" + SessionBase.DELIM, message);
	}
}
