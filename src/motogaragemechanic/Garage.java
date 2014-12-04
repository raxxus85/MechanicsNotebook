package motogaragemechanic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The "main" saving point for MotoGarage Mechanic App. 
 * @author Mark
 */
public class Garage implements Serializable{
    private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private ArrayList<Consumer> consumers = new ArrayList<Consumer>();
    
    
    public ArrayList<Mechanic> getMechanics(){
        return this.mechanics;
    }
    
    public void addMechanic(Mechanic incomingMechanic){
        this.mechanics.add(incomingMechanic);
    }
}
