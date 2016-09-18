package au.com.thoughtworks.time.manage.strategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
	private static final Integer THREE = 3;
	private static final Integer NINE = 9;
	private static final Integer FIFTEEN = 15;
	private static final Integer TWENTY_ONE = 21;

	public static Strategy createStrategy(Session session, Map<Integer, Integer> status) {
		Strategy strategy = null;
		Map<Integer, Integer> entries = new HashMap<>();
		
		TreeMap<Integer, Integer> sortedStatus = new TreeMap<>(status);
		TreeMap<Integer, Integer> shortPrenstation = new TreeMap<>(sortedStatus.headMap(31));
		if (canDoTwoHours(sortedStatus)) {
			if (canDoTwoThirtyMinute(shortPrenstation)) {
				
				entries.put(SIXTY, TWO);
				entries.put(THIRTY, TWO);
				strategy = new StrategyImpl(entries);
			} else if (canDoTwelveFix(shortPrenstation)) {
				
				entries.put(SIXTY, TWO);
				entries.put(FIVE, TWELVE);
				strategy = new StrategyImpl(entries);
			}
		} 
		else if (canDoOneHours(sortedStatus)) {
			if (canDoTwoFourtyFive(sortedStatus) && (canDoOneThirty(shortPrenstation))) {
				
				entries.put(SIXTY, ONE);
				entries.put(FOURTY_FIVE, TWO);
				entries.put(THIRTY, ONE);
				strategy = new StrategyImpl(entries);
			}
			if (canDoOneFourtyFive(sortedStatus) 
					&& canDoTwoThirtyMinute(shortPrenstation)
					&& canDoSixFive(shortPrenstation)) {
				
				entries.put(SIXTY, ONE);
				entries.put(THIRTY, TWO);
				entries.put(FIVE, SIX);
				strategy = new StrategyImpl(entries);
			}
		}
		else if (canDoFourFoutyFive(sortedStatus)) {
			
			entries.put(FOURTY_FIVE, FOUR);
			strategy = new StrategyImpl(entries);
		}
		else if (canDoThreeFourtyFive(sortedStatus) 
				&& canDoOneThirty(sortedStatus) 
				&& canDoThreeFive(sortedStatus)) {
			
			entries.put(FOURTY_FIVE, THREE);
			entries.put(THIRTY, ONE);
			entries.put(FIVE, THREE);
			strategy = new StrategyImpl(entries);
			
		}
		else if(canDoTwoFourtyFive(sortedStatus) 
				&& (canDoThreeThirty(sortedStatus))) {
			
			entries.put(FOURTY_FIVE, TWO);
			entries.put(THIRTY, THREE);
			strategy = new StrategyImpl(entries);
		}
		else if (canDoOneFourtyFive(sortedStatus)) {
			if (canDoFourThirty(sortedStatus) && 
					(canDoThreeFive(sortedStatus))) {
				entries.put(FOURTY_FIVE, ONE);
				entries.put(THIRTY, FOUR);
				entries.put(FIVE, THREE);
				strategy = new StrategyImpl(entries);
			}
			else if (canDoThreeThirty(sortedStatus) && 
					(canDoNineFive(sortedStatus))) {
				entries.put(FOURTY_FIVE, ONE);
				entries.put(THIRTY, THREE);
				entries.put(FIVE, NINE);
				strategy = new StrategyImpl(entries);
			}
			else if (canDoTwoThirty(sortedStatus) && 
					(canDoFifteenFive(sortedStatus))) {
				entries.put(FOURTY_FIVE, ONE);
				entries.put(THIRTY, TWO);
				entries.put(FIVE, FIFTEEN);
				strategy = new StrategyImpl(entries);
			}
			else if (canDoOneThirty(sortedStatus) && 
					(canDoTwentyOneFive(sortedStatus))) {
				entries.put(FOURTY_FIVE, ONE);
				entries.put(THIRTY, ONE);
				entries.put(FIVE, TWENTY_ONE);
				strategy = new StrategyImpl(entries);
			}
		}
		else if (canDoSixThirty(sortedStatus)) {
			
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

	protected static boolean canDoTwentyOneFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FIVE, TWENTY_ONE);
	}

	private static boolean canDoFifteenFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FIVE, FIFTEEN);
	}

	protected static boolean canDoTwoThirty(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, THIRTY, TWO);
	}

	private static boolean canDoNineFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FIVE, NINE);
	}

	protected static boolean canDoFourThirty(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, THIRTY, FOUR);
	}

	protected static boolean canDoThreeThirty(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, THIRTY, THREE);
	}

	protected static boolean canDoThreeFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FIVE, THREE);
	}

	protected static boolean canDoThreeFourtyFive(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, FOURTY_FIVE, THREE);
	}

	protected static boolean canDoSixThirty(TreeMap<Integer, Integer> sortedStatus) {
		return canDo(sortedStatus, THIRTY, SIX);
	}

	protected static Strategy defaultStrategy(TreeMap<Integer, Integer> sortedStatus,
			final Integer availableTime, final Integer minimumTime) {
		Map<Integer, Integer> entries = new HashMap<>();
		Integer totalTimeLeave = getRestTime(sortedStatus);
		if (totalTimeLeave < minimumTime) {
			//We do not have enough to fill the session.
			//Just fill with whatever we can.
			sortedStatus.entrySet().stream()
				.filter(e -> ZERO < e.getValue())
				.forEach(e -> {
					entries.put(e.getKey(), e.getValue());
				});
		}
		else {
		sortedStatus.entrySet().stream()
			.filter(e -> ZERO < e.getValue())
			.forEach(e -> {
				findFittedArrangement(entries, e.getKey(), 
						e.getValue(), minimumTime, availableTime);
			});
		}
		return new StrategyImpl(entries);
	}
	
	protected static Integer getRestTime(TreeMap<Integer, Integer> sortedStatus) {
		Integer totalTimeLeave = ZERO;
		Iterator<Entry<Integer, Integer>> iterator = sortedStatus.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();
			totalTimeLeave += entry.getKey() * entry.getValue();
		}
		return totalTimeLeave;
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
			--times;
			if (ZERO < times) {
				findFittedArrangement(entries, 
					length, times, minimum, max);
			}
		}
		return entries;
	}

	protected static boolean canDoFourFoutyFive(TreeMap<Integer, Integer> sortedStatus) {
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
