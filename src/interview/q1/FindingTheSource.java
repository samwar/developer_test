/**
 * 
 */
package interview.q1;

import interview.helper.FileIoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swarters
 * 
 */
public class FindingTheSource {

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

				FindingTheSource fts = new FindingTheSource();

				System.out.println(fts.findHiddenObjectPosition(fileItems));

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(FileIoHelper.NO_PATH);
		}

	}

	/**
	 * Finds the position of the hidden object if it can be found 
	 * Solution derived from http://2000clicks.com/mathhelp/GeometryConicSectionCircleIntersection.aspx
	 * 
	 * @param measurements
	 * @return A string telling the user where the object has been found, or if it can't be found
	 */
	public String findHiddenObjectPosition(List<String> measurements) {

		String objectLocation = new String();

		List<RadioMeasurement> rm = new ArrayList<RadioMeasurement>();
		try {
			//Parse the measurements from the file input
			for (String measurement : measurements) {
				String[] values = measurement.split(" ");
				rm.add(new RadioMeasurement(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double
						.parseDouble(values[2])));
			}
	
			RadioMeasurement rm1 = rm.get(0);
			RadioMeasurement rm2 = rm.get(1);
	
			double distance = getDistanceBtwnMeasurements(rm1, rm2);
	
			// If the distance is 0 from either measurement, then the object is at that point
			if (rm1.getDistance() == 0.0) {
				objectLocation = rm1.displayFormattedPoint();
			} else if (rm2.getDistance() == 0.0) {
				objectLocation = rm2.displayFormattedPoint();
			} else if ((distance > rm1.getDistance() + rm2.getDistance())
					|| (distance <= Math.abs(rm1.getDistance() - rm2.getDistance()))) { // If the distance between the 2 circles is
																						// greater than the combined strength measurements
																						// or less than or equal to difference between them
																						// the object cannot be found.
				objectLocation = "The object cannot be found with these measurements.";
			} else {
				RadioMeasurement[] hiddenObjectLocations =  calculatePoints(rm1, rm2);
			
				if (hiddenObjectLocations[0].equals(hiddenObjectLocations[1])) {
					objectLocation = hiddenObjectLocations[0].displayFormattedPoint();
				} else {
					objectLocation = hiddenObjectLocations[0].displayFormattedPoint() + "\n" + hiddenObjectLocations[1].displayFormattedPoint();
				}
			}
		} catch (NumberFormatException e){
			System.err.println("Some of these readings look funny. Are you sure your scanner is returning doubles?\nMeasurements Taken: "+measurements.toString());
			System.exit(1);
		}
		
		return objectLocation;
	}

	/**
	 * Gets the distance between the two measurements
	 * @param rm1 first measurement
	 * @param rm2 second measurement
	 * @return the distance between the measurements
	 */
	private double getDistanceBtwnMeasurements(RadioMeasurement rm1, RadioMeasurement rm2) {
		return Math.sqrt(Math.pow(rm2.getX() - rm1.getX(), 2.0) + Math.pow(rm2.getY() - rm1.getY(), 2.0));
	}
	
	/**
	 * Calculates the intersecting points of the circles formed by the two measurements
	 * Solution derived from http://2000clicks.com/mathhelp/GeometryConicSectionCircleIntersection.aspx
	 * @param rm1 first measurement
	 * @param rm2 second measurement
	 * @return The intersecting points where the object might be found
	 */
	private RadioMeasurement[] calculatePoints(RadioMeasurement rm1, RadioMeasurement rm2) {
		double distanceSquared = Math.pow(getDistanceBtwnMeasurements(rm1, rm2), 2);
		double x1 = rm1.getX();
		double x2 = rm2.getX();
		double y1 = rm1.getY();
		double y2 = rm2.getY();
		double r1 = rm1.getDistance();
		double r2 = rm2.getDistance();
		double r1Squared = Math.pow(r1, 2);
		double r2Squared = Math.pow(r2, 2);

		// K is the area of triangle formed by the intersection of the two circles
		// K = (1/4)sqrt(((rA+rB)2-d^2)(d^2-(rA-rB)^2))
		double a = Math.pow(r1 + r2, 2) - distanceSquared;
		double b = distanceSquared - Math.pow(r1 - r2, 2);
		double K = (.25) * Math.sqrt(Math.abs(a * b));

		// Calculate the intersection points.
		// x = (1/2)(xB+xA) + (1/2)(xB-xA)(rA^2-rB^2)/d^2 ± 2(yB-yA)K/d^2
		double xi1 = .5 * (x2 + x1) + .5 * (x2 - x1) * (r1Squared - r2Squared) / distanceSquared 
			+ 2.0 * (y2 - y1) * K / distanceSquared;
		double xi2 = .5 * (x2 + x1) + .5 * (x2 - x1) * (r1Squared - r2Squared) / distanceSquared 
			- 2.0 * (y2 - y1) * K / distanceSquared;

		// y = (1/2)(yB+yA) + (1/2)(yB-yA)(rA^2-rB^2)/d^2 ± -2(xB-xA)K/d^2
		double yi1 = .5 * (y2 + y1) + .5 * (y2 - y1) * (r1Squared - r2Squared) / distanceSquared 
			+ 2.0 * (x2 - x1) * K / distanceSquared;
		double yi2 = .5 * (y2 + y1) + .5 * (y2 - y1) * (r1Squared - r2Squared) / distanceSquared 
			- 2.0 * (x2 - x1) * K / distanceSquared;

		RadioMeasurement hiddenObjectLocal = new RadioMeasurement(xi1, yi1, 0.0);
		RadioMeasurement hiddenObjectLocal2 = new RadioMeasurement(xi2, yi2, 0.0);
		
		return new RadioMeasurement[]{hiddenObjectLocal, hiddenObjectLocal2};
	}
}
