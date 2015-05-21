/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;

/**
 *
 * @author Mark
 */
public class ModelSpecificMaintenanceType extends MaintenanceType implements Serializable{

    public ModelSpecificMaintenanceType(String incomingMaintenanceTypeName, Integer incomingMileageInterval, String incomingDescription) {
        super(incomingMaintenanceTypeName, incomingMileageInterval, incomingDescription);
    }
    
}
