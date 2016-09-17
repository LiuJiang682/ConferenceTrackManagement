package au.com.thoughtworks.parser;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import au.com.thoughtworks.constants.Constants;

public class StringParser {

	public static final String DELIM_SPACE = " ";
	
	private static final int ONE = 1;
	private static final int NOT_FOUND = -1;

	/**
	 * This method extracts the last word from a sentence. 
	 * It will only return the last word of a standard sentence,
	 * any other cases will NOT return any word! It will also
	 * not return any word if the delim is null or empty.
	 * For example:
	 * 		 parserLastWord("last word", " ") => "word"
	 *       parserLastWord(null, " ") => false
	 *       parserLastWord("", " ") => false
	 *       parserLastWord(" ", " ") => false
	 *       parserLastWord("word", " ") => false
	 *       parserLastWord("word", "d") => false
	 *       parserLastWord("word", null) => false
	 *       parserLastWord("word", "") => false
	 *       
	 * @param string the content string
	 * @param delim the delim
	 * 
	 * @return last word of the content string or false 
	 * 			of Optional
	 */
	public static Optional<String> parseLastWord(String string, String delim) {
		Optional<String> lastWord = Optional.empty(); 
		
		if (StringUtils.isNotBlank(string)
				&&(StringUtils.isNotEmpty(delim))) {
			int index = string.lastIndexOf(delim);
			if ((NOT_FOUND != index)
					&&(index < (string.length() - ONE))) {
				String last = string.substring(index);
				lastWord = Optional.of(last.trim().toLowerCase());
			}
		}
		return lastWord;
	}

//	public static Function<String, String> lastWordOfSentence(final String string) {
//		return f -> {
//			StringParser parser = new StringParser();
//			Optional<String> result = parser.parseLastWord(string, " ");
//			String category = result.isPresent() ? result.get() : "";
//			return category;
//		};
//	}
	
	public static String lastWordOfSentence(final String string) {
		Optional<String> result = parseLastWord(string, DELIM_SPACE);
		String category = result.isPresent() ? result.get() : Constants.Strings.EMPTY;
		return category;
	}
}
