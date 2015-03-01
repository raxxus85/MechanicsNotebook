package objectmodels;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Mechanic Class
 * @author Mark
 */
public class Mechanic implements Serializable{
    private String firstName;
    private String middleName;
    private String lastName;
    //private String name;
    private String description;
    private ImageIcon imageIcon;

        
    public Mechanic(String incomingFirstName, String incomingMiddleName, String incomingLastName){
        this.firstName = incomingFirstName;
        this.middleName = incomingMiddleName;
        this.lastName = incomingLastName;
    }    
    
    public Mechanic(String incomingFirstName, String incomingMiddleName, String incomingLastName, String incomingDescription){
        this.description = incomingDescription;
        this.firstName = incomingFirstName;
        this.middleName = incomingMiddleName;
        this.lastName= incomingLastName;
    }
    

     /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getMechanicObject(){
        String[] stringArray = {this.firstName,this.middleName, this.lastName};
        return stringArray;
    }
    
    public void setImageIcon(ImageIcon incomingImageIcon){
        this.imageIcon = incomingImageIcon;
    }
    
    public ImageIcon getImageIcon(){
        return this.imageIcon;
    }
    
    public String getFullName(){
        return this.firstName + " " + this.middleName + " " + lastName;
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
    
    /**
     * Override toString to display relevent info etc
     * @return 
     */
  @Override
  public String toString() {
    return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
  }
}
