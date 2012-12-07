/**
 * 
 */
package interview.q2;

import interview.helper.FileIoHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author swarters
 * 
 */
public class JustifyingText {

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
				int columnWidth = 1; // This is set to one so the formatter doesn't complain if no number is parsed.

				try {
					columnWidth = Integer.parseInt(fileItems.get(0));
				} catch (NumberFormatException e) {
					System.err.println("This isn't the number you're looking for."
							+ "\nNo number parsed from file, using the default column width of " + columnWidth + ".");
				}

				String text = fileItems.get(1);

				JustifyingText jt = new JustifyingText();

				// Break out the string into rows
				List<StringBuilder> sbList = jt.breakStringIntoRows(text, columnWidth);

				// Display those nicely formatted strings
				for (StringBuilder sb : sbList) {
					System.out.printf("%-" + columnWidth + "s %n", sb.toString());
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(FileIoHelper.NO_PATH);
		}

	}

	/**
	 * Splits the text into rows no longer than the column width
	 * 
	 * @param text to break into rows
	 * @param columnWidth the length of each column
	 * @return A list of strings each equaling the length of the specified column width
	 */
	private List<StringBuilder> breakStringIntoRows(String text, int columnWidth) {
		String[] splitTexts = text.split(" ");

		List<StringBuilder> sbList = new ArrayList<StringBuilder>();
		StringBuilder sb = new StringBuilder();
		sbList.add(sb);

		for (String s : splitTexts) {
			int sbLength = sbList.get(sbList.size() - 1).length();
			int sbListPos = sbList.size() - 1;

			if (sbLength + s.length() < columnWidth) {
				if (sbLength > 0) {
					sbList.get(sbListPos).append(" ");
				}
				sbList.get(sbListPos).append(s);
			} else {
				addExtraSpaces(sb, columnWidth);
				sb = new StringBuilder(s);
				sbList.add(sb);
			}
		}
		return sbList;
	}

	/**
	 * Randomly pads the a string with extra spaces in the pre-existing whitespace to make
	 * the length of the column width. If there are no whitespaces, make one at the end
	 * and then pad it out. This is done randomly in an attempt to make the text look more
	 * natural.
	 * 
	 * @param sb string to pad
	 * @param columnWidth length to pad it to
	 */
	private void addExtraSpaces(StringBuilder sb, int columnWidth) {
		Random rand = new Random(System.currentTimeMillis());

		while (sb.length() < columnWidth) {
			// No whitespace? Add it to the end to get things started.
			if (sb.indexOf(" ") == -1) {
				sb.insert(sb.length(), " ");
			} else {
				int index = rand.nextInt(sb.length());
				if (sb.charAt(index) == ' ') {
					sb.insert(index, " ");
				}
			}
		}

	}

}
