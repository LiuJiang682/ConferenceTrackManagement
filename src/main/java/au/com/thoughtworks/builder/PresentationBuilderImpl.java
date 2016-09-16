package au.com.thoughtworks.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

import au.com.thoughtworks.parser.StringParser;

public class PresentationBuilderImpl implements PresentationBuilder {

	//The minutes key will be in 1 -- 999min format.
	private static final String REGEX_MINS_PATTERN = "^\\d{1,3}min$";
	private static final String FIVE_MIN = "5min";
	private static final String LIGHTNING = "lightning";

	@Override
	public SortedMap<String, List<String>> buildPresentationCategories(List<String> contents) {
		SortedMap<String, List<String>> timeCategories = new TreeMap<>();
		
		Map<String, List<String>> map = contents.stream()
				.collect(Collectors.groupingBy(StringParser::lastWordOfSentence));
		map = replaceLightning(map);
		Map<String, List<String>> timeCategoriesMap = new HashMap<>();
		
		map.entrySet().stream()
				.filter(isMinutesFormat())
				.forEach(e -> {timeCategoriesMap.put(e.getKey(), e.getValue());});
		timeCategories.putAll(timeCategoriesMap);
		
		return timeCategories;
	}

	/**
	 * This method matches the map entry key with a ##min pattern.
	 * 
	 * @return only if the map entry key is compliance with a
	 * 		##min pattern. For example:
	 * 			isMinutesFormat.text("10min") => true
	 * 			isMinutesFormat.text("0min") => true
	 * 			isMinutesFormat.text("999min") => true
	 * 			isMinutesFormat.text("min") => false
	 * 			isMinutesFormat.text("1000min") => false
	 * 			isMinutesFormat.text(" 10min") => false
	 * 			isMinutesFormat.text("10min ") => false
	 * 			isMinutesFormat.text(" 10min ") => false
	 */
	static Predicate<Map.Entry<String, List<String>>> isMinutesFormat() {
		return e -> e.getKey().matches(REGEX_MINS_PATTERN);
	}
	
	protected Map<String, List<String>> replaceLightning(Map<String, List<String>> map) {
		List<String> lightning = map.remove(LIGHTNING);
		if (CollectionUtils.isNotEmpty(lightning)) {
			map.put(FIVE_MIN, lightning);
		}
		return map;
	}

}
