package au.com.thoughtworks.time.manage.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import au.com.thoughtworks.time.session.Session;
import au.com.thoughtworks.time.session.utils.PresentationMashaller;
import au.com.thoughtworks.time.session.utils.SessionPrinter;

public class StrategyImpl implements Strategy {

	private static final int SHORT_TALK_TIME = 31;
	
	private Map<Integer, Integer> entries;
	private Integer counter;
	
	public StrategyImpl(Map<Integer, Integer> entries) {
		if (null == entries) {
			throw new IllegalArgumentException("Entries map cannot be null!");
		}
		
		this.entries = entries;
		counter = 0;
	}

	@Override
	public String execute(Session session, Map<Integer, Integer> timeCounters,
			SortedMap<Integer, List<String>> timeCategories) {
		
		SortedMap<Integer, List<String>> presentationsInSession = new TreeMap<>();
		//Extract the title and update the timeCounter
		entries.entrySet().stream()
			.forEach(e -> {
				List<String> titlesEntries = new ArrayList<>();
				List<String> titles = timeCategories.get(e.getKey());
				Integer counter = timeCounters.get(e.getKey());
				Integer currentEntries = e.getValue();
				for (int i = 0; i < currentEntries; i++) {
					String title = titles.get(counter);
					titlesEntries.add(title);
					//Move the counter forward
					++counter;
				}
				//Update the time counter.
				timeCounters.put(e.getKey(), counter);
				presentationsInSession.put(e.getKey(), titlesEntries);
			});
		
		//Construct the program
		SortedMap<Integer, List<String>> longPresentations = new TreeMap<>(Collections.reverseOrder());
		longPresentations.putAll(presentationsInSession.tailMap(SHORT_TALK_TIME));
		SortedMap<Integer, List<String>> shortPresentations = new TreeMap<>(Collections.reverseOrder());
		shortPresentations.putAll(presentationsInSession.headMap(SHORT_TALK_TIME));
		Iterator<Entry<Integer, List<String>>> longPresentationIterator = longPresentations.entrySet().iterator();
		Iterator<Entry<Integer, List<String>>> shortPresentationIterator = shortPresentations.entrySet().iterator();
		List<Object[]> longPresentationList = PresentationMashaller.marshall(longPresentationIterator);
		List<Object[]> shortPresentationList = PresentationMashaller.marshall(shortPresentationIterator);
		int i = 0;
		for (; i < longPresentationList.size(); i++) {
			Object[] presentations = longPresentationList.get(i);
			session.addNextTalk((Integer)presentations[0], (String)presentations[1]);
			if (i < shortPresentationList.size()) {
				Object[] shortPresentation = shortPresentationList.get(i);
				session.addNextTalk((Integer)shortPresentation[0], (String)shortPresentation[1]);
			}
		}
		if (i < shortPresentationList.size()) {
			Object[] shortPresentation = shortPresentationList.get(i);
			session.addNextTalk((Integer)shortPresentation[0], (String)shortPresentation[1]);
		}
		
		counter += shortPresentationList.size();
		counter += longPresentationList.size();
		
		return SessionPrinter.print(session);
	}
	
	
	@Override
	public Integer getOrganizedPresentations() {
		return counter;
	}

}
