package au.com.thoughtworks.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import au.com.thoughtworks.time.session.AfternoonSession;
import au.com.thoughtworks.time.session.MorningSession;
import au.com.thoughtworks.time.session.Session;

public class PresentationFixture {

	public static List<String> getTitleList() {
		return Arrays.asList(
				"Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min",
				"Lua for the Masses 30min",
				"Ruby Errors from Mismatched Gem Versions 45min",
				"Common Ruby Errors 45min",
				"Rails for Python Developers lightning",
				"Communicating Over Distance 60min",
				"Accounting-Driven Development 45min",
				"Woah 30min",
				"Sit Down and Write 30min",
				"Pair Programming vs Noise 45min",
				"Rails Magic 60min",
				"Ruby on Rails: Why We Should Move On 60min",
				"Clojure Ate Scala (on my project) 45min",
				"Programming in the Boondocks of Seattle 30min",
				"Ruby vs. Clojure for Back-End Development 30min",
				"Ruby on Rails Legacy App Maintenance 60min",
				"A World Without HackerNews 30min",
				"User Interface CSS in Rails Apps 30min"
				);
	}

	public static Map<String, List<String>> getLightningTimeCategories() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> lightningList = new ArrayList<>();
		lightningList.add("Rails for Python Developers lightning");
		map.put("lightning", lightningList);
		List<String> thirtyList = getList();
		thirtyList.add("A World Without HackerNews 30min");
		map.put("30min", thirtyList);
		return map;
	}

	public static Entry<String, List<String>> getMinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("30min", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> get0MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("0min", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> getNoDigitMinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("min", thirtyList);
		return map.entrySet().iterator().next();
	}

	private static List<String> getList() {
		List<String> thirtyList = new ArrayList<>();
		thirtyList.add("User Interface CSS in Rails Apps 30min");
		return thirtyList;
	}

	public static Entry<String, List<String>> get999MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("999min", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> get1000MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("1000min", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> getLeadSpace10MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put(" 10min", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> getTailSpace10MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put("10min ", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static Entry<String, List<String>> getLeadTailSpace10MinutesFormateKey() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> thirtyList = getList();
		map.put(" 10min ", thirtyList);
		return map.entrySet().iterator().next();
	}

	public static List<String> getMixTitleList() {
		List<String> list = new ArrayList<>(getTitleList());
		list.add("F# ABC");
		return list;
	}

	public static SortedMap<String, List<String>> getStringTimeTitleList() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> lightningList = new ArrayList<>();
		lightningList.add("Rails for Python Developers lightning");
		map.put("5min", lightningList);
		List<String> thirtyList = getList();
		thirtyList.add("A World Without HackerNews 30min");
		map.put("30min", thirtyList);
		return new TreeMap<>(map);
	}

	public static SortedMap<Integer, List<String>> getTimeCategories() {
		TreeMap<Integer, List<String>> timeCategories = new TreeMap<>();
		List<String> sixty = getSixtyList();
		timeCategories.put(60, sixty);
		List<String> fourtyFive = getFourtyFiveList();
		timeCategories.put(45, fourtyFive);
		List<String> thirty = getThirty();
		timeCategories.put(30, thirty);
		return timeCategories;
	}
	
	public static SortedMap<Integer, List<String>> getFullTimeCategories() {
		TreeMap<Integer, List<String>> timeCategories = new TreeMap<>();
		List<String> sixty = getSixtyList();
		timeCategories.put(60, sixty);
		List<String> fourtyFive = getFourtyFiveList();
		timeCategories.put(45, fourtyFive);
		List<String> thirty = getThirty();
		timeCategories.put(30, thirty);
		timeCategories.put(5, getFive());
		return timeCategories;
	}

	public static List<String> getFive() {
		return Arrays.asList("Rails for Python Developers 5min",
				"Perl for Python Developers 5min"
				);
	}
	
	public static List<String> getThirty() {
		return Arrays.asList(
				"Lua for the Masses 30min",
				"Woah 30min",
				"Sit Down and Write 30min",
				"Programming in the Boondocks of Seattle 30min",
				"Ruby vs. Clojure for Back-End Development 30min",
				"A World Without HackerNews 30min",
				"User Interface CSS in Rails Apps 30min"
		);
	}

	public static List<String> getFourtyFiveList() {
		return Arrays.asList("Overdoing it in Python 45min",
				"Ruby Errors from Mismatched Gem Versions 45min",
				"Common Ruby Errors 45min",
				"Accounting-Driven Development 45min",
				"Pair Programming vs Noise 45min",
				"Clojure Ate Scala (on my project) 45min"
		);
	}

	public static List<String> getSixtyList() {
		return Arrays.asList("Writing Fast Tests Against Enterprise Rails 60min", 
				"Communicating Over Distance 60min",
				"Rails Magic 60min",
				"Ruby on Rails Legacy App Maintenance 60min"
				);
	}

	public static Session getMorningSession() {
		return new MorningSession();
	}

	public static Map<Integer, Integer> getStatus() {
		Map<Integer, Integer> status = new HashMap<>();
		status.put(60, 4);
		status.put(45, 6);
		status.put(30, 7);
		status.put(5, 2);
		return status;
	}

	public static Map<Integer, Integer> getStrategyEntries() {
		Map<Integer, Integer> entries = new HashMap<>();
		entries.put(60, 2);
		entries.put(30, 2);
		return entries;
	}

	public static Map<Integer, Integer> getTimeCounters() {
		Map<Integer, Integer> counters = new HashMap<>();
		counters.put(60, 0);
		counters.put(45, 0);
		counters.put(30, 0);
		counters.put(5, 0);
		return counters;
	}

	public static Iterator<Entry<Integer, List<String>>> getIterator() {
		Map<Integer, List<String>> timeCategories = new HashMap<>();
		timeCategories.put(5, getFive());
		return timeCategories.entrySet().iterator();
	}

	public static Session getAfternoonSession() {
		return new AfternoonSession();
	}
}
