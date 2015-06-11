/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;

/**
 *
 * @author Mark
 */
public class MaintenanceType extends BaseObjectModel implements Serializable{
    private String maintenanceTypeName;
    private String description;
    private Integer mileageInterval;
    // testing
    
    private VehicleType vehicleType;
    // private Date timeInterval?
    
    /**
     * Main Constructor
     * @param incomingMaintenanceTypeName
     * @param incomingDescription
     * @param incomingMileageInterval 
     */
    public MaintenanceType(String incomingMaintenanceTypeName, Integer incomingMileageInterval,String incomingDescription){
        this.maintenanceTypeName=incomingMaintenanceTypeName;
        this.description = incomingDescription;
        this.mileageInterval = incomingMileageInterval;
    }
    
     /**
     * Main Constructor when using VehicleType...
     * @param incomingMaintenanceTypeName
     * @param incomingDescription
     * @param incomingMileageInterval 
     * @param incomingVehicleType 
     */
    public MaintenanceType(String incomingMaintenanceTypeName, Integer incomingMileageInterval,String incomingDescription, VehicleType incomingVehicleType){
        this.maintenanceTypeName=incomingMaintenanceTypeName;
        this.description = incomingDescription;
        this.mileageInterval = incomingMileageInterval;
        this.vehicleType = incomingVehicleType;
    }
    
    public void setVehicleType(VehicleType incomingVehicleType){
        this.vehicleType = incomingVehicleType;
    }
    
    public VehicleType getVehicleType(){
        return this.vehicleType;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable 
     * <li> last item in array is "False" as is not Vehicle Model Specific
     * @return 
     */
    public Object[] getMaintenanceTypeObject(){
        //String[] stringArray = {this.maintenanceTypeName,this.mileageInterval.toString(), this.description,"False"};
        String vehicleType = "";
        if(this.vehicleType.equals(VehicleType.CARORTRUCK)){
            vehicleType = "Car/Truck";
        }else{
            vehicleType = "Motorcycle";
        }
        String[] stringArray = {this.maintenanceTypeName,this.mileageInterval.toString(), this.description,vehicleType};
        return stringArray;
    }
    
    public String getMaintenanceTypeName() {
        return maintenanceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMileageInterval() {
        return mileageInterval;
    }
    
    public void setMaintenanceTypeName(String incomingMaintenanceTypeName) {
        this.maintenanceTypeName = incomingMaintenanceTypeName;
    }

    public void setDescription(String incomingDescription) {
        this.description = incomingDescription;
    }

    public void setMileageInterval(int incomingMileageInterval) {
        this.mileageInterval = incomingMileageInterval;
    }
    
    @Override
    public String toString(){
        String returnString = this.getMaintenanceTypeName();
        return returnString;
    }
}
