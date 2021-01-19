
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;
import vehicles.*;
import locations.*;
import java.util.Locale;
import interfaces.*;
/**
 * creates Passenger objects.
 * 
 * It implements two interfaces for the ability of owning a car and using public transport.
 * 
 *
 */



public class Passenger implements ownCar, usePublicTransport {
	/**
	 * the ID number of passenger. It is given by the program automatically.
	 */
	private int ID;
	/**
	 * the boolean value for the state of having drivers license. It is true if the passenger has a license.
	 */
	private boolean hasDriversLicense;
	/**
	 * the double value that represents the amount of money passengers have in their travel card.
	 */
	private double cardBalance;
	/**
	 * the Car object of the Passenger.
	 */
	private Car car;
	/**
	 * represents the current Location of the passenger.
	 */
	private Location currentLocation;
	
	/**
	 * constructor for Passengers who does not own a car.
	 * 
	 * @param ID ID number of the passenger.
	 * @param hasDriversLicense boolean value for the existance of driver's license.
	 * @param l starting Location of the passenger.
	 */
	public Passenger(int ID, boolean hasDriversLicense, Location l) {
		this.setID(ID);
		this.setHasDriversLicense(hasDriversLicense);
		this.currentLocation =l;
		this.currentLocation.incomingPassenger(this);
		
		
	}
	/**
	 * constructor for the Passengers who owns a car.
	 * Additionaly, sets the driversLicense value to true and sets a fuel consumption value to the Passenger's car along with and ID and starting Location.
	 * @param ID ID number of the passenger.
	 * @param fuelConsumption double value for the fuel consumption rate of the car.
	 * @param l starting Location of the passenger.
	 */
	
	public Passenger(int ID, Location l, double fuelConsumption) {
		this.setID(ID);
		this.currentLocation=l;
		this.currentLocation.incomingPassenger(this);
		setHasDriversLicense(true);
		this.purchaseCar(fuelConsumption);
		
		
		
	}
	/**
	 * @returns the ID of the passenger.
	 */
	public int getID() {
		return ID;
	}
	/**
	 * 
	 * @param iD ID value to set. (integer value)
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the boolean value for having driver's license.
	 */
	public boolean isHasDriversLicense() {
		return hasDriversLicense;
	}
	/**
	 * 
	 * @param hasDriversLicense boolean value to set the driver's license status.
	 */
	public void setHasDriversLicense(boolean hasDriversLicense) {
		this.hasDriversLicense = hasDriversLicense;
	}
	/**
	 * 
	 * @param amount Amount to set card balance.
	 */
	public void setCardBalance(double amount) {
		this.cardBalance=amount;
	}
	/**
	 * 
	 * @return the card balance.
	 */
	public double getCardBalance() {
		return cardBalance;
	}
	/**
	 * checks if the passenger has a car.
	 * @return true if the Passenger owns a car.
	 */
	public boolean hasCar() {
		if(this.getCar()==null) {
			return false;
		}
		return true;
	}
	/**
	 * Override method of the usePublicTransport interface. 
	 * Rides a passenger to a location using public transport vehicles if it required conditions are required. 
	 * 
	 * checks if the given Public transport vehicle is bus or train.
	 * checks for the card balance if it has enough to travel.
	 * Changes the passenger's current location to the location they traveled.
	 * Removes the passenger from the current list of its first location and add to its history list. 
	 * Adds the passenger to the current list of the last Location.
	 * @param p Public Transport vehicle. 
	 * @param l  travel Location. 
	 * 
	 */
	public void ride(PublicTransport p, Location l) {
		if(p.canRide(currentLocation, l)) {
			if(p instanceof Bus) {
				Bus t = (Bus) p;
				if(getCardBalance()>=t.getPrice(this)) {
					this.setCardBalance(getCardBalance()-t.getPrice(this));
					currentLocation.outgoingPassenger(this);
					currentLocation = l;
					l.incomingPassenger(this);
			}
		}else if(p instanceof Train) {
			Train t = (Train) p;
			if(getCardBalance()>=t.getPrice(l, this)) {
				setCardBalance(getCardBalance()-t.getPrice(l, this));
				currentLocation.outgoingPassenger(this);
				currentLocation = l;
				l.incomingPassenger(this);
			}
		}
			
	}
	}
	/**
	 * 
	 * @return the current location.
	 */

	public Location getCurrentLocation() {
		return currentLocation;
	}
	/**
	 * 
	 * @param l Location to set current.
	 */
	public void setCurrentLocation(Location l) {
		currentLocation = l;
	}
	
	
	
	@Override
	/**
	 * override method of PublicTransport interface.
	 * @param amount Amount to add to the travel card.
	 */
	public void refillCard(double amount) {
		cardBalance = cardBalance + amount;
		
	}


	@Override
	/**
	 * Override method of ownCar interface.
	 * @param amount Amount to add to fuel tank.
	 */
	public void refuel(double amount) {
		if(this.hasCar()) {
		car.setFuelAmount(car.getFuelAmount()+amount);
		}
	}


	@Override
	/**
	 * Override method of ownCar interface.
	 * drives a passenger to the determinated location if required conditions are provided.
	 * Checks if the passenger owns a car.
	 * checks if the car has enough fuel to drive the distance between passenger's current location and the determined location.
	 * removes a passenger from the Location's current list and adds it to the history list.
	 * adds passenger to the current list of the Location it arrived. 
	 * 
	 * @param l the Location that the passenger drives.
	 */
	public void drive(Location l) {
		if(this.isHasDriversLicense()) {
			if(this.hasCar()) {
			if(car.getFuelAmount() >= l.getDistance(currentLocation)*car.getFuelConsumption()) {
				this.getCar().setFuelAmount(car.getFuelAmount()-(l.getDistance(currentLocation)*this.getCar().getFuelConsumption()));
				currentLocation.outgoingPassenger(this);
				setCurrentLocation(l);
				l.incomingPassenger(this);
				}
			}
		}
	}


	@Override
	/**
	 * Override method of the ownCar interface.
	 * sets the driver license true and creates a Car object for the passenger.
	 * 
	 * @param fuelConsumption fuel consumption rate of the car.
	 */
	public void purchaseCar(double fuelConsumption) {
		car = new Car(this.getID(),fuelConsumption);
		this.setHasDriversLicense(true);
		
	}
	/**
	 * overrides the toString method in order to print Passengers in given format in main.
	 * fuel amount or the amount in travel card has two digits after the decimal.
	 */
	public String toString() {
		if(this.hasCar()) {
		String fuel = String.valueOf((this.car.getFuelAmount()));
		if(fuel.substring(fuel.indexOf(".")).length()>3) {
			fuel = fuel.substring(0, fuel.indexOf(".")+3);	
		}
		if(fuel.substring(fuel.indexOf(".")).length()==2) {
			fuel = fuel + "0";
		}
		return "Passenger " + ID + ": " + fuel;
		}else {
			String card = String.valueOf(this.getCardBalance());
			if(card.substring(card.indexOf(".")).length()>3) {
			card = card.substring(0, card.indexOf(".")+3);
			}
			if(card.substring(card.indexOf(".")).length()==2) {
				card = card + "0";
			}
			return "Passenger " + ID + ": " + card;
		}
	}
	/**
	 * 
	 * @return the car of the Passenger.
	 */
	public Car getCar() {
		return car;
	}
	

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

