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
    private ArrayList<FuelEntry> fuelEntries = new ArrayList<>();
    private ArrayList<Warranty> warranties = new ArrayList<>();
    
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
    
    public void editFuelEntry(FuelEntry existingFuelEntry, FuelEntry updatedFuelEntry){
        int indexOfExistingFuelEntry = this.fuelEntries.indexOf(existingFuelEntry);
        //MaintenanceAction tempMaintenanceAction = this.maintenanceActions.get(indexOfExistingMaintenanceAction);
        this.fuelEntries.get(indexOfExistingFuelEntry).setOdometer(updatedFuelEntry.getOdometer());
        this.fuelEntries.get(indexOfExistingFuelEntry).setGallons(updatedFuelEntry.getGallons());
        this.fuelEntries.get(indexOfExistingFuelEntry).setCostPerGallon(updatedFuelEntry.getCostPerGallon());
    }
    
    public void editWarranty(Warranty existingWarranty, Warranty updatedWarranty){
        int indexOfExistingWarranty = this.warranties.indexOf(existingWarranty);

        this.warranties.get(indexOfExistingWarranty).setPartName(updatedWarranty.getPartName());
        this.warranties.get(indexOfExistingWarranty).setDatePurchased(updatedWarranty.getDatePurchased());
        this.warranties.get(indexOfExistingWarranty).setWarrantyDuration(updatedWarranty.getWarrantyDuration());
        this.warranties.get(indexOfExistingWarranty).setDescription(updatedWarranty.getDescription());
        this.warranties.get(indexOfExistingWarranty).setCost(updatedWarranty.getCost());
    }
    
    public void addWarranty(Warranty incomingWarranty){
        this.warranties.add(incomingWarranty);
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
    
    public void addFuelEntry(FuelEntry incomingFuelEntry){
        this.fuelEntries.add(incomingFuelEntry);
    }
    
    public ArrayList<FuelEntry> getFuelEntries(){
        return this.fuelEntries;
    }
    
    public ArrayList<Warranty> getWarranties(){
        return this.warranties;
    }
    
    public Warranty[] getWarrantyArray(){
        Vehicle currentVehicle = this;
        ArrayList<Warranty> warrantyList = currentVehicle.getWarranties();
        Warranty[] warrantyArray = warrantyList.toArray(new Warranty[warrantyList.size()]);
        return warrantyArray;
    }
    
    public FuelEntry[] getFuelEntriesArray(){
        Vehicle currentVehicle = this;
        ArrayList<FuelEntry> fuelEntryList = currentVehicle.getFuelEntries();
        FuelEntry[] fuelEntryArray = fuelEntryList.toArray(new FuelEntry[fuelEntryList.size()]);
        return fuelEntryArray;    
    }
    
    public ArrayList<FuelEntry> getSortedFuelEntryList(){
        Vehicle currentVehicle = this;
        ArrayList<FuelEntry> fuelEntryList = currentVehicle.getFuelEntries();
        Collections.sort(fuelEntryList, new Comparator<FuelEntry>() {
            @Override
            public int compare(FuelEntry o1, FuelEntry o2) {
                return Double.compare(o1.getOdometer(), o2.getOdometer());
            }
        });
        return fuelEntryList;
    }
    
    public FuelEntry[] getSortedFuelEntryArray(){
        Vehicle currentVehicle = this;
        ArrayList<FuelEntry> fuelEntryList = currentVehicle.getFuelEntries();
        Collections.sort(fuelEntryList, new Comparator<FuelEntry>() {
            @Override
            public int compare(FuelEntry o1, FuelEntry o2) {
                return Double.compare(o1.getOdometer(), o2.getOdometer());
            }
        });
        
        FuelEntry[] fuelEntryArray = fuelEntryList.toArray(new FuelEntry[fuelEntryList.size()]);
        
        return fuelEntryArray;
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
    
    public void deleteFuelEntry(FuelEntry incomingFuelEntry){
        this.fuelEntries.remove(incomingFuelEntry);
    }
    
    public void deleteWarranty(Warranty incomingWarranty){
        this.warranties.remove(incomingWarranty);
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
