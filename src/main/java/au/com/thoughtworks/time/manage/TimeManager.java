package au.com.thoughtworks.time.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class TimeManager {

	public List<String> arrange(SortedMap<Integer, List<String>> timeCategories) {
		List<String> programs = new ArrayList<>();
		List<String> currentLongestPresentations = null;
		
		//Step 1: take out the short (<31min) presentations
		SortedMap<Integer, List<String>> shortPresentations = timeCategories.headMap(31);
		
		while(!timeCategories.isEmpty()) {
			//Step 2: take out the longest presentations
			Integer time = timeCategories.lastKey();
			currentLongestPresentations = timeCategories.remove(time);
		}
		
		return programs;
	}

}
