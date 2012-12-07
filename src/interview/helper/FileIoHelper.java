/**
 * 
 */
package interview.helper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author swarters
 * 
 */
public class FileIoHelper {

	public final static String NO_CONTENTS = "There were no items in the file to parse.";
	public final static String NO_PATH = "Please put a file path in your arguments.";

	/**
	 * Reads the contents from the file and returns them.
	 * @param filePath location of the file
	 * @return the contents of the file
	 */
	public static List<String> readFileContents(String filePath) {
		List<String> fileItems = new ArrayList<String>();

		try {
			Path path = Paths.get(filePath);
			fileItems = Files.readAllLines(path, StandardCharsets.UTF_8);

		} catch (Exception e) {
			if (e.getClass().equals(InvalidPathException.class)) {
				System.out.println(NO_PATH);
			} else {
				System.err.print("Something has gone terribly wrong! And I can no longer live life."
						+ "\nWith my final breath, I deliver you this stack trace.\n");
				e.printStackTrace();				
			}
			System.exit(1);
		}
		return fileItems;
	}

}
