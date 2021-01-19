
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import locations.*;
import passengers.*;
/**
 * Subclass of the PublicTransport class.
 * creates Train object.
 * 
 *
 */

public class Train extends PublicTransport{
	/**
	 * Constructor for Train objects with ID number and coordinates for the limits of the vehicle.
	 * @param ID ID number of the Train object.
	 * @param x1 X coordinate of the beginning location.
	 * @param y1 Y coordinate of the beginning location.
	 * @param x2 X coordinate of the ending location.
	 * @param y2 Y coordinate of the ending location.
	 */
	public Train(int ID, double x1, double y1, double x2, double y2) {
		super(ID, x1, y1, x2, y2);
	}
	/**
	 * calculates the price of the travel according to the type of the Passenger object that is travelling.
	 * @param s travelling Location. 
	 * @param p Passenger that is travelling.
	 * @return the price of the travel.
	 */
	public double getPrice(Location s, Passenger p) {
		double distance = p.getCurrentLocation().getDistance(s);
		int firstStop = (int)distance/15;
		if(p instanceof DiscountedPassenger) {
		if(distance - firstStop*15 >= (firstStop+1)*15 - distance) {
			return (firstStop+1)*4;
		}else {
			return firstStop*4;
		}
	}
		if(distance - firstStop*15 >= (firstStop+1)*15 - distance) {
			return (firstStop+1)*5;
		}else {
			return firstStop*5;
		}	
	}
		
		
	}
	


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

