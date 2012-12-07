package interview.q4;

import interview.helper.FileIoHelper;

import java.util.List;

public class StringManipulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Read in the contents of the file
			List<String> fileItems = FileIoHelper.readFileContents(args[0]);

			if (fileItems.isEmpty()) {
				System.out.println(FileIoHelper.NO_CONTENTS);
			} else {
				String s = fileItems.get(0);

				StringManipulation sm = new StringManipulation();

				System.out.print(sm.compactString(s));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(FileIoHelper.NO_PATH);
		}

	}

	/**
	 * Strips a string of whitespace and removes duplicate characters that are next to
	 * each other
	 * 
	 * @param s the string to compact
	 * @return the compacted string
	 */
	private String compactString(String s) {

		// Since strings are immutable in Java, you can't modify the string "in place"
		// as that would require direct memory manipulation. Because of this, Java is
		// going to allocate memory in the string pool for the modified string.
		s = s.replace(" ", "");
		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			if (sb.length() == 0 || (sb.charAt(sb.length() - 1) != c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

}
