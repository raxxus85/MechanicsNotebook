package objectmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The "main" saving point for MotoGarage Mechanic App. 
 * @author Mark
 */
public class Garage implements Serializable{
    private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    
    /**
     * Method to return the ArrayList<Customer> customers of the garage.
     * @return ArrayList<Customer> customers
     */
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }
    
    public ArrayList<Mechanic> getMechanics(){
        return this.mechanics;
    }
    
    public void addMechanic(Mechanic incomingMechanic){
        this.mechanics.add(incomingMechanic);
    }
    
    public void addCustomer(Customer incomingCustomer){
        this.customers.add(incomingCustomer);
    }
    
    /**
     * Return the array of Mechanics (converted from ArrayList)
     * 
    */
    public Mechanic[] getMechanicsArray(){
        ArrayList<Mechanic> mechanicArrayList = this.getMechanics();
        Mechanic[] mechanicArray = mechanicArrayList.toArray(new Mechanic[mechanicArrayList.size()]);
        return mechanicArray;
    }
    
    /**
     * Return the array of Customers (converted from ArrayList)
     * 
    */
    public Customer[] getCustomersArray(){
        ArrayList<Customer> customerArrayList = this.getCustomers();
        Customer[] customerArray = customerArrayList.toArray(new Customer[customerArrayList.size()]);
        return customerArray;
    }
}
