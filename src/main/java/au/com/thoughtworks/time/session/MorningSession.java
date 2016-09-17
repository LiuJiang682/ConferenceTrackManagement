package au.com.thoughtworks.time.session;

import java.util.ArrayList;
import java.util.List;

import au.com.thoughtworks.constants.Constants.Strings;
import au.com.thoughtworks.time.session.utils.TimeCalculator;

public class MorningSession extends SessionBase {

	private int availableTime = 180;
	private boolean nextTalkShort = false;
	private List<String> presentations = new ArrayList<>();
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
		this.isValidTimeLimit(time);

		this.availableTime -= time;
		if (SessionBase.SHORT_PRESENTATION_TIME < time) {
			this.nextTalkShort = true;
		}
		else {
			this.nextTalkShort = false;
		}
		this.presentations.add(this.programTime + Strings.SPACE + title);
		this.programTime = TimeCalculator.newTime(this.programTime, time);
	}

	@Override
	public int getMinimumTime() {
		return this.availableTime;
	}

	@Override
	List<String> getPresentations() {
		return this.presentations;
	}

}
