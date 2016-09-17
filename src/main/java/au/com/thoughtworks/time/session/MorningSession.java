package au.com.thoughtworks.time.session;

import java.util.ArrayList;
import java.util.List;

import au.com.thoughtworks.constants.Constants.Strings;
import au.com.thoughtworks.time.session.utils.TimeCalculator;

public class MorningSession implements Session {

	private static final int SHORT_PRESENTATION_TIME = 30;

	public static final String DELIM = System.getProperty("line.separator");

	private int availableTime = 180;
	private boolean nextTalkShort = false;
	private List<String> presentantions = new ArrayList<>();
	private String programTime = "09:00AM";

	@Override
	public int getAvailableTime() {
		return this.availableTime;
	}

	@Override
	public boolean isNextTalkShort() {
		return nextTalkShort;
	}

	@Override
	public void addNextTalk(Integer time, String title) {
		if (this.availableTime < time) {
			throw new IllegalArgumentException("Invalid presentation time: " + time);
		}

		this.availableTime -= time;
		if (SHORT_PRESENTATION_TIME < time) {
			this.nextTalkShort = true;
		}
		this.presentantions.add(this.programTime + Strings.SPACE + title);
		this.programTime = TimeCalculator.newTime(this.programTime, time);
	}

	@Override
	public String getProgramDetails() {
		StringBuilder buf = new StringBuilder();
		for (String presentation : presentantions) {
			buf.append(presentation);
			buf.append(DELIM);
		}
		return buf.toString();
	}

	@Override
	public int getMinimumTime() {
		return this.availableTime;
	}

}
