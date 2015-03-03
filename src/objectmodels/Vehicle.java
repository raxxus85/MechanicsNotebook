/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;

/**
 *
 * @author Mark
 */
public class Vehicle implements Serializable{
    //private String vehicleIdentificationNumber;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private String vin;
    private Integer odometer;
    private String description;
    private ArrayList<MaintenanceAction> maintenanceActions = new ArrayList<>();
    
    private ImageIcon imageIcon;
    /**
     * Main Constructor for Vehicle
     * @param incomingMake
     * @param incomingModel
     * @param incomingYear
     * @param incomingDescription 
     */
    public Vehicle(String incomingMake, String incomingModel,Integer incomingYear,String incomingColor, String incomingVIN,  Integer incomingOdometer, String incomingDescription){
        this.make = incomingMake;
        this.model = incomingModel;
        this.year = incomingYear;
        this.color = incomingColor;
        this.vin = incomingVIN;
        this.odometer = incomingOdometer;
        this.description = incomingDescription;
    }
    
    public void editMaintenanceAction(MaintenanceAction existingMaintenanceAction, MaintenanceAction updatedMaintenanceAction){
        //int indexOfExistingMaintenanceAction = this.maintenanceActions.get(1);
        int indexOfExistingMaintenanceAction = this.maintenanceActions.indexOf(existingMaintenanceAction);
        //MaintenanceAction tempMaintenanceAction = this.maintenanceActions.get(indexOfExistingMaintenanceAction);
        this.maintenanceActions.get(indexOfExistingMaintenanceAction).setMaintenanceType(updatedMaintenanceAction.getMaintenanceType());
        this.maintenanceActions.get(indexOfExistingMaintenanceAction).setMechanic(updatedMaintenanceAction.getMechanic());
        this.maintenanceActions.get(indexOfExistingMaintenanceAction).setNotes(updatedMaintenanceAction.getNotes());
        this.maintenanceActions.get(indexOfExistingMaintenanceAction).setOdometer(updatedMaintenanceAction.getOdometer());
        this.maintenanceActions.get(indexOfExistingMaintenanceAction).setVehicle(updatedMaintenanceAction.getVehicle());
        
    }
    
    public Object[] getVehicleObject(){
        String[] stringArray = {this.make,this.model, this.year.toString(), this.odometer.toString()};
        return stringArray;
    }
    
    public void setImageIcon(ImageIcon incomingImageIcon){
        this.imageIcon = incomingImageIcon;
    }
    
    public ImageIcon getImageIcon(){
        return this.imageIcon;
    }
    
    public void setColor(String incomingColor){
        this.color = incomingColor;
    }      
    
    public String getColor(){
        return this.color;
    }
            
    public void setVIN(String incomingVIN){
        this.vin = incomingVIN;
    }
    
    public String getVIN(){
        return this.vin;
    }
    
    public ArrayList<MaintenanceAction> getMaintenanceActions(){
        return this.maintenanceActions;
    }
    
    /**
     * Method used to return a sorted ArrayList of MaintenanceActions, based on Odometer
     * @return ArrayList<MaintenanceAction>
     */
    public ArrayList<MaintenanceAction> getSortedMaintenanceActionsList(){
        ArrayList<MaintenanceAction> maintenanceActionList = this.maintenanceActions;
        Collections.sort(maintenanceActionList, new Comparator<MaintenanceAction>() {
            @Override
            public int compare(MaintenanceAction o1, MaintenanceAction o2) {
                return Double.compare(o1.getOdometer(), o2.getOdometer());
            }
        });
        return maintenanceActionList;
    }
    
    public MaintenanceAction[] getSortedMaintenanceActionsArray(){
        ArrayList<MaintenanceAction> maintenanceActionList = this.maintenanceActions;
        Collections.sort(maintenanceActionList, new Comparator<MaintenanceAction>() {
            @Override
            public int compare(MaintenanceAction o1, MaintenanceAction o2) {
                return Double.compare(o1.getOdometer(), o2.getOdometer());
            }
        });
        MaintenanceAction[] maintenanceActionArray = maintenanceActionList.toArray(new MaintenanceAction[maintenanceActionList.size()]);
        
        
        return maintenanceActionArray;
    }
    
    public MaintenanceAction[] getMaintenanceActionsArray(){
        Vehicle currentVehicle = this;
        ArrayList<MaintenanceAction> maintenanceActionArrayList = currentVehicle.getMaintenanceActions();
        MaintenanceAction[] maintenanceActionArray = maintenanceActionArrayList.toArray(new MaintenanceAction[maintenanceActionArrayList.size()]);
        return maintenanceActionArray;
    }
    
    /**
     * Method used to return a 2d Array used for JTable... FILL THIS OUT!!! 
     * @return Object[][]
     */
    public Object[][] getMaintenanceDoubleArray(){       
        Vehicle currentVehicle = this;
        ArrayList<MaintenanceAction> maintenanceActionArrayList = currentVehicle.getMaintenanceActions();
        MaintenanceAction[] maintenanceActionArray = maintenanceActionArrayList.toArray(new MaintenanceAction[maintenanceActionArrayList.size()]);
        String[][] object2DArray = new String[maintenanceActionArray.length][];

        for(int counter = maintenanceActionArray.length - 1;counter>-1; counter = counter-1){
            String odometer = maintenanceActionArray[counter].getOdometer().toString();
            String maintenanceType = maintenanceActionArray[counter].getMaintenanceType().toString();
            String notes = maintenanceActionArray[counter].getNotes();
            String performedBy = maintenanceActionArray[counter].getMechanic().toString();
            String[] testStringArray = {odometer,maintenanceType,notes,performedBy};        
                    // odo maint type , notes, performed by

            object2DArray[counter]= testStringArray;
        }
   
        return object2DArray;
    }

    
    public void updateMileage(Integer incomingMileage){
        this.odometer = incomingMileage;
    }
    
    public void addMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActions.add(incomingMaintenanceAction);
    }
    
    public void deleteMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
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
    
    public void setOdometer(Integer incomingOdometer){
        this.odometer = incomingOdometer;
    }
    
    public Integer getOdometer(){
        return this.odometer;
    }
    
    @Override
    public String toString() {
        return this.getYear() + " " + this.getMake()+ " " + this.getModel();
  }
    
}
