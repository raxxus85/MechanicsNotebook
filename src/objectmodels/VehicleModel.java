package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * VehicleModel class, used for each vehicle
 * <li> Garage will contain list of Vehicle Models
 * <li> User can add, update, delete Vehicle Models
 * <li> A vehicle will have one Vehicle Model
 * @author Mark
 */
public class VehicleModel implements Serializable{
    private String make;
    private String model;
    private ArrayList<VehicleMaintenanceType> vehicleMaintenanceTypes = new ArrayList<>();
    
    public VehicleModel(String incomingMake, String incomingModel){
        this.make=incomingMake;
        this.model=incomingModel;
    }
    
    public void addVehicleMaintenanceType(VehicleMaintenanceType incomingVehicleMaintenanceType){
        this.vehicleMaintenanceTypes.add(incomingVehicleMaintenanceType);
    }
    
    public void deleteVehicleMaintenanceType(VehicleMaintenanceType incomingVehicleMaintenanceType){
        this.vehicleMaintenanceTypes.remove(incomingVehicleMaintenanceType);
    }
    
    public void editVehicleMaintenanceType(VehicleMaintenanceType existingVehicleMaintenanceType, VehicleMaintenanceType updatedVehicleMaintenanceType){
        int indexOfExistingVehicleMaintenaceType = this.vehicleMaintenanceTypes.indexOf(existingVehicleMaintenanceType);
        
        this.vehicleMaintenanceTypes.get(indexOfExistingVehicleMaintenaceType).setDescription(updatedVehicleMaintenanceType.getDescription());
        this.vehicleMaintenanceTypes.get(indexOfExistingVehicleMaintenaceType).setMaintenanceTypeName(updatedVehicleMaintenanceType.getMaintenanceTypeName());
        this.vehicleMaintenanceTypes.get(indexOfExistingVehicleMaintenaceType).setMileageInterval(updatedVehicleMaintenanceType.getMileageInterval());

    }
    
    public ArrayList<VehicleMaintenanceType> getVehicleMaintenanceTypesList(){
        return this.vehicleMaintenanceTypes;
    }
    
    public VehicleMaintenanceType[] getVehicleMaintenanceTypesArray(){
        ArrayList<VehicleMaintenanceType> vehicleMaintenanceTypesList = this.getVehicleMaintenanceTypesList();
        VehicleMaintenanceType[] vehicleMaintenanceTypesArray = vehicleMaintenanceTypesList.toArray(new VehicleMaintenanceType[vehicleMaintenanceTypesList.size()]);
        return vehicleMaintenanceTypesArray;
    }
    
    
    /**
     * Method used for the sole purpose of filling out a JTable 
     * @return 
     */
    public Object[] getVehicleModelObject(){
        String[] stringArray = {this.make,this.model};
        return stringArray;
    }
    
    public String getMake(){
        return this.make;
    }
    
    public void setMake(String incomingMake){
        this.make = incomingMake;
    }
    
    public String getModel(){
        return this.model;
    }
    
    public void setModel(String incomingModel){
        this.model = incomingModel;
    }
    
    @Override
    public String toString(){
        return this.make + " " + this.model;
    }
}
