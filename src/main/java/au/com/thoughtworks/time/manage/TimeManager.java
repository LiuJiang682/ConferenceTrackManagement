package au.com.thoughtworks.time.manage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import au.com.thoughtworks.time.session.MorningSession;
import au.com.thoughtworks.time.session.Session;
import au.com.thoughtworks.time.session.SessionBase;
import au.com.thoughtworks.time.session.SessionFactory;
import edu.emory.mathcs.backport.java.util.Collections;

public class TimeManager {

	private static final String KEY_COUNT = "count";
	private static final String KEY_SESSION = "session";
	private static final int THIRTY = 30;
	private static final String NETWORKING = "05:00PM Networking Event";
	private static final String LUNCH = "12:00PM Lunch";
	private static final int ZERO = 0;

	public List<String> arrange(SortedMap<Integer, List<String>> timeCategories, int totalPresentations) {
		List<String> programs = new ArrayList<>();
		Integer count = 0;

		// Step 1: take out the short (<31min) presentations
		SortedMap<Integer, List<String>> shortPresentations = new TreeMap<>(timeCategories.headMap(31));
		@SuppressWarnings("unchecked")
		SortedMap<Integer, List<String>> longPresentations = new TreeMap<>(Collections.reverseOrder());
		longPresentations.putAll(timeCategories.tailMap(31));

		Map<Integer, Integer> timeCounters = getTimeCounter(timeCategories);
		// Step 2: build the session
		Session session = SessionFactory.getNextSession();
		// take out the longest presentations
		Iterator<Integer> keyIterator = longPresentations.keySet().iterator();
		Integer time = keyIterator.next();
		
//		while (count <= totalPresentations) {
			boolean keepGoing = true;
			do {
				if (time < session.getMinimumTime()) {
					List<String> currentLongestPresentations = timeCategories.get(time);
					Integer index = timeCounters.get(time);
					if (index < currentLongestPresentations.size()) {
						Map<String, Object> resultMap = this.setProgram(currentLongestPresentations, timeCounters, time, session,
								shortPresentations, programs);
						session = (Session) resultMap.get(KEY_SESSION);
						count += (Integer) resultMap.get(KEY_COUNT);
					} else {
						// Next longest session
						if (keyIterator.hasNext()) {
							time = keyIterator.next();
						} else {
							keepGoing = false;
						}
					}
				}
				else {
					int remainingTime = session.getMinimumTime();
					int addedCount= buildShortSession(session, shortPresentations, timeCounters, remainingTime);
					session = this.finializingSesion(session, programs);
					if (0 < addedCount) {
						count += addedCount;
					}
					else {
						this.doProgramPupolute(session, programs);
						keepGoing = false;
					}
					
				}
			} while ((count < totalPresentations)||(keepGoing));
//		}

		return programs;
	}

	protected Map<String, Object> setProgram(List<String> currentLongestPresentations,
			Map<Integer, Integer> timeCounters, Integer time, Session session, 
			SortedMap<Integer, List<String>> shortPresentations, List<String> programs) {
		Integer count = 0;
		Integer index = timeCounters.get(time);
		String title = currentLongestPresentations.get(index);
		++index;
		timeCounters.put(time, index);
		session.addNextTalk(time, title);
		++count;
		if (session.isNextTalkShort()) {
			if (THIRTY <= session.getAvailableTime()) {
				count += build30MinSession(session, shortPresentations, timeCounters);
			}
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put(KEY_COUNT, count);
		resultMap.put(KEY_SESSION, this.finializingSesion(session, programs));
		return resultMap;
	}

	protected Session finializingSesion(Session session, 
			List<String> programs) {
		if ((ZERO == session.getMinimumTime()) || (ZERO == session.getAvailableTime())) {
			this.doProgramPupolute(session, programs);
			// New Session
			session = SessionFactory.getNextSession();
		}
		return session;
	}
	
	protected void doProgramPupolute(Session session, List<String> programs) {
		StringBuilder trackPrograms = new StringBuilder(session.getProgramDetails());
		// If the session is full or reached minimum time, stop!
		if (session instanceof MorningSession) {
			// Morning Session
			trackPrograms.append(LUNCH);
			trackPrograms.append(SessionBase.DELIM);
		} else {
			// Afternoon Session
			trackPrograms.append(NETWORKING);
			trackPrograms.append(SessionBase.DELIM);
		}
		programs.add(trackPrograms.toString());
	}
	
	protected Map<Integer, Integer> getTimeCounter(SortedMap<Integer, List<String>> timeCategories) {
		Map<Integer, Integer> timeCounters = new HashMap<>();
		timeCategories.entrySet().stream().forEach(e -> {
			timeCounters.put(e.getKey(), 0);
		});
		return timeCounters;
	}

	protected int build30MinSession(Session session, SortedMap<Integer, List<String>> shortPresentations,
			Map<Integer, Integer> timeCounters) {
		return this.buildShortSession(session, shortPresentations, timeCounters, THIRTY);
	}

	protected Integer buildShortSession(Session session, SortedMap<Integer, List<String>> shortPresentations,
			Map<Integer, Integer> timeCounters, int timeDelta) {
		Integer count = ZERO;
		boolean keepGoing = true;
		Iterator<Integer> keyIterator = shortPresentations.keySet().iterator();
		Integer time = keyIterator.next();

		do {
			List<String> presentations = shortPresentations.get(time);
			Integer index = timeCounters.get(time);
			if (index < presentations.size()) {
				session.addNextTalk(time, presentations.get(index));
				++index;
				timeCounters.put(time, index);
				++count;
				timeDelta -= time;
				if (timeDelta <= ZERO) {
					keepGoing = false;
				}
			} else {
				if (keyIterator.hasNext()) {
					time = keyIterator.next();
				} else {
					keepGoing = false;
				}
			}
		} while (keepGoing);

		return count;
	}

}
