package au.com.thoughtworks.parser;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class StringParser implements Parser {

	private static final int ONE = 1;
	private static final int NOT_FOUND = -1;

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
