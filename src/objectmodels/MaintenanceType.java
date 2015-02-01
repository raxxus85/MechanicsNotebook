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
public class MaintenanceType implements Serializable{
    private String maintenanceTypeName;
    private String description;
    private Integer mileageInterval;
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
