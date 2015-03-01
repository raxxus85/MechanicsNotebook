/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Mark
 */
public class Customer implements Serializable{
    private String firstName;
    private String middleName;
    private String lastName;
    private String description;
    // attached info
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ImageIcon imageIcon;
    
    // CONSTRUCTORS
    
    public Customer(String incomingFirstName, String incomingMiddleName, String incomingLastName){
        this.firstName = incomingFirstName;
        this.middleName = incomingMiddleName;
        this.lastName = incomingLastName;
    }    
    
    public Customer(String incomingFirstName, String incomingMiddleName, String incomingLastName, String incomingDescription){
        this.description = incomingDescription;
        this.firstName = incomingFirstName;
        this.middleName = incomingMiddleName;
        this.lastName= incomingLastName;
    }
    
    // END CONSTRUCTORS
    
    public Object[] getCustomerObject(){
        String[] stringArray = {this.firstName,this.middleName, this.lastName};
        return stringArray;
    }
    
    public void setImageIcon(ImageIcon incomingImageIcon){
        this.imageIcon= incomingImageIcon;
    }
    
    public ImageIcon getImageIcon(){
        return this.imageIcon;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setFirstName(String incomingFirstName){
        this.firstName = incomingFirstName;
    }
    
    public String getMiddleName(){
        return this.middleName;
    }
    
    public void setMiddleName(String incomingMiddleName){
        this.middleName = incomingMiddleName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setLastName(String incomingLastName){
        this.lastName = incomingLastName;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String incomingDescription){
        this.description = incomingDescription;
    }
    
    public Vehicle[] getVehicleArray(){
        ArrayList<Vehicle> vehicleArrayList = this.vehicles;
        Vehicle[] vehicleArray = vehicleArrayList.toArray(new Vehicle[vehicleArrayList.size()]);
        return vehicleArray;
    }
    
    public ArrayList<Vehicle> getVehicles(){
        return this.vehicles;
    }
    
    public void addVehicle(Vehicle incomingVehicle){
        this.vehicles.add(incomingVehicle);
    }
    
    public void removeVehicle(Vehicle incomingVehicle){
        this.vehicles.remove(incomingVehicle);
    }
    
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
  }
}
