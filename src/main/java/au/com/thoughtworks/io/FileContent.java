package au.com.thoughtworks.io;

import java.util.List;

public interface FileContent {

	List<String> getContents(final String fileName, final int maxSize);
}
