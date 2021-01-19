
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package locations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import passengers.*;

/**
 * Creates Location objects which have an ID number and X, Y coordinates.
 * 
 *
 */

public class Location{
	/**
	 * An integer ID number of Location object.
	 */
	private int ID;
	/**
	 * double value of X coordinate.
	 */
	public double locationX;
	/**
	 * double value of Y coordinate.
	 */
	public double locationY;
	/**
	 * An array list of the passengers who had been on this location.
	 */
	private ArrayList<Passenger> history;
	/**
	 * An array list of the passengers who are travelling to or on this location.
	 */
	private ArrayList<Passenger> current;
	
	/**
	 * Constructor for Location objects.
	 * @param ID : ID number.
	 * @param locationX : X coordinate.
	 * @param locationY : Y coordinate.
	 */
	
	public Location(int ID, double locationX, double locationY) {
		this.setID(ID);
		this.locationX=locationX;
		this.locationY=locationY;
		ArrayList<Passenger> a = new ArrayList();
		ArrayList<Passenger> b = new ArrayList();
		history = a;
		current = b;
		
	}
	/**
	 * returns the distance between this Location and another given as parameter.
	 * @param other other Location 
	 * @return distance between the locations.
	 */
	public double getDistance(Location other) {
		return Math.sqrt((locationX-other.locationX)*(locationX-other.locationX) + (locationY-other.locationY)*(locationY-other.locationY));
		
	}
	/**
	 * adds a passenger to the current passenger list.
	 * @param p Passenger who will be added
	 */
		public void incomingPassenger(Passenger p) {
			current.add(p);
		}
		
		
	/**
	 *removes a passenger from the current list and adds it to the history list. 
	 * @param p Passenger who will be removed and added.
	 */
		public void outgoingPassenger(Passenger p) {
			current.remove(p);
			history.add(p);
		}
/**
 * gets the ID of location.
 * @return ID
 */
		public int getID() {
			return ID;
		}
/**
 * sets the ID of Location
 * @param iD ID of the Location.
 */
		public void setID(int iD) {
			ID = iD;
		}
		
		/**
		 * displays the Location with its X and Y coordinates which have two digits after the decimal point.
		 *  @return the Location displayed as "Location ID : (X Coordinate, Y Coordinate)		
		 */
		public String toString() {
			String coorX = String.valueOf(locationX);
			if(coorX.substring(coorX.indexOf(".")).length()>3){
				coorX = coorX.substring(0, coorX.indexOf(".")+3);	
			}
			if(coorX.substring(coorX.indexOf(".")).length()==2) {
				coorX = coorX + "0";
			}
			String coorY = String.valueOf(locationY);
			if(coorY.substring(coorY.indexOf(".")).length()>3){
				coorY = coorY.substring(0, coorY.indexOf(".")+3);	
			}
			if(coorY.substring(coorY.indexOf(".")).length()==2) {
				coorY = coorY + "0";
			}
			return "Location " + ID + ":" + " (" + coorX + ", " + coorY + ")";
		}
		/**
		 * sorts the current list according to the passengers' ID numbers in ascending order and returns it.
		 * @return the current list.
		 */
		public ArrayList<Passenger> getCurrent() {
			Passenger keep;
			for(int j=0; j<current.size(); j++) {
				for(int k=1; k<current.size()-j; k++) {
				if(current.get(k-1).getID()>current.get(k).getID()) {
					keep=current.get(k-1);
					current.set(k-1, current.get(k));
					current.set(k, keep);
				}
			}
		}
			return current;
		}		
		
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

