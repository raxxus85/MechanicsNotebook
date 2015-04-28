/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import objectmodels.Mechanic;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseUser;
//import org.parse4j.ParseObject;
//import org.parse4j.ParseUser;

/**
 *
 * @author Mark
 */
public class TestClass {
    public static void main(String[] args) throws ParseException {
        
        TestClass test = new TestClass();
        test.testRun();
      
    }
    
    public void testRun() throws ParseException{
        
        ParseEngine parseEngine = new ParseEngine();
        
        ParseUser user = parseEngine.signInUser("mark.p.milford@gmail.com", "password");
        
        //String appId="ijXu9OWAFfekiM8hFlLjrHa7A2BK3kWir8X1v5nM";
        //String restAppId= "sB8eqMDf5M8gyEzgkwJ8EzQRhYGaTeAbTPhfhTNE";        
        //Parse.initialize(appId, restAppId);
        
        // user login
        //ParseUser user = new ParseUser();
        //user.setUsername("mark.p.milford2@gmail.com");
        //user.setEmail("mark.p.milford2@gmail.com");
        //user.setPassword("p_n7!-e8");       

        //try {
            //user.signUp();
        //    user = user.login("mark.p.milford2@gmail.com","p_n7!-e8");
            //user.signUp()
        //    user.save();
            //user.logout();                       
        //} catch (ParseException ex) {
            //Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        //    ex.printStackTrace();
        //}
        
        // build out garage
        ParseObject garage = new ParseObject("Garage");
        garage.put("User", user);
        garage.save();
        
        Mechanic testMechanic = new Mechanic("Mark", "Patrick", "Milford");
        
        ParseObject mechanic = new ParseObject("Mechanic");
        mechanic.put("JavaObject",testMechanic);
        mechanic.put("Garage",garage);

        mechanic.save();
        
        ParseObject customer = new ParseObject("Customer");
        customer.put("Garage", garage);
        customer.put("FirstName", "Mark");
        customer.put("MiddleName", "Patrick");
        customer.put("LastName","Milford");
        customer.save();
        
        ParseObject vehicle = new ParseObject("Vehicle");
        vehicle.put("Make", "Saab");
        vehicle.put("Model", "9-3");
        vehicle.put("Customer", customer);
        vehicle.put("Odometer", 150002);
        vehicle.save();
        

        //System.out.println("Test");
        
        //ParseUser user = new ParseUser();
        //System.out.println("Test2");
        //user.setUsername("mark_milford");
        //user.setPassword("bkelly69");
        //user.setEmail("mark.p.milford@gmail.com");
        //System.out.println("Test2");
        
        //try {
        //    user.signUp();
        //} catch (ParseException ex) {
        //    //Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

}
