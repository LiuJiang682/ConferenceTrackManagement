package au.com.thoughtworks.parser;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class StringParser implements Parser {

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
	@Override
	public Optional<String> parseLastWord(String string, String delim) {
		Optional<String> lastWord = Optional.empty(); 
		
		if (StringUtils.isNotBlank(string)
				&&(StringUtils.isNotEmpty(delim))) {
			int index = string.lastIndexOf(delim);
			if ((NOT_FOUND != index)
					&&(index < (string.length() - ONE))) {
				String last = string.substring(index);
				lastWord = Optional.of(last.trim());
			}
		}
		return lastWord;
	}

}
