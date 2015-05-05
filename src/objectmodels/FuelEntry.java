/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mark
 */
public class FuelEntry extends BaseObjectModel implements Serializable{
    
    private Integer odometer;
    private Date date;
    private Float gallons;
    private Float costPerGallon;
    
    public FuelEntry(Integer incomingOdometer,Date incomingDate, Float incomingGallons, Float incomingCostPerGallon){
        
        this.odometer = incomingOdometer;
        this.date = incomingDate;
        this.gallons = incomingGallons;
        this.costPerGallon = incomingCostPerGallon;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getFuelEntryObject(){
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        
        // parse to 2 decimal places for simplicty... (Figured out total cost later?)
        Float totalFuelCost = this.gallons * this.costPerGallon;
        totalFuelCost = Float.parseFloat(String.format("%.2f", totalFuelCost));
        
        
        String totalFuelCostEdited = "$" + totalFuelCost.toString();
        String costPerGallonEdited = "$" +this.costPerGallon.toString();
        String[] stringArray = {this.odometer.toString(),dateInstance.format(this.date),this.gallons.toString(), costPerGallonEdited,totalFuelCostEdited};
        return stringArray;
    }
    
    public void setDate(Date incomingDate){
        this.date = incomingDate;
    }
    
    public Date getDate(){
        return this.date;
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

