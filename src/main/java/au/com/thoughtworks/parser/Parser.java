package au.com.thoughtworks.parser;

import java.util.Optional;

public interface Parser {

	Optional<String> parseLastWord(final String string, final String delim);
}
