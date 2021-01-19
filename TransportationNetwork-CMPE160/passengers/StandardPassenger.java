
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;

import locations.Location;

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
/**
 * Subclass of Passenger.
 * Creates a Standard passenger object which has no discount while travelling with public transport.
 *
 */

public class StandardPassenger extends Passenger{
	/**
	 * Constructor of Standard Passenger who does not own car.
	 * @param ID ID number.
	 * @param hasDriversLicense Driver's license status.
	 * 
	 * @param l current location.
	 */
	
	public StandardPassenger(int ID, boolean hasDriversLicense, Location l) {
		super(ID, hasDriversLicense, l);
		}
		
		/**
		 * constructor of Standard Passenger who owns a car.
		 * @param ID ID number.
		 * @param l Current location.
		 * @param fuelConsumption fuel consumption rate of the car.
		 */
		public StandardPassenger(int ID, Location l, double fuelConsumption) {
			super(ID, l, fuelConsumption);
		}
}