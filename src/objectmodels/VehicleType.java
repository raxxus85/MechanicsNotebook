/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;

/**
 * VehicleType class, used for each vehicle
 * <li> Garage will contain list of Vehicle Types
 * <li> User can add, update, delete Vehicle Types
 * <li> A vehicle will have one Vehicle Type
 * @author Mark
 */
public class VehicleType implements Serializable{
    private String make;
    private String model;
    
    public VehicleType(String incomingMake, String incomingModel){
        this.make=incomingMake;
        this.model=incomingModel;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable 
     * @return 
     */
    public Object[] getVehicleTypeObject(){
        String[] stringArray = {this.make,this.model};
        return stringArray;
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
    
    @Override
    public String toString(){
        return this.make + " " + this.model;
    }
}
