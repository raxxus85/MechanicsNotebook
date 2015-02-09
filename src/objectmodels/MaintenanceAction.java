package objectmodels;

import java.io.Serializable;

/**
 *
 * @author Mark
 */
public class MaintenanceAction implements Serializable{
    private Vehicle vehicle;
    private MaintenanceType maintenanceType;
    private Integer odometer;
    private String notes;
    private Mechanic mechanic;
    // private DATE???
    
    /**
     * Main constructor
     * @param incomingVehicle
     * @param incomingMaintenanceType
     * @param incomingOdometer
     * @param incomingNotes 
     */
    public MaintenanceAction(Mechanic incomingMechanic,Vehicle incomingVehicle, MaintenanceType incomingMaintenanceType, Integer incomingOdometer, String incomingNotes){
        this.mechanic = incomingMechanic;
        this.vehicle=incomingVehicle;
        this.maintenanceType = incomingMaintenanceType;
        this.odometer = incomingOdometer;
        this.notes = incomingNotes;
    }
    
    /**
     * Second constructor, when not supplying "notes" for action
     * @param incomingVehicle
     * @param incomingMaintenanceType
     * @param incomingOdometer 
     */
    public MaintenanceAction(Mechanic incomingMechanic,Vehicle incomingVehicle, MaintenanceType incomingMaintenanceType, Integer incomingOdometer){
        this.mechanic = incomingMechanic;
        this.vehicle=incomingVehicle;
        this.maintenanceType = incomingMaintenanceType;
        this.odometer = incomingOdometer;
        this.notes = "";
    }
    
    public Mechanic getMechanic(){
        return this.mechanic;
    }
    
    public void setMechanic(Mechanic incomingMechanic){
        this.mechanic = incomingMechanic;
    }
    
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    
    public void setVehicle(Vehicle incomingVehicle){
        this.vehicle = incomingVehicle;
    }
    
    public MaintenanceType getMaintenanceType(){
        return this.maintenanceType;
    }
    
    public void setMaintenanceType(MaintenanceType incomingMaintenanceType){
        this.maintenanceType = incomingMaintenanceType;
    }
    
    public Integer getOdometer(){
        return this.odometer;
    }
    
    public void setOdometer(Integer incomingOdometer){
        this.odometer = incomingOdometer;
    }
    
    public String getNotes(){
        return this.notes;
    }
    
    public void setNotes(String incomingNotes){
        this.notes = incomingNotes;
    }   
    
    @Override
    public String toString(){
        String returnString = this.maintenanceType.getMaintenanceTypeName() + ","+this.getOdometer() + ", " + this.getNotes();
        return returnString;
    }
    
    /**
     @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(name).
            append(age).
            toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
	} else {
            MaintenanceAction maintenanceAction = (MaintenanceAction) object;
                if (this.color == tiger.getColor()
		&& this.stripePattern == tiger.getStripePattern()) {
		result = true;
			}
		}
		return result;
	}
        * **/
}
