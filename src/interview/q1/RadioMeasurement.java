/**
 * 
 */
package interview.q1;

import java.text.DecimalFormat;

/**
 * @author swarters
 *
 */
public class RadioMeasurement {
	private double x;
	private double y;
	private double distance;
	
	public RadioMeasurement(double x, double y, double distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	
	public RadioMeasurement() {
		
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/* (non-Javadoc) - Generated by Eclipse
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc) - Generated by Eclipse
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadioMeasurement other = (RadioMeasurement) obj;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	/* (non-Javadoc) - Generated by Eclipse
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RadioMeasurement [x=" + x + ", y=" + y + ", distance=" + distance + "]";
	}

	/**
	 * Display the x,y coordinates as a string
	 * @return the coordinates as a string
	 */
	public String displayPoint() {
		return getX()+ " " + getY();
	}
	
	/**
	 * Display the x,y coordinates as a string with one decimal place
	 * @return the coordinates as a string
	 */
	public String displayFormattedPoint() {
		DecimalFormat df = new DecimalFormat("#.0");
		return df.format(getX())+ " " + df.format(getY());
	}
	
	
	

}
