package au.com.thoughtworks.time.manage.strategy;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import au.com.thoughtworks.time.session.Session;

public class StrategyImpl implements Strategy {

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
	public void execute(Session session, Map<Integer, Integer> timeCounters,
			SortedMap<Integer, List<String>> timeCategories) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getOrganizedPresentations() {
		// TODO Auto-generated method stub
		return null;
	}

}
