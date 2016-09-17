package au.com.thoughtworks.time.session;

import java.util.ArrayList;
import java.util.List;

import au.com.thoughtworks.constants.Constants.Strings;
import au.com.thoughtworks.time.session.utils.TimeCalculator;

public class AfternoonSession extends SessionBase {
	
	private int availableTime = 240;
	private int minimumTime = 180;
	private boolean nextTalkShort = false;
	private List<String> presentations = new ArrayList<>();
	private String programTime = "01:00PM";
	
	@Override
	public int getAvailableTime() {
		return this.availableTime;
	}

	@Override
	public int getMinimumTime() {
		return this.minimumTime;
	}

	@Override
	public boolean isNextTalkShort() {
		return this.nextTalkShort;
	}

	@Override
	public void addNextTalk(Integer time, String title) {
		this.isValidTimeLimit(time);
		
		this.availableTime -= time;
		if (time < this.minimumTime) {
			this.minimumTime -= time;
		}
		
		if (SHORT_PRESENTATION_TIME < time) {
			this.nextTalkShort = true;
		}
		this.presentations.add(this.programTime + Strings.SPACE + title);
		this.programTime = TimeCalculator.newTime(this.programTime, time);
	}

	@Override
	List<String> getPresentations() {
		return this.presentations;
	}


}
