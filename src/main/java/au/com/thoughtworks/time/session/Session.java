package au.com.thoughtworks.time.session;

public interface Session {

	int getAvailableTime();
	int getMinimumTime();
	boolean isNextTalkShort();
	void addNextTalk(final Integer time, final String title);
	String getProgramDetails();
}
