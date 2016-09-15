package au.com.thoughtworks.io;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

/**
 * The file content read/write utility class.
 *
 */
public class FileContentImpl implements FileContent {

	private static final Logger LOGGER = Logger.getLogger(FileContentImpl.class);
	
	/**
	 * This method provides all contents for a particular file.
	 * 
	 * The file contains conference presentation titles will be
	 * small, less than few hundreds lines in the file, thus, 
	 * Files.readAllLines will not use all memory. Refactor may 
	 * required is this method is used for read a larger file.
	 * At this stage, this method is fit for it purpose. 
	 * 
	 * @param fileName the file to read
	 * @return a list of content belong the provided file.
	 */
	@Override
	public Optional<List<String>> getContents(String fileName) {
		Optional<List<String>> contents = Optional.empty();
		
		try {
			Path filePath = FileSystems.getDefault().getPath(fileName); 
			contents = Optional.of(Files.readAllLines(filePath));
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return contents;
	}

}
