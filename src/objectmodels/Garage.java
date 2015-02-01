package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The "main" saving point for MotoGarage Mechanic App. 
 * @author Mark
 */
public class Garage implements Serializable{
    // "current" or selected mechanic, customer, vehicle
    private Mechanic currentMechanic=null;
    private Customer currentCustomer=null;
    private Vehicle currentVehicle=null;

    
    // attached data
    private ArrayList<Mechanic> mechanics = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<MaintenanceType> maintenanceTypes = new ArrayList<>();
    
    
    // current object manipulation methods
   
    /**
     * Method to add a new MaintenanceType to the Garage
     * @param incomingMaintenanceType 
     */
    public void addMaintenanceType(MaintenanceType incomingMaintenanceType){
        this.maintenanceTypes.add(incomingMaintenanceType);
    }
    

    /**
     * Method to return an ARRAY of the Maintenance Type, used to populate ComboBoxes, etc
     * @return 
     */
    public MaintenanceType[] getMaintenaceTypeArray(){
        ArrayList<MaintenanceType> maintenanceTypeArrayList = this.maintenanceTypes;
        MaintenanceType[] maintenanceTypeArray = maintenanceTypeArrayList.toArray(new MaintenanceType[maintenanceTypeArrayList.size()]);
        return maintenanceTypeArray;
    }
    
    /**
     * Method to return an [] of the current Vehicles Maintenance Actions, converted from Array List
     * @return MaintenanceAction[]
     */
    public MaintenanceAction[] getMaintenanceActions(){
        Vehicle currentVehicle = this.currentVehicle;
        ArrayList<MaintenanceAction> maintenanceActionArrayList = currentVehicle.getMaintenanceActions();
        MaintenanceAction[] maintenanceActionArray = maintenanceActionArrayList.toArray(new MaintenanceAction[maintenanceActionArrayList.size()]);
        return maintenanceActionArray;
    }
    
    
    /**
     * Method to set the current Mechanic
     * @param incomingMechanic 
     */
    public void setCurrentMechanic(Mechanic incomingMechanic){
        this.currentMechanic = incomingMechanic;
    }
    
    /**
     * Method to get current Mechanic
     * @return the current Mechanic
     */
    public Mechanic getCurrentMechanic(){
        return this.currentMechanic;
    }
    
    /**
     * Method to set the current Customer
     * @param incomingCustomer 
     */
    public void setCurrentCustomer(Customer incomingCustomer){
        this.currentCustomer = incomingCustomer;
        // should we place logic here to get a vehicle then?
        if(this.currentCustomer.getVehicles().size()== 0){
            this.currentVehicle = null;
        }else{
            this.currentVehicle = this.currentCustomer.getVehicles().get(0);
        }
    }
    
    /**
     * Method to get the current Customer
     * @return current Customer
     */
    public Customer getCurrentCustomer(){
        return this.currentCustomer;
    }
    
    /**
     * Method to set the current Vehicle
     * @param incomingVehicle 
     */
    public void setCurrentVehicle(Vehicle incomingVehicle){
        this.currentVehicle = incomingVehicle;
    }
    
    /**
     * Method to get the current Vehicle
     * @return current Vehicle
     */
    public Vehicle getCurrentVehicle(){
        return this.currentVehicle;
    }
    
    // end current object manipulation methods
    
    
    /**
     * Method to return the ArrayList<Customer> Customers of the garage.
     * @return ArrayList<Customer> customers
     */
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }
    
    /**
     * Method to get the ArrayList<Mechanic> Mechanics of the garage.
     * @return ArrayList<Mechanic> all the Mechanics
     */
    public ArrayList<Mechanic> getMechanics(){
        return this.mechanics;
    }
    
    /**
     * Method to get the ArrayList<Vehicle> Vehicles from the current Customer.
     * @return ArrayList<Vehicle> all the Vehicles
     */
    public ArrayList<Vehicle> getVehicles(){
        return this.currentCustomer.getVehicles();
    }
    
    /**
     * Method to add a Mechanic to the Garage
     * @param incomingMechanic 
     */
    public void addMechanic(Mechanic incomingMechanic){
        this.mechanics.add(incomingMechanic);
        // If it's the first Mechanic to be added, it needs to be the "current" mechanic
        if(this.mechanics.size()==1){
            this.currentMechanic = incomingMechanic;
        }
    }
    
    /**
     * Method to add a Customer to the Garage
     * @param incomingCustomer 
     */
    public void addCustomer(Customer incomingCustomer){
        this.customers.add(incomingCustomer);
        // If it's the first Customer to be added, it needs to be the "current" Customer
        if(this.customers.size()==1){
            this.currentCustomer = incomingCustomer;
        }
    }
    
    /**
     * Method to add a Vehicle, picks the CURRENT Customer
     * @param incomingVehicle
     * @return if there is a Customer, return true after adding Vehicle, else return false
     */
    public boolean addVehicle(Vehicle incomingVehicle){
        if(this.currentCustomer!=null){
            this.currentCustomer.addVehicle(incomingVehicle);
            if(this.currentCustomer.getVehicles().size()==1){
                this.currentVehicle = incomingVehicle;
            }
            return true;
        }else{
            return false;
        }
        
    }
    
    /**
     * Return the array of Mechanics (converted from ArrayList)
     * 
    */
    public Mechanic[] getMechanicArray(){
        ArrayList<Mechanic> mechanicArrayList = this.getMechanics();
        Mechanic[] mechanicArray = mechanicArrayList.toArray(new Mechanic[mechanicArrayList.size()]);
        return mechanicArray;
    }
    
    /**
     * Return the array of Customers (converted from ArrayList)
     * 
    */
    public Customer[] getCustomerArray(){
        ArrayList<Customer> customerArrayList = this.getCustomers();
        Customer[] customerArray = customerArrayList.toArray(new Customer[customerArrayList.size()]);
        return customerArray;
    }
    
     /**
     * Method to return the Vehicles from the current customer
     * 
     * @return Vehicle[] for that Customer else NULL if no current customer or no vehicles for said customer
     */
    public Vehicle[] getVehicleArray(){
        if(this.currentCustomer!= null && this.currentCustomer.getVehicles().size()>0){
            ArrayList<Vehicle> vehicleArrayList = this.currentCustomer.getVehicles();
            Vehicle[] vehicleArray = vehicleArrayList.toArray(new Vehicle[vehicleArrayList.size()]);
            return vehicleArray;
        }else{
            Vehicle[] emptyVehicleArray = {};
            return emptyVehicleArray;
        }
    }
}
