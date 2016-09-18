package au.com.thoughtworks.time.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

import au.com.thoughtworks.builder.PresentationBuilder;
import au.com.thoughtworks.builder.PresentationBuilderImpl;
import au.com.thoughtworks.io.FileContent;
import au.com.thoughtworks.io.FileContentImpl;
import au.com.thoughtworks.time.manage.strategy.Strategy;
import au.com.thoughtworks.time.manage.strategy.StrategyFactory;
import au.com.thoughtworks.time.session.Session;
import au.com.thoughtworks.time.session.SessionFactory;

public class TimeManager {

	private static final int TWO = 2;
	private static final int ONE = 1;
	private static final int ZERO = 0;
	private static final String DEFAULT_FILE_NAME = "src/test/resources/test.txt";

	protected Map<Integer, Integer> getTimeCounter(SortedMap<Integer, List<String>> timeCategories) {
		Map<Integer, Integer> timeCounters = new HashMap<>();
		timeCategories.entrySet().stream().forEach(e -> {
			timeCounters.put(e.getKey(), 0);
		});
		return timeCounters;
	}

	public List<String> organized(SortedMap<Integer, List<String>> timeCategories, int totalPresentations) {
		// Step 1 -- Initial the counter
		Map<Integer, Integer> timeCounters = getTimeCounter(timeCategories);
		Integer count = ZERO;
		List<String> programs = new ArrayList<>();

		// Step 2 -- Get the size of the time categories
		Map<Integer, Integer> categoriesSize = getCategoriesSize(timeCategories);

		// Start organizing the programs
		while (count < totalPresentations) {
			// Step 3 -- Get the session.
			Session session = SessionFactory.getNextSession();
			Map<Integer, Integer> status = getStatus(timeCounters, categoriesSize);
			Strategy strategy = StrategyFactory.createStrategy(session, status);
			programs.add(strategy.execute(session, timeCounters, timeCategories));
			count += strategy.getOrganizedPresentations();
		}

		return programs;
	}

	protected Map<Integer, Integer> getCategoriesSize(SortedMap<Integer, List<String>> timeCategories) {
		Map<Integer, Integer> categoriesSize = new HashMap<>();
		timeCategories.entrySet().stream().forEach(e -> {
			categoriesSize.put(e.getKey(), e.getValue().size());
		});
		return categoriesSize;
	}

	protected Map<Integer, Integer> getStatus(Map<Integer, Integer> timeCounters,
			Map<Integer, Integer> categoriesSize) {
		Map<Integer, Integer> categoriesStatus = new HashMap<>();
		timeCounters.entrySet().stream().forEach(e -> {
			Integer size = categoriesSize.get(e.getKey());
			Integer delta = size - e.getValue();
			categoriesStatus.put(e.getKey(), delta);
		});

		return categoriesStatus;
	}
	
	public void doConferences(final String fileName) {
		FileContent fileContent = new FileContentImpl();
		Optional<List<String>> contents = fileContent.getContents(fileName);
		if (contents.isPresent()) {
			PresentationBuilder builder = new PresentationBuilderImpl();
			SortedMap<Integer, List<String>> timeCategories = builder.buildPresentationCategories(contents.get());
			List<String> programs = this.organized(timeCategories, contents.get().size());
			printPrograms(programs);
		}
	}

	private void printPrograms(List<String> programs) {
		int trackCounter = ZERO;
		for (int i = 0; i< programs.size(); i++) {
			if(i % TWO == ZERO) {
				++trackCounter;
				System.out.println("Track " + trackCounter + ":");
			}
			System.out.println(programs.get(i));
			
		}
	}

	public static void main(String[] argv) {
		String fileName = DEFAULT_FILE_NAME;
		if ((null != argv) && (ONE == argv.length)) {
			fileName = argv[ZERO];
		}

		try {
			new TimeManager().doConferences(fileName);
			
		} catch (Exception e) {
			usage();
		}
	}

	private static void usage() {
		System.out.println("Usage: java -jar TimeManager fileName");

	}
}
