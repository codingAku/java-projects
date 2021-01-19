
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import locations.*;
import passengers.*;
/**
 * Subclass of Public Transport.
 * Creates Bus objects.
 * 
 *
 */
public class Bus extends PublicTransport{
	/**
	 *Constructor of Bus.
	 *All public transport objects have a limit where they can travel freely inside. 
	 *There are 4 coordinates(2 points) which sets this limit as a rectangle shape. 
	 * 
	 * @param ID ID number.
	 * @param x1 X coordinate of the first point.
	 * @param y1 Y coordinate of the first point.
	 * @param x2 X coordinate of the last point.
	 * @param y2 Y coordinate of the last point.
	 */
	
	
	public Bus(int ID, double x1, double y1, double x2, double y2) {
		super(ID, x1, y1, x2, y2);
	}
	
	/**
	 * 
	 * @param p Passenger object.
	 * @return the price the passenger will pay to the bus according to the type of the passenger.
	 */
	public double getPrice(Passenger p) {
		if(p instanceof DiscountedPassenger) {
		return 1.00;
		}
		return 2.00;
		
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

