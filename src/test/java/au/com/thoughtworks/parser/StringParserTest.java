package au.com.thoughtworks.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

// In order to organized the conference
// As the conference manager
// I like to extract the time from content
public class StringParserTest {
	
	@Test
	public void whenASentenceProvidedThenLastWordShouldReturn() {
		//Given a sentence
		String content = "Communicating Over Distance 60min";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, StringParser.DELIM_SPACE);
		//Then the last word should return
		assertNotNull(lastWord);
		assertTrue(lastWord.isPresent());
		assertEquals("60min", lastWord.get());
	}
	
	@Test
	public void whenANullSentenceProvidedThenNoLastWordShouldReturn() {
		//Given a null sentence
		String content = null;
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, StringParser.DELIM_SPACE);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenAEmptySentenceProvidedThenNoLastWordShouldReturn() {
		//Given a null sentence
		String content = "";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, StringParser.DELIM_SPACE);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenABlankSentenceProvidedThenNoLastWordShouldReturn() {
		//Given a null sentence
		String content = " ";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, StringParser.DELIM_SPACE);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenAWordSentenceProvidedThenNoLastWordShouldReturn() {
		//Given a null sentence
		String content = "abc";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, StringParser.DELIM_SPACE);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenASentenceProvidedWithDelimAtLastCharThenNoLastWordShouldReturn() {
		//Given a null sentence
		String content = "abc";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, "c");
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenASentenceProvidedWithDelimIsNullThenNoLastWordShouldReturn() {
		//Given a null sentence and null delim
		String content = "abc";
		String delim = null;
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, delim);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
	
	@Test
	public void whenASentenceProvidedWithDelimIsEmptyThenNoLastWordShouldReturn() {
		//Given a null sentence and null delim
		String content = "abc";
		String delim = "";
		//When the parseLastWord method called
		Optional<String> lastWord = StringParser.parseLastWord(content, delim);
		//Then the last word should NOT return
		assertNotNull(lastWord);
		assertFalse(lastWord.isPresent());
	}
}
