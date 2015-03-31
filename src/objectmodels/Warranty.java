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
public class Warranty implements Serializable{
    String partName;
    Date date;
    String warrantyDuration;
    String description;
    Float cost;
    
    public Warranty(String incomingPartName, Date incomingDate, String incomingWarrantyDuration, String incomingDescription,Float incomingCost){
        this.partName = incomingPartName;
        this.date = incomingDate;
        this.warrantyDuration = incomingWarrantyDuration;
        this.description = incomingDescription;
        this.cost = incomingCost;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getWarrantyObject(){
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String costEdited = "$" + this.cost.toString();
        String[] stringArray = {this.partName,dateInstance.format(this.date), this.warrantyDuration,this.description,costEdited};
        return stringArray;
    }
    
    public void setPartName(String incomingPartName){
        this.partName = incomingPartName;
    }
    
    public String getPartName(){
        return this.partName;
    }   
    
    public void setDate(Date incomingDate){
        this.date = incomingDate;
    }
    
    public Date getDate(){
        return this.date;
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
