package objectmodels;

/**
 *
 * @author Mark
 */
public class MaintenanceAction {
    private Vehicle vehicle;
    private MaintenanceType maintenanceType;
    private Integer odometer;
    private String notes;
    // private DATE???
    
    /**
     * Main constructor
     * @param incomingVehicle
     * @param incomingMaintenanceType
     * @param incomingOdometer
     * @param incomingNotes 
     */
    public MaintenanceAction(Vehicle incomingVehicle, MaintenanceType incomingMaintenanceType, Integer incomingOdometer, String incomingNotes){
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
    public MaintenanceAction(Vehicle incomingVehicle, MaintenanceType incomingMaintenanceType, Integer incomingOdometer){
        this.vehicle=incomingVehicle;
        this.maintenanceType = incomingMaintenanceType;
        this.odometer = incomingOdometer;
        this.notes = "";
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
        String returnString = this.maintenanceType.getMaintenanceTypeName() + ","+this.getOdometer();
        return returnString;
    }
}
