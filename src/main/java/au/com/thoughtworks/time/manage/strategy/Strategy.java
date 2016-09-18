package au.com.thoughtworks.time.manage.strategy;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import au.com.thoughtworks.time.session.Session;

public interface Strategy {

	String execute(Session session, Map<Integer, Integer> timeCounters, SortedMap<Integer, List<String>> timeCategories);

	Integer getOrganizedPresentations();

}
