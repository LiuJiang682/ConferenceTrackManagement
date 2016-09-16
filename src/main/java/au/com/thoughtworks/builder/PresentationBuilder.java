package au.com.thoughtworks.builder;

import java.util.List;
import java.util.SortedMap;

public interface PresentationBuilder {

	SortedMap<String, List<String>> buildPresentationCategories(final List<String> contents);
}
