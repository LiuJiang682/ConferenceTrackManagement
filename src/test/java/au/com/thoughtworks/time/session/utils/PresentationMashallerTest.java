package au.com.thoughtworks.time.session.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import au.com.thoughtworks.fixture.PresentationFixture;

public class PresentationMashallerTest {

	@Test
	public void whenIteratorProvidedThenAListReturn() {
		//Given the Iterator
		Iterator<Entry<Integer, List<String>>> i = PresentationFixture.getIterator();
		//When marsh
		List<Object[]> list = PresentationMashaller.marshall(i);
		//Then a list should return 
		assertNotNull(list);
		assertFalse(list.isEmpty());
		Object[] entry = list.get(0);
		assertTrue(5 == (Integer)entry[0]);
		assertEquals("Rails for Python Developers 5min", entry[1]);
		
		Object[] entry1 = list.get(1);
		assertTrue(5 == (Integer)entry1[0]);
		assertEquals("Perl for Python Developers 5min", entry1[1]);
	}
}
