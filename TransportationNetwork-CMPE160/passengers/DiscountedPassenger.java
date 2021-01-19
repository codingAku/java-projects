
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;


import locations.Location;
/**
 * Subclass of Passenger.
 *  Creates a Discounted Passenger object which travel cheaper with bus or train in certain amount.
 *  
 */

public class DiscountedPassenger extends Passenger{
	
	/**
	 * constructor for Discounted Passengers who does not own a car.
	 * @param ID ID number of the passenger.
	 * @param hasDriversLicense states that if the passenger has a drivers license or not.
	 * @param l current location of the passenger.
	 */
	public DiscountedPassenger(int ID, boolean hasDriversLicense, Location l) {
	super(ID, hasDriversLicense, l);
	}
	/**
	 * constructor  for Discounted Passengers who does own a car.
	 * @param ID ID number of the passenger.
	 * @param l current location of the passenger.
	 * @param fuelConsumption fuel consumption rate of the passenger's car.
	 */
	public DiscountedPassenger(int ID, Location l, double fuelConsumption) {
		super(ID, l, fuelConsumption);
	}

	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

