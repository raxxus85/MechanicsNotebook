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
public class FuelEntry implements Serializable{
    
    private Integer odometer;
    private Float gallons;
    private Float costPerGallon;
    
    public FuelEntry(Integer incomingOdometer, Float incomingGallons, Float incomingCostPerGallon){
        
        this.odometer = incomingOdometer;
        this.gallons = incomingGallons;
        this.costPerGallon = incomingCostPerGallon;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getFuelEntryObject(){
        Float totalFuelCost = this.gallons * this.costPerGallon;
        String[] stringArray = {this.odometer.toString(),this.gallons.toString(), this.costPerGallon.toString(),};
        return stringArray;
    }
    
    public void setOdometer(Integer incomingOdometer){
        this.odometer = incomingOdometer;
    }
    
    public Integer getOdometer(){
        return this.odometer;
    }
    
    public void setGallons(Float incomingGallons){
        this.gallons = incomingGallons;
    }
    
    public Float getGallons(){
        return this.gallons;
    }
    
    public void setCostPerGallon(Float incomingCostPerGallon){
        this.costPerGallon = incomingCostPerGallon;
    }
    
    public Float getCostPerGallon(){
        return this.costPerGallon;
    }
    
}

