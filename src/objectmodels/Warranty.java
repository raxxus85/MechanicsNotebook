/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mark
 */
public class Warranty implements Serializable{
    String partName;
    String datePurchased;
    String warrantyDuration;
    String description;
    Float cost;
    
    public Warranty(String incomingPartName, String incomingDatePurchased, String incomingWarrantyDuration, String incomingDescription,Float incomingCost){
        this.partName = incomingPartName;
        this.datePurchased = incomingDatePurchased;
        this.warrantyDuration = incomingWarrantyDuration;
        this.description = incomingDescription;
        this.cost = incomingCost;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getWarrantyObject(){
        String[] stringArray = {this.partName,this.datePurchased.toString(), this.warrantyDuration,this.description,this.cost.toString()};
        return stringArray;
    }
    
    public void setPartName(String incomingPartName){
        this.partName = incomingPartName;
    }
    
    public String getPartName(){
        return this.partName;
    }   
    
    public void setDatePurchased(String incomingDatePurchased){
        this.datePurchased = incomingDatePurchased;
    }
    
    public String getDatePurchased(){
        return this.datePurchased;
    }
    
    public void setWarrantyDuration(String incomingWarrantyDuration){
        this.warrantyDuration = incomingWarrantyDuration;
    }
    
    public String getWarrantyDuration(){
        return this.warrantyDuration;
    }
    
    public void setDescription(String incomingDescription){
        this.description = incomingDescription;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setCost(Float incomingCost){
        this.cost = incomingCost;
    }
    
    public Float getCost(){
        return this.cost;
    }
}
