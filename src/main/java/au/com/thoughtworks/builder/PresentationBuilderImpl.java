package au.com.thoughtworks.builder;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

import au.com.thoughtworks.parser.StringParser;

public class PresentationBuilderImpl implements PresentationBuilder {

	@Override
	public SortedMap<String, List<String>> buildPresentationCategories(List<String> contents) {
		SortedMap<String, List<String>> timeCategories = new TreeMap<>();
		
		Map<String, List<String>> map = contents.stream()
				.collect(Collectors.groupingBy(StringParser::lastWordOfSentence));
		map = replaceLightning(map);
		timeCategories.putAll(map);
		
		return timeCategories;
	}

	protected Map<String, List<String>> replaceLightning(Map<String, List<String>> map) {
		List<String> lightning = map.remove("lightning");
		if (CollectionUtils.isNotEmpty(lightning)) {
			map.put("5min", lightning);
		}
		return map;
	}

}
