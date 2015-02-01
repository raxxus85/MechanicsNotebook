/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mark
 */
public class Vehicle implements Serializable{
    //private String vehicleIdentificationNumber;
    private String make;
    private String model;
    private Integer year;
    private Float odometer;
    private String description;
    private ArrayList<MaintenanceAction> maintenanceActions = new ArrayList<>();
    
    /**
     * Main Constructor for Vehicle
     * @param incomingMake
     * @param incomingModel
     * @param incomingYear
     * @param incomingDescription 
     */
    public Vehicle(String incomingMake, String incomingModel,Integer incomingYear, String incomingDescription){
        this.make = incomingMake;
        this.model = incomingModel;
        this.year = incomingYear;
        this.description = incomingDescription;
    }
    
    /**
     * Secondary Constructor for Vehicle
     * @param incomingMake
     * @param incomingModel
     * @param incomingYear 
     */
    public Vehicle(String incomingMake, String incomingModel, Integer incomingYear){
        this(incomingMake,incomingModel,incomingYear, "");
    }
    
    public ArrayList<MaintenanceAction> getMaintenanceActions(){
        return this.maintenanceActions;
    }
    
    public MaintenanceAction[] getMaintenanceActionsArray(){
        Vehicle currentVehicle = this;
        ArrayList<MaintenanceAction> maintenanceActionArrayList = currentVehicle.getMaintenanceActions();
        MaintenanceAction[] maintenanceActionArray = maintenanceActionArrayList.toArray(new MaintenanceAction[maintenanceActionArrayList.size()]);
        return maintenanceActionArray;
    }
    
    public void addMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActions.add(incomingMaintenanceAction);
    }
    
    public void removeMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActions.remove(incomingMaintenanceAction);
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
    
    public Integer getYear(){
        return this.year;
    }
    
    public void setYear(Integer incomingYear){
        this.year = incomingYear;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String incomingDescription){
        this.description = incomingDescription;
    }
    
    public void setOdometer(Float incomingOdometer){
        this.odometer = incomingOdometer;
    }
    
    public Float getOdometer(){
        return this.odometer;
    }
    
    @Override
    public String toString() {
        return this.getYear() + "," + this.getMake()+ " " + this.getModel();
  }
    
}
