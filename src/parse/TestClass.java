/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parse;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.parse4j.ParseException;
import org.parse4j.ParseUser;
import org.parse4j.callback.LoginCallback;

/**
 *
 * @author Mark
 */
public class TestClass {
    public static void main(String[] args) {
        System.out.println("Test");
        
        ParseUser user = new ParseUser();
        user.setUsername("TestName");
        user.setPassword("TestPass");
        user.setEmail("testUser1@example.com");
        
        try {
            user.signUp();
        } catch (ParseException ex) {
            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
    

}
