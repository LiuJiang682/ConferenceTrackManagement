package au.com.thoughtworks.time.session.utils;

import java.math.BigDecimal;

import au.com.thoughtworks.constants.Constants.Strings;

public class TimeCalculator {

	private static final String TIME_FORMAT = "%02d:%02d";
	private static final String COLON = ":";
	private static final int ONE = 1;
	private static final int ZERO = 0;
	private static final int TWO = 2;
	private static final BigDecimal SIXTY = new BigDecimal(60);

	/**
	 * This method calculates the new time according to the
	 * current time and time delta pass in. It will update 
	 * the hour and minute accordingly. 
	 * Warning: This method will NOT update the AM to
	 * 		PM when the time cross from 11:59 to 12:00.
	 * 
	 * @param currentTime the current time
	 * @param delta the time delta to add.
	 * @return the new time.
	 */
	public static String newTime(String currentTime, Integer delta) {
		String ampm = currentTime.substring(currentTime.length() - TWO);
		String digits = currentTime.replace(ampm, Strings.EMPTY);
		String[] hourAndMinuts = digits.split(COLON);
		
		BigDecimal presentationTime = new BigDecimal(delta);
		BigDecimal[] presentationHoursAndMinuts = presentationTime.divideAndRemainder(SIXTY); 
		BigDecimal newHour = presentationHoursAndMinuts[ZERO].add(new BigDecimal(hourAndMinuts[ZERO]));
		BigDecimal newMinute = presentationHoursAndMinuts[ONE].add(new BigDecimal(hourAndMinuts[ONE]));
		BigDecimal realMinute = newMinute.subtract(SIXTY);
		if (ZERO <= realMinute.intValue()) {
			//Add one hour to hour
			newHour = newHour.add(BigDecimal.ONE);
			newMinute = realMinute;
		}
		String newTimeString = String.format(TIME_FORMAT, newHour.intValue(), newMinute.intValue());
		return newTimeString + ampm;
	}

}
