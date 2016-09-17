package au.com.thoughtworks.time.session;

public class SessionFactory {

	//This is not thread safe. It is fit for purpose for now.
	//Further refactor may required if this class is to use
	//in multi-thread environment.
	private static boolean afternoonSession = false;
	
	public static Session getNextSession() {
		Session session = null;
		
		if (afternoonSession) {
			session = new AfternoonSession();
			afternoonSession = false;
		}
		else {
			session = new MorningSession();
			afternoonSession = true;
		}
		return session;
	}

	
}
