package motogaragemechanic;

/**
 * Mechanic Class
 * @author Mark
 */
public class Mechanic {
    private String name;
    
    public Mechanic(String incomingName){
        this.name=incomingName;
    }
    
    public String getName(){
        return this.name;
    }
}
