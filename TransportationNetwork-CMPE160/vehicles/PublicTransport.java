
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import locations.*;
/**
 *  creates a Public Transport vehicle.
 * @author Ecenur Sezer
 *
 */
public abstract class PublicTransport {
	private int ID;
	public double x1, y1, x2, y2;
	
	/**
	 * Constructor for creating a vehicle with ID number and limits of travel.
	 * @param ID ID number of the Public Transport object.
	 * @param x1 X coordinate of the beginning location.
	 * @param y1 Y coordinate of the beginning location.
	 * @param x2 X coordinate of the ending location.
	 * @param y2 Y coordinate of the ending location.
	 */
	public PublicTransport(int ID, double x1, double y1, double x2, double y2) {
		this.ID=ID;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}
	/**
	 * checks if the given Locations are in the limits of the vehicle.(Each vehicle has a rectangular limit where they can not travel outside)
	 * @param departure departure Location.
	 * @param arrival  arrival Location.
	 * @return returns true if both departure and arrival locations are in limits.
	 */
	
	public boolean canRide(Location departure, Location arrival) {
		if(x2 > x1) {
			if(x2< departure.locationX || departure.locationX < x1 || arrival.locationX >  x2 || arrival.locationX < x1) {
				return false;
			}
		}else if(x2> departure.locationX || departure.locationX > x1 || arrival.locationX <  x2 || arrival.locationX > x1) {
			return false;
		}
		if(y2 > y1) {
			if(y2< departure.locationY || departure.locationY < y1 || arrival.locationY >  y2 || arrival.locationY < y1) {
				return false;
			}
		}else if(y2> departure.locationY || departure.locationY > y1 || arrival.locationY <  y2 || arrival.locationY > y1) {
			return false;
		}	
		
		
		return true;
		}
	}
	

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE





