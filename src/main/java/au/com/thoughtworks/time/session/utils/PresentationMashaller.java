package au.com.thoughtworks.time.session.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class PresentationMashaller {

	public static List<Object[]> marshall(Iterator<Entry<Integer, List<String>>> i) {
		List<Object[]> results = new ArrayList<>();
		while (i.hasNext()) {
			Entry<Integer, List<String>> entry = i.next();
			for(String string : entry.getValue()) {
				Object[] result = {entry.getKey(), string};
				results.add(result);
			}
		}
		return results;
	}

}
