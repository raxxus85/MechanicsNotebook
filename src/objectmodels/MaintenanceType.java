/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

/**
 *
 * @author Mark
 */
public class MaintenanceType {
    private String maintenanceTypeName;
    private String description;
    private int mileageInterval;
    // private Date timeInterval?
    
    public String getMaintenanceTypeName() {
        return maintenanceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public int getMileageInterval() {
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
