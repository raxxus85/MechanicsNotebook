package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The "main" saving point for MotoGarage Mechanic App. 
 * @author Mark
 */
public class Garage implements Serializable{
    // vehicle trackers
    private Boolean fuelEntriesEnabled = false;
    private Boolean warrantiesEnabled = false;
    private Boolean modificationsEnabled = false;
    private Boolean dragStripSlipsEnabled= false;
    private Boolean vehicleTrackersChanged =true;
    
    // "current" or selected mechanic, customer, vehicle
    private Mechanic currentMechanic=null;
    private Customer currentCustomer=null;
    private Vehicle currentVehicle=null;

    
    // attached data
    private ArrayList<Mechanic> mechanics = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<MaintenanceType> maintenanceTypes = new ArrayList<>();
    
    
    // current object manipulation methods
   
    public void setVehicleTrackersChanged(Boolean incomingBoolean){
        this.vehicleTrackersChanged = incomingBoolean;
    }
    
    public Boolean getVehicleTrackersChanged(){
        return this.vehicleTrackersChanged;
    }
    
    public void setFuelEntriesEnabled(Boolean incomingBoolean){
        this.fuelEntriesEnabled = incomingBoolean;
    }
    
    public Boolean getFuelEntriesEnabled(){
        return this.fuelEntriesEnabled;
    }
    
    public void setWarrantiesEnabled(Boolean incomingBoolean){
        this.warrantiesEnabled = incomingBoolean;
    }
    
    public Boolean getWarrantiesEnabled(){
        return this.warrantiesEnabled;
    }
    
    public void setModificationsEnabled(Boolean incomingBoolean){
        this.modificationsEnabled = incomingBoolean;
    }
    
    public Boolean getModificationsEnabled(){
        return this.modificationsEnabled;
    }
    
    public void setDragStripSlipsEnabled(Boolean incomingBoolean){
        this.dragStripSlipsEnabled = incomingBoolean;
    }
    
    public Boolean getDragStripSlipsEnabled(){
        return this.dragStripSlipsEnabled;
    }
    
    
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
    
    public ArrayList<MaintenanceType> getMaintenanceTypeArrayList(){
        return this.maintenanceTypes;
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
     * Method used to update a Maintenance Type
     * @param originalMaintenanceType
     * @param updatedMaintenanceType
     * @return true if successful
     */
    public boolean updateMaintenanceType(MaintenanceType originalMaintenanceType, MaintenanceType updatedMaintenanceType){
        Integer originalIndex = this.getMaintenanceTypeArrayList().indexOf(originalMaintenanceType);
        this.getMaintenanceTypeArrayList().set(originalIndex, updatedMaintenanceType);
        if(!this.getMaintenanceTypeArrayList().contains(originalMaintenanceType)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method to delete Maintenance Type
     * @param incomingMaintenanceType
     * @return true if successful
     */
    public boolean deleteMaintenanceType(MaintenanceType incomingMaintenanceType){
        ArrayList<MaintenanceType> currentMaintenanceTypes = this.getMaintenanceTypeArrayList();
        if(currentMaintenanceTypes.contains(incomingMaintenanceType)){
            currentMaintenanceTypes.remove(incomingMaintenanceType);
            return true;
        }else{
            return false;
        }
    }
    
    
    
    public void updateCurrentMechanic(Mechanic updatedMechanic){
        //this.currentMechanic = updatedMechanic;
        this.currentMechanic.setFirstName(updatedMechanic.getFirstName());
        this.currentMechanic.setMiddleName(updatedMechanic.getMiddleName());
        this.currentMechanic.setLastName(updatedMechanic.getLastName());
        this.currentMechanic.setDescription(updatedMechanic.getDescription());
        //this.currentCustomer.setImageIcon(updatedMechanic.getImageIcon());
        if(updatedMechanic.getImageIcon()== null){
            this.currentMechanic.setImageIcon(null);
        }else{
            this.currentMechanic.setImageIcon(updatedMechanic.getImageIcon());
        }
    }
    
    public void updateCurrentCustomer(Customer updatedCustomer){
        this.currentCustomer.setFirstName(updatedCustomer.getFirstName());
        this.currentCustomer.setMiddleName(updatedCustomer.getMiddleName());
        this.currentCustomer.setLastName(updatedCustomer.getLastName());
        this.currentCustomer.setDescription(updatedCustomer.getDescription());
        if(updatedCustomer.getImageIcon()==null){
            this.currentCustomer.setImageIcon(null);
        }else{
            this.currentCustomer.setImageIcon(updatedCustomer.getImageIcon());
        }
    }
    
    public void updateCurrentVehicle(Vehicle updatedVehicle){
        this.currentVehicle.setMake(updatedVehicle.getMake());
        this.currentVehicle.setModel(updatedVehicle.getModel());
        this.currentVehicle.setYear(updatedVehicle.getYear());
        this.currentVehicle.setColor(updatedVehicle.getColor());
        this.currentVehicle.setVIN(updatedVehicle.getVIN());
        this.currentVehicle.setOdometer(updatedVehicle.getOdometer());
        this.currentVehicle.setDescription(updatedVehicle.getDescription());
        if(updatedVehicle.getImageIcon()==null){
            this.currentVehicle.setImageIcon(null);
        }else{
            this.currentVehicle.setImageIcon(updatedVehicle.getImageIcon());
        }
    }
    
    
    /**
     * Method to set the current Customer
     * @param incomingCustomer 
     */
    public void setCurrentCustomer(Customer incomingCustomer){
        this.currentCustomer = incomingCustomer;
        // should we place logic here to get a vehicle then?
        if(this.currentCustomer!=null){
            if(this.currentCustomer.getVehicles().size()== 0){
                this.currentVehicle = null;
            }else{
                this.currentVehicle = this.currentCustomer.getVehicles().get(0);
            }
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
     * Method to remove the current Mechanic
     */
    public void deleteCurrentMechanic(){
        this.mechanics.remove(this.getCurrentMechanic());
    }
    
    /**
     * Method to remove the current Customer
     */
    public void deleteCurrentCustomer(){
        this.customers.remove(this.getCurrentCustomer());
    }
    
    /**
     * Method to remove the current Vehicle
     */
    public void deleteCurrentVehicle(){
        this.currentCustomer.getVehicles().remove(this.getCurrentVehicle());
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
