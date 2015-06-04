/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;

/**
 * A subclass of maintenance type
 * <li> The same, except has a Vehicle Model attached to it
 * @author Mark
 */
public class VehicleMaintenanceType extends MaintenanceType implements Serializable{
    private VehicleModel vehicleModel;

    public VehicleMaintenanceType(VehicleModel incomingVehicleModel, String incomingMaintenanceTypeName, Integer incomingMileageInterval, String incomingDescription) {
        super(incomingMaintenanceTypeName, incomingMileageInterval, incomingDescription);
        this.vehicleModel = incomingVehicleModel;
    }
    
    public void setVehicleModel(VehicleModel incomingVehicleModel){
        this.vehicleModel = incomingVehicleModel;
    }
    
    public VehicleModel VehicleModel(){
        return this.vehicleModel;
    }
    
        /**
     * Method used for the sole purpose of filling out a JTable 
     * <li> Last item in array is True, as IS vehicle model Specific
     * @return 
     */
    @Override
    public Object[] getMaintenanceTypeObject(){
        String[] stringArray = {this.getMaintenanceTypeName(),this.getMileageInterval().toString(), this.getDescription(),"True"};
        return stringArray;
    }
}
