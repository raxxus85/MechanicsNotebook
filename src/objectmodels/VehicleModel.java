package objectmodels;

import java.io.Serializable;

/**
 * VehicleModel class, used for each vehicle
 * <li> Garage will contain list of Vehicle Models
 * <li> User can add, update, delete Vehicle Models
 * <li> A vehicle will have one Vehicle Model
 * @author Mark
 */
public class VehicleModel implements Serializable{
    private String make;
    private String model;
    
    public VehicleModel(String incomingMake, String incomingModel){
        this.make=incomingMake;
        this.model=incomingModel;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable 
     * @return 
     */
    public Object[] getVehicleModelObject(){
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
