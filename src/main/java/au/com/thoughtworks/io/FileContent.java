package au.com.thoughtworks.io;

import java.util.List;
import java.util.Optional;

public interface FileContent {

	Optional<List<String>> getContents(final String fileName);
}
