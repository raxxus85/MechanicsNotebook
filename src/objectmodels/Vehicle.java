/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

/**
 *
 * @author Mark
 */
public class Vehicle {
    //private String vehicleIdentificationNumber;
    private String make;
    private String model;
    private Integer year;
    private String description;
    
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
    
    
}
