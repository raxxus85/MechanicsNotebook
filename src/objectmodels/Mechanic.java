package objectmodels;

import java.io.Serializable;

/**
 * Mechanic Class
 * @author Mark
 */
public class Mechanic implements Serializable{
    private String firstName;
    private String middleInitial;
    private String lastName;
    //private String name;
    private String description;
    

        
    public Mechanic(String incomingFirstName, String incomingMiddleInitial, String incomingLastName){
        this.firstName = incomingFirstName;
        this.middleInitial = incomingMiddleInitial;
        this.lastName = incomingLastName;
    }    
    
    public Mechanic(String incomingFirstName, String incomingMiddleInitial, String incomingLastName, String incomingDescription){
        this.description = incomingDescription;
        this.firstName = incomingFirstName;
        this.middleInitial = incomingMiddleInitial;
        this.lastName= incomingLastName;
    }
    

    
    public String getFullName(){
        return this.firstName + " " + middleInitial + " " + lastName;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setFirstName(String incomingFirstName){
        this.firstName = incomingFirstName;
    }
    
    public String getMiddleInitial(){
        return this.middleInitial;
    }
    
    public void setMiddleInitial(String incomingMiddleInitial){
        this.middleInitial = incomingMiddleInitial;
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
    
    /**
     * Override toString to display relevent info etc
     * @return 
     */
  @Override
  public String toString() {
    return this.getFirstName() + " " + this.getMiddleInitial() + " " + this.getLastName();
  }
}
