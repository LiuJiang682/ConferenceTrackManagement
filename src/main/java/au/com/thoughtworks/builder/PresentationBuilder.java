package au.com.thoughtworks.builder;

import java.util.List;
import java.util.SortedMap;

public interface PresentationBuilder {

	SortedMap<Integer, List<String>> buildPresentationCategories(final List<String> contents);
}
