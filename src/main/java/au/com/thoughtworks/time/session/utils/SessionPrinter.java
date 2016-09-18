package au.com.thoughtworks.time.session.utils;

import au.com.thoughtworks.time.session.MorningSession;
import au.com.thoughtworks.time.session.Session;
import au.com.thoughtworks.time.session.SessionBase;

public class SessionPrinter {

	private static final String NETWORKING = "05:00PM Networking Event";
	private static final String LUNCH = "12:00PM Lunch";
	
	public static String print(Session session) {
		StringBuilder trackPrograms = new StringBuilder(session.getProgramDetails());
		// If the session is full or reached minimum time, stop!
		if (session instanceof MorningSession) {
			// Morning Session
			trackPrograms.append(LUNCH);
		} else {
			// Afternoon Session
			trackPrograms.append(NETWORKING);
			trackPrograms.append(SessionBase.DELIM);
		}
		
		return trackPrograms.toString();
	}
}
