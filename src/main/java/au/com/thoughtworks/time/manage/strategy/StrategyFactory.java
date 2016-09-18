package au.com.thoughtworks.time.manage.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import au.com.thoughtworks.time.session.Session;

/**
 * This class determine the strategy for presentation arrangement. It try to 2 x
 * 60 first, then 4 x 45 second, then full up with rest.
 */
public class StrategyFactory {

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THIRTY = 30;
	private static final int SIXTY = 60;
	private static final Integer FIVE = 5;
	private static final Integer TWELVE = 12;
	private static final Integer FOURTY_FIVE = 45;
	private static final Integer SIX = 6;
	private static final Integer FOUR = 4;
	private static final Integer ZERO = 0;

	public static Strategy createStrategy(Session session, Map<Integer, Integer> status) {
		Strategy strategy = null;

		TreeMap<Integer, Integer> sortedStatus = new TreeMap<>(status);
		TreeMap<Integer, Integer> shortPrenstation = new TreeMap<>(sortedStatus.headMap(31));
		if (canDoTwoHours(sortedStatus)) {
			if (canDoTwoThirtyMinute(shortPrenstation)) {
				Map<Integer, Integer> entries = new HashMap<>();
				entries.put(SIXTY, TWO);
				entries.put(THIRTY, TWO);
				strategy = new StrategyImpl(entries);
			} else if (canDoTwelveFix(shortPrenstation)) {
				Map<Integer, Integer> entries = new HashMap<>();
				entries.put(SIXTY, TWO);
				entries.put(FIVE, TWELVE);
				strategy = new StrategyImpl(entries);
			}
		} 
		else if (canDoOneHours(sortedStatus)) {
			if (canDoTwoFourtyFive(sortedStatus) && (canDoOneThirty(shortPrenstation))) {
				Map<Integer, Integer> entries = new HashMap<>();
				entries.put(SIXTY, ONE);
				entries.put(FOURTY_FIVE, TWO);
				entries.put(THIRTY, ONE);
				strategy = new StrategyImpl(entries);
			}
			if (canDoOneFourtyFive(sortedStatus) 
					&& canDoTwoThirtyMinute(shortPrenstation)
					&& canDoSixFive(shortPrenstation)) {
				Map<Integer, Integer> entries = new HashMap<>();
				entries.put(SIXTY, ONE);
				entries.put(THIRTY, TWO);
				entries.put(FIVE, SIX);
				strategy = new StrategyImpl(entries);
			}
		}
		else if (canDoFourFoutyFix(sortedStatus)) {
			Map<Integer, Integer> entries = new HashMap<>();
			entries.put(FOURTY_FIVE, FOUR);
			strategy = new StrategyImpl(entries);
		}
		else if (canDoSixThirty(sortedStatus)) {
			Map<Integer, Integer> entries = new HashMap<>();
			entries.put(THIRTY, SIX);
			strategy = new StrategyImpl(entries);
		}
		else {
			//Default strategy -- Just line up according to time length
			strategy = defaultStrategy(sortedStatus, session.getAvailableTime(), 
					session.getMinimumTime());
		}
		
		return strategy;
	}

	protected static boolean canDoSixThirty(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, THIRTY, SIX);
	}

	protected static Strategy defaultStrategy(TreeMap<Integer, Integer> sortedStatus,
			final Integer availableTime, final Integer minimumTime) {
		Map<Integer, Integer> entries = new HashMap<>();
		sortedStatus.entrySet().stream()
			.filter(e -> ZERO < e.getValue())
			.forEach(e -> {
				findFittedArrangement(entries, e.getKey(), 
						e.getValue(), minimumTime, availableTime);
			});
		
		return new StrategyImpl(entries);
	}
	
	protected static Map<Integer, Integer> findFittedArrangement(Map<Integer, Integer> entries,
			Integer length, 
			Integer times, 
			Integer minimum,
			Integer max) {
		Integer timeLength = length * times;
		if ((minimum <= timeLength)
				&&(timeLength <= minimum)) {
			entries.put(length, times);
		}
		else {
			findFittedArrangement(entries, 
					length, --times, minimum, max);
		}
		return entries;
	}

	protected static boolean canDoFourFoutyFix(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FOURTY_FIVE, FOUR);
	}

	protected static boolean canDoSixFive(TreeMap<Integer, Integer> shortPrenstation) {
		return canDo(shortPrenstation, FIVE, SIX);
	}

	protected static boolean canDoOneFourtyFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FOURTY_FIVE, ONE);
	}

	protected static boolean canDoOneThirty(TreeMap<Integer, Integer> shortPrenstation) {
		return canDo(shortPrenstation, THIRTY, ONE);
	}

	protected static boolean canDoTwoFourtyFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FOURTY_FIVE, TWO);
	}

	protected static boolean canDoTwelveFix(TreeMap<Integer, Integer> shortPrenstation) {
		return canDo(shortPrenstation, FIVE, TWELVE);
	}

	protected static boolean canDoOneHours(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, SIXTY, ONE);
	}

	protected static boolean canDoTwoThirtyMinute(TreeMap<Integer, Integer> shortPrenstation) {
		return canDo(shortPrenstation, THIRTY, TWO);
	}

	protected static boolean canDoTwoHours(Map<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, SIXTY, TWO);
	}

	protected static boolean canDo(Map<Integer, Integer> statusMap, Integer time, Integer required) {
		Integer status = statusMap.get(time);
		if ((null != status) && (required <= status)) {
			return true;
		}
		return false;
	}

}
