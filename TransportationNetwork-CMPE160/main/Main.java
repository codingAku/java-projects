
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.util.*;
import vehicles.*;
import java.io.*;
import passengers.*;
import locations.*;
/**
 * initializes the program using mostly if/else statements. 
 *
 * 
 * @author Ecenur Sezer
 */
public class Main {
	/**
	 * ID number of the passengers.
	 */
	public static int ID;
	/**
	 * ID number of the locations.
	 */
	public static int LocationID=1;
	/**
	 * ID number of the vehicles.
	 */
	public static int VehicleID;
	/**
	 * Main method of the program.
	 * 
	 * Using a scanner main reads the file and uses a loop to perform all actions given in the input file.
	 * There are 3 ArrayLists which contains every passenger, location and vehicle object created separately.
	 * According to the operation number,creates passengers or vehicles or locations, makes the passenger travel, refill their card  etc.
	 * Finally, prints the locations to the file and the passengers who are currently in that location.  
	 * 
	 * @param args takes an array of two elements, the input file and the output file.  
	 * @throws FileNotFoundException if the file given does not exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(new File(args[0]));
		input.useLocale(Locale.US);
		PrintStream output = new PrintStream(new File(args[1]));

		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<PublicTransport> vehicles = new ArrayList<PublicTransport>();
		
		// Uncomment the lines below after implementing the Location class
		
		  Location l = new Location(0, 0, 0); // The first location is always (0,0).
		  locations.add(l);
		
		int operations = input.nextInt(); // operation count
		for(int i=0; i<operations; i++) {
			int operation = input.nextInt();
			if(operation == 1) {
				String passenger = input.next();
				if(passenger.equalsIgnoreCase("D")) {
					int hasDriverLicense=input.nextInt();
					if(hasDriverLicense == 1) {
						int hasCar = input.nextInt();
						if(hasCar==0) {
							passengers.add(new DiscountedPassenger(ID++,true,l));
						}else if(hasCar==1) {
							double fuelCons=input.nextDouble();
							passengers.add(new DiscountedPassenger(ID++, l, fuelCons));
						}
					}else if(hasDriverLicense==0){
						int hasCar = input.nextInt();
						if(hasCar==0) {
						passengers.add(new DiscountedPassenger(ID++, false, l));
						}
					}
				}else if(passenger.equalsIgnoreCase("S")) {
					int hasDriverLicense=input.nextInt();
					if(hasDriverLicense == 1) {
						int hasCar=input.nextInt();
						if(hasCar==0) {
							passengers.add(new StandardPassenger(ID++,true,l));
						}else if(hasCar==1) {
							double fuelCons=input.nextDouble();
							passengers.add(new StandardPassenger(ID++, l, fuelCons));
						}
					}else if(hasDriverLicense==0){
						int hasCar = input.nextInt();
						if(hasCar==0) {
						passengers.add(new StandardPassenger(ID++, false, l));
						}
					}
				}
			}
			else if(operation == 2) {
				double X = input.nextDouble();
				double Y = input.nextDouble();
				locations.add(new Location(LocationID++, X,Y));
			}
			else if(operation == 3) {
				int Type = input.nextInt();
				double X1 = input.nextDouble();
				double X2 = input.nextDouble();
				double Y1 = input.nextDouble();
				double Y2 = input.nextDouble();
			if(Type== 1) {
					vehicles.add(new Bus(VehicleID++,X1, X2, Y1, Y2));
				}
				if(Type == 2) {
					vehicles.add(new Train(VehicleID++, X1, X2, Y1, Y2));
				}
			}
			
			else if(operation == 4) {
				int passengerId = input.nextInt();
				int locationId = input.nextInt();
				int travelType = input.nextInt();
				if(travelType == 1) {
					int vehicleId= input.nextInt();
					if(vehicles.get(vehicleId) instanceof Bus) {
						if(vehicles.get(vehicleId).canRide(passengers.get(passengerId).getCurrentLocation(), locations.get(locationId))) {
					passengers.get(passengerId).ride(vehicles.get(vehicleId), locations.get(locationId));
						}
					}	
				}
				if(travelType == 2) {
					int vehicleId= input.nextInt();
					if(vehicles.get(vehicleId) instanceof Train) {
						if(vehicles.get(vehicleId).canRide(passengers.get(passengerId).getCurrentLocation(), locations.get(locationId))) {
					passengers.get(passengerId).ride(vehicles.get(vehicleId), locations.get(locationId));
						}
					}
				}
				if(travelType == 3) {
					passengers.get(passengerId).drive(locations.get(locationId));
				}
			}
			
			else if(operation == 5) {
				int passengerID=input.nextInt();
				double fuelConsumption = input.nextDouble();
				passengers.get(passengerID).purchaseCar(fuelConsumption);
			}
			else if(operation == 6) {
				int passengerID = input.nextInt();
				double amount = input.nextDouble();
				passengers.get(passengerID).refuel(amount);
			}
			else if(operation == 7) {
				int passengerID = input.nextInt();
				double amount = input.nextDouble();
				passengers.get(passengerID).refillCard(amount);
			}
	
		}

		for(int i=0; i<locations.size(); i++) {
			output.println(locations.get(i));
			for(int k=0; k<locations.get(i).getCurrent().size(); k++) {
				if(i==locations.size()-1 && k==locations.get(i).getCurrent().size()-1) {
					output.print(locations.get(i).getCurrent().get(k));
				}else {
			output.println(locations.get(i).getCurrent().get(k));
				}
			}		
			
		}

		
		
		
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

