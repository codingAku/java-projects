
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
/**
 * creates a Car object with the same ID number of its owner.
 * Car has 2 fields which state the amount of fuel it has in gas tank and rate of consumption of the fuel per km.
 * 
 *
 */

public class Car {
	private int ownerID;
	private double fuelAmount;
	private double fuelConsumption;
	
	/**
	 * Constructor of the car.
	 * @param ID ID of the car (same with its owner).
	 * @param fuelConsumption fuel consumption rate of the car. 
	 */
	public Car(int ID, double fuelConsumption) {
		this.setOwnerID(ID);
		this.fuelConsumption=fuelConsumption;
		this.fuelAmount = 0;
	}
	/**
	 * adds the amount to the gas tank.
	 * @param amount the fuel amount to be added.
	 */
	public void refuel(double amount) {
		this.fuelAmount += amount;
	}
	/**
	 * 
	 * @return the fuel amount car has.
	 */
	public double getFuelAmount() {
		return fuelAmount;
	}
	/**
	 * 
	 * @param fuelAmount sets the fuel amount.
	 */
	public void setFuelAmount(double fuelAmount) {
		this.fuelAmount = fuelAmount;
	}
	/**
	 * 
	 * @param fuelConsumption sets the fuel consumption rate per km.
	 */
	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption=fuelConsumption;
	}
	/**
	 * 
	 * @return the fuel consumption rate per km.
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	/**
	 * 
	 * @return ID of car's owner.
	 */
	public int getOwnerID() {
		return ownerID;
	}
	/**
	 * 
	 * @param ownerID sets the car's ID (same with owner).
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

