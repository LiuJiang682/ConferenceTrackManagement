package au.com.thoughtworks.time.session;

import java.util.List;

public abstract class SessionBase implements Session {

	public static final String DELIM = System.getProperty("line.separator");
	protected static final int SHORT_PRESENTATION_TIME = 30;

	@Override
	public String getProgramDetails() {
		StringBuilder buf = new StringBuilder();
		for (String presentation : getPresentations()) {
			buf.append(presentation);
			buf.append(DELIM);
		}
		return buf.toString();
	}
	
	public void isValidTimeLimit(Integer time) {
		if (getAvailableTime() < time) {
			throw new IllegalArgumentException("Invalid presentation time: " + time + ", max availble time: " + getAvailableTime());
		}
	}

	abstract List<String> getPresentations();
}
